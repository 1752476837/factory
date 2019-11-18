package com.ly.factory.mapper;

import com.ly.factory.domain.Params;
import com.ly.factory.utils.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Tarry
 * @create 2019/11/15 16:18
 */
@Repository
public interface ParamsMapper extends BaseMapper<Params,Integer> {

    @Select("select id,title,content from tb_param_template where component_id =#{comp_id}")
    public List<Params> queryListByComponentId(@Param("comp_id") Integer id);
}
