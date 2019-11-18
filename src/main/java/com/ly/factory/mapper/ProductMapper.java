package com.ly.factory.mapper;


import com.ly.factory.domain.Products;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

/**
 * @author Tarry
 * @create 2019/10/18 15:44
 */
@Repository
public interface ProductMapper extends Mapper<Products> {
}
