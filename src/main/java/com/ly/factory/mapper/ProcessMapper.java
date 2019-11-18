package com.ly.factory.mapper;

import com.ly.factory.domain.Process;
import com.ly.factory.utils.BaseMapper;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * @author Tarry
 * @create 2019/10/18 16:48
 */
@Repository
public interface ProcessMapper extends BaseMapper<Process,Integer> {

    //查询全部内容的树结构
    @Select("select * from tb_process where parent_id = #{pid}")
    @Results({
            @Result(id=true,property = "id" ,column = "id"),
            @Result(property = "componentId",column = "component_id"),
            @Result(property = "componentTitle",column = "component_id",javaType = java.lang.String.class,one = @One(select = "com.ly.factory.mapper.ComponentMapper.queryTitleById")),
            @Result(property = "dutyId",column = "duty_id"),
            @Result(property = "checkId",column = "check_id"),
            @Result(property = "paramId",column = "param_id"),
            @Result(property = "parentId",column = "parent_id"),
            @Result(property = "content",column = "content"),
            @Result(property = "child",column = "id" ,javaType = java.util.List.class,many = @Many(select = "com.ly.factory.mapper.ProcessMapper.queryNextTree"))
    })
    public List<Process> queryBaseTree(@Param("pid") Integer pid);


    //根据父id，查询process列表
    @Select("select * from tb_process where parent_id = #{pid}")
    @Results({
            @Result(id=true,property = "id" ,column = "id"),
            @Result(property = "componentId",column = "component_id"),
            @Result(property = "componentTitle",column = "component_id",javaType = java.lang.String.class,one = @One(select = "com.ly.factory.mapper.ComponentMapper.queryTitleById")),
            @Result(property = "dutyId",column = "duty_id"),
            @Result(property = "checkId",column = "check_id"),
            @Result(property = "paramId",column = "param_id"),
            @Result(property = "parentId",column = "parent_id"),
            @Result(property = "content",column = "content"),
            @Result(property = "child",column = "id" ,javaType = java.util.List.class,many = @Many(select = "com.ly.factory.mapper.ProcessMapper.queryNextTree"))
    })
    public List<Process> queryNextTree(@Param("pid") Integer pid);

    @Update("update tb_process set component_id=#{process.componentId},duty_id = #{process.dutyId},check_id = #{process.checkId},param_id = #{process.paramId},content = #{process.content} where id = #{process.id}")
    public void updateProcess(@Param("process") Process process);
}
