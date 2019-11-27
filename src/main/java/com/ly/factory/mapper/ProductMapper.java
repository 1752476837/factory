package com.ly.factory.mapper;


import com.ly.factory.domain.Product;
import com.ly.factory.domain.dto.ProductDTO;
import com.ly.factory.utils.BaseMapper;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.HashMap;

/**
 * @author Tarry
 * @create 2019/10/18 15:44
 */
@Repository
public interface ProductMapper extends BaseMapper<Product,Integer> {

    //根据id查询title
    @Select("select title from tb_product where id = #{id}")
    public String queryNameById(@Param("id") Integer id);

    //根据id设置该product的状态
    @Update("update tb_product set state = #{state} where id = #{pid}")
    public void setState(@Param("pid") int pid,@Param("state") int state);

    @Select("select state,cur_count,count from tb_product where id =#{pid}")
    @Results({
            @Result(property = "state",column = "state"),
            @Result(property = "curCount",column = "cur_count"),
            @Result(property = "count",column = "count"),
    })
    ProductDTO queryState(Integer pid);
}
