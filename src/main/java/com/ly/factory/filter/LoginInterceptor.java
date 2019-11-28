package com.ly.factory.filter;


import com.ly.factory.auth.AuthService;
import com.ly.factory.utils.CookieUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;

/**
 * @author bystander
 * @date 2018/10/3
 */
@Component
public class LoginInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    AuthService authService;

    public LoginInterceptor() {
        super();
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String method = request.getMethod();


        //验证请求头
        //取出头信息
        String authorization = request.getHeader("authorization");
        if(StringUtils.isEmpty(authorization)){
            //用户未登录,返回401，拦截
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return false;
        }
        if(!authorization.startsWith("Bearer ")){
            //用户未登录,返回401，拦截
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return false;
        }
        //取到jwt令牌
        String jwt = authorization.substring(7);
        if(StringUtils.isEmpty(jwt)){
            //拒绝访问
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return false;
        }




        //查询token
        String token = CookieUtil.readCookie(request,"uid").get("uid");
        if (StringUtils.isBlank(token)) {
            //用户未登录,返回401，拦截
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return false;
        }

        //
        String key = "user_token:"+token;

//        Long expire = stringRedisTemplate.getExpire(key, TimeUnit.SECONDS);
        Long expire = authService.getExpire(key);
        if (expire<0){
            //用户未登录,返回401，拦截
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return false;
        }


        return true;
    }

}
