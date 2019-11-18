package com.ly.factory.mapper;

import com.ly.factory.domain.Component;
import com.ly.factory.utils.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
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
}
