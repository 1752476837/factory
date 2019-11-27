package com.ly.factory.service;

import com.ly.factory.domain.User;
import com.ly.factory.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Tarry
 * @create 2019/11/27 9:37
 */
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

//    根据账号phone查询user对象
    public User findUserByPhone(String phone){
        User user = new User();
        user.setPhone(phone);
        return userMapper.selectOne(user);
    }


}
