package com.ly.factory.service;

import com.ly.factory.domain.Permission;
import com.ly.factory.domain.User;
import com.ly.factory.mapper.PermissionMapper;
import com.ly.factory.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author Tarry
 * @create 2019/11/27 9:37
 */
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PermissionMapper permissionMapper;

//    根据账号phone查询user对象
    public User findUserByPhone(String phone){
        User user = new User();
        user.setPhone(phone);
        User user2 = userMapper.selectOne(user);
        if (user2 == null){
            return null;
        }
        Integer id = user2.getId();
        List<Integer> permissionIdList = userMapper.queryPermissionIdByUserId(id);
        if (CollectionUtils.isEmpty(permissionIdList)){
            return null;
        }
        List<Permission> permissionList = permissionMapper.selectByIdList(permissionIdList);

        user2.setPermissionList(permissionList);
        return user2;
    }


}
