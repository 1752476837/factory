package com.ly.factory.mapper;


import com.ly.factory.domain.Product;
import com.ly.factory.utils.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

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
    @Update("update tb_product set state = #{state} where pid = #{pid}")
    public void setState(@Param("pid") int pid,@Param("state") int state);
}
