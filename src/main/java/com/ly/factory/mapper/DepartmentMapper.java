package com.ly.factory.mapper;

import com.ly.factory.domain.Department;
import com.ly.factory.utils.BaseMapper;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Tarry
 * @create 2019/11/26 16:38
 */
@Repository
public interface DepartmentMapper extends BaseMapper<Department,Integer> {

    //查询部门树结构列表
    @Select("select id,title,parent_id from tb_department where parent_id = #{id}")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "title",column = "title"),
            @Result(property = "parentId",column = "parent_id"),
            @Result(property = "children",column = "id",many = @Many(select = "com.ly.factory.mapper.DepartmentMapper.getDeptList")),
    })
    List<Department> getDeptList(Integer id);

    //修改部门名称
    @Update("update tb_department set title = #{dept.title} where id = #{dept.id}")
    void updateDept(@Param("dept")Department department);
}
