package com.ly.factory.mapper;

import com.ly.factory.domain.Permission;
import com.ly.factory.domain.User;
import com.ly.factory.utils.BaseMapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Tarry
 * @create 2019/11/27 9:38
 */
@Repository
public interface UserMapper extends BaseMapper<User,Integer> {

    @Select("select permission_id from tb_user_role A,tb_role_permission B where A.id = #{id} and B.role_id = A.role_id")
    List<Integer> queryPermissionIdByUserId(Integer id);

}
