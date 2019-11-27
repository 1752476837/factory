package com.ly.factory.mapper;

import com.ly.factory.domain.User;
import com.ly.factory.utils.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * @author Tarry
 * @create 2019/11/27 9:38
 */
@Repository
public interface UserMapper extends BaseMapper<User,Integer> {

}
