package com.ly.factory.mapper;

import com.ly.factory.domain.Component;
import com.ly.factory.domain.vo.Skill;
import com.ly.factory.utils.BaseMapper;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Tarry
 * @create 2019/11/15 9:51
 */
@Repository
public interface ComponentMapper extends BaseMapper<Component,Integer> {

    @Select("select id,title from tb_component")
    public List<Component> queryIdAndTitle();

    @Select("SELECT A.id,A.title,B.type AS type_title FROM tb_component A JOIN tb_component_type B ON A.type_id = B.id")
    @Results({
            @Result(id=true,property="id",column="id"),
            @Result(property="title",column="title"),
            @Result(property="typeTitle",column="type_title")
    })
    public List<Component> selectListAndTypeTitle();


    //根据Component的id 查询 Component的Title
    @Select("select title from tb_component where id=#{id}")
    public String queryTitleById(@Param("id") Integer id);

    //获取注册页面的职能列表
    @Select("select id,type from tb_component_type")
    @Results({
            @Result(id=true,property="value",column="id"),
            @Result(property="label",column="type"),
            @Result(property = "options",column = "id" ,javaType = java.util.List.class,many = @Many(select = "com.ly.factory.mapper.ComponentMapper.querySkillType"))
    })
    public List<Skill> querySkillSelect();

    //根据不同的组件类型查询列表
    @Select("select id,title from tb_component where type_id=#{id}")
    @Results({
            @Result(id=true,property="value",column="id"),
            @Result(property="label",column="title"),
    })
    public List<Skill> querySkillType(@Param("id") Integer id);


}
