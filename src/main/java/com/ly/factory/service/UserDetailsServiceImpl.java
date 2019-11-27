package com.ly.factory.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    ClientDetailsService clientDetailsService;
    @Autowired
    UserService userService;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //取出身份，如果身份为空说明没有认证
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //没有认证统一采用httpbasic认证，httpbasic中存储了client_id和client_secret，开始认证client_id和client_secret
        if(authentication==null){
            ClientDetails clientDetails = clientDetailsService.loadClientByClientId(username);
            if(clientDetails!=null){
                //密码
                String clientSecret = clientDetails.getClientSecret();
                return new User(username,clientSecret, AuthorityUtils.commaSeparatedStringToAuthorityList(""));
            }
        }
        if (StringUtils.isEmpty(username)) {
            return null;
        }
//        远程请求用户中心，根据账号查询用户信息
        com.ly.factory.domain.User curUser = userService.findUserByPhone(username);
        if (curUser == null){
            return null;
        }

        //取出正确密码（hash值）
        String password = curUser.getPassword();

//        ------------------------------------------------------------
        List<String> user_permission = new ArrayList<>();
        //使用静态的权限表示用户所拥有的权限
        user_permission.add("course_get_baseinfo");//查询课程信息
        //user_permission.add("course_pic_list");//图片查询
        String user_permission_string  = StringUtils.join(user_permission.toArray(), ",");

        List<GrantedAuthority> authList = AuthorityUtils.commaSeparatedStringToAuthorityList(user_permission_string);


        UserJwt userDetails = new UserJwt(username,password,authList);
        userDetails.setId(String.valueOf(curUser.getId()));
        userDetails.setName(curUser.getPhone());//用户名称
       /* UserDetails userDetails = new org.springframework.security.core.userdetails.User(username,
                password,
                AuthorityUtils.commaSeparatedStringToAuthorityList(""));*/
//                AuthorityUtils.createAuthorityList("course_get_baseinfo","course_get_list"));
        return userDetails;
    }
}
