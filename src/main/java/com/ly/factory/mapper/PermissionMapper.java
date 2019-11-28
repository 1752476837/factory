package com.ly.factory.mapper;

/**
 * @author Tarry
 * @create 2019/11/28 11:28
 */

import com.ly.factory.domain.Permission;
import com.ly.factory.utils.BaseMapper;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionMapper extends BaseMapper<Permission,Integer> {

}
