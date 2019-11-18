package com.ly.factory.service;

import com.ly.factory.domain.Employee;
import com.ly.factory.domain.Products;
import com.ly.factory.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Tarry
 * @create 2019/10/19 13:15
 */
@Service
public class ProductService {
    @Autowired
    ProductMapper productMapper;


    public Products getProduct(Long id){
      return  productMapper.selectByPrimaryKey(id);
    }

    public Integer addProduct(Products products){
        return productMapper.insert(products);
    }

}
