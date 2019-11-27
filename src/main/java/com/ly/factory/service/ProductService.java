package com.ly.factory.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ly.factory.domain.Product;
import com.ly.factory.domain.dto.ProductDTO;
import com.ly.factory.mapper.ProductMapper;
import com.ly.factory.utils.PageResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @author Tarry
 * @create 2019/10/19 13:15
 */
@Service
public class ProductService {
    @Autowired
    ProductMapper productMapper;

    //插入一条product新纪录
    public void insertProduct(Product product) {
        product.setCreateTime(new Date());
        product.setState(0);
        product.setCurCount(0);
        productMapper.insert(product);
    }

    /**
     * 条件查询product列表
      * @param name
     * @param type
     * @param sDate
     * @param eDate
     * @param currentPage
     * @param pageSize
     * @param pageTotal
     * @return
     */
    public PageResult<Product> queryListProduct(String name, Integer type, Date sDate, Date eDate, Integer currentPage, Integer pageSize, Integer pageTotal) {
        //分页
        PageHelper.startPage(currentPage,pageSize);

        //过滤条件
        Example example = new Example(Product.class);
        Example.Criteria criteria = example.createCriteria();
        //根据名字过滤
        if (StringUtils.isNoneBlank(name)){
            criteria.andLike("title","%"+name+"%");
        }
        //根据产品状态过滤
        if (type != null){
            criteria.andEqualTo("state",type);
        }
        //根据时间过滤
        if (sDate != null && eDate != null){
            criteria.andBetween("createTime",sDate,eDate);
        }
        //默认按时间排序
        example.setOrderByClause("create_time DESC");

        List<Product> productList = productMapper.selectByExample(example);

        //解析分页结果
        PageInfo<Product> productPageInfo = new PageInfo<Product>(productList);

        return new PageResult<Product>(productPageInfo.getTotal(), productList);
    }

    /**
     * 根据产品id查询产品名称
     * @param id
     * @return
     */
    public String queryNameById(Integer id) {
        return productMapper.queryNameById(id);
    }

    public ProductDTO queryState(Integer pid) {
        return productMapper.queryState(pid);

    }
}
