package com.ly.factory.auth;


import com.ly.factory.domain.User;
import com.ly.factory.domain.ext.AuthToken;
import com.ly.factory.exception.ExceptionEnum;
import com.ly.factory.exception.LyException;
import com.ly.factory.utils.CookieUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author Administrator
 * @version 1.0
 **/
@RestController
public class AuthController {

    @Value("${auth.clientId}")
    String clientId;
    @Value("${auth.clientSecret}")
    String clientSecret;
    @Value("${auth.cookieDomain}")
    String cookieDomain;
    @Value("${auth.cookieMaxAge}")
    int cookieMaxAge;

    @Autowired
    AuthService authService;


    @PostMapping("loginSys")
    public ResponseEntity<String> login(@RequestBody User loginRequest) {

        if(loginRequest == null || StringUtils.isEmpty(loginRequest.getPhone())){
            throw new LyException(ExceptionEnum.AUTH_USERNAME_NONE);
        }
        if(loginRequest == null || StringUtils.isEmpty(loginRequest.getPassword())){
            throw new LyException(ExceptionEnum.AUTH_USERNAME_NONE);
        }
        //账号
        String username = loginRequest.getPhone();
        //密码
        String password = loginRequest.getPassword();
        System.out.println("账号："+username+"      密码："+password);
        //申请令牌
        AuthToken authToken =  authService.login(username,password,clientId,clientSecret);

        //用户身份令牌
        String access_token = authToken.getAccess_token();
        //将令牌存储到cookie
        this.saveCookie(access_token);

        return ResponseEntity.ok(authToken.getJwt_token());
}

    //将令牌存储到cookie
    private void saveCookie(String token){

        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        //HttpServletResponse response,String domain,String path, String name, String value, int maxAge,boolean httpOnly
        CookieUtil.addCookie(response,cookieDomain,"/","uid",token,cookieMaxAge,false);

    }


    public ResponseEntity logout() {
        return null;
    }


    /**
     * 根据cookie携带的身份短令牌，查询redis，返回jwt令牌给前台
     * @return
     */
    @GetMapping("userjwt")
    public ResponseEntity<String> userjwt() {
        //取出cookie的短令牌，身份令牌
        String uid = this.getTokenFromCookie();
        if (StringUtils.isBlank(uid)){
            //这里返回空值，不抛异常是因为，前台不显示就行，没必要抛异常
            return ResponseEntity.ok(null);
        }
        //去redis中查询jwt
        AuthToken userToken = authService.getUserToken(uid);
        //返回jwt令牌
        if (userToken != null){
            String jwt_token = userToken.getJwt_token();
            return ResponseEntity.ok(jwt_token);
        }
        return null;
    }

    //从cookie中取出身份令牌
    private String getTokenFromCookie(){
        //获取Request请求
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        //取多个cookie的话直接在后面累加
        Map<String, String> map = CookieUtil.readCookie(request,"uid");
        if (map != null && map.get("uid")!=null){
            return map.get("uid");
        }
        return null;
    }

}
