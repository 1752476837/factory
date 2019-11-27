package com.ly.factory.controller;

import com.ly.factory.domain.Product;
import com.ly.factory.domain.dto.ProductDTO;
import com.ly.factory.service.ProductService;
import com.ly.factory.utils.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @author Tarry
 * @create 2019/11/20 10:41
 */
@RestController
@RequestMapping("product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping("insert")
    public ResponseEntity<Void> insertProduct(@RequestBody Product product){
        productService.insertProduct(product);
        return ResponseEntity.ok().build();
    }
//    查询当前产品的状态  0 未开始  1运行中
    @GetMapping("state/{pid}")
    public ResponseEntity<ProductDTO> queryState(@PathVariable("pid") Integer pid){
        ProductDTO dto = productService.queryState(pid);
        System.out.println(dto);
        return ResponseEntity.ok(dto);
    }

    /**
     * 按条件查询参数模板列表
     * @return
     */
    @GetMapping("queryList")
    public ResponseEntity<PageResult<Product>> queryListProduct(
            @RequestParam(required=false) String name,
            @RequestParam(required=false) Integer type,
            @RequestParam(required=false) Long startDate,
            @RequestParam(required=false) Long endDate,
            @RequestParam(required=false) Integer currentPage,
            @RequestParam(required=false) Integer pageSize,
            @RequestParam(required=false) Integer pageTotal
    ){
        Date sDate = null;
        Date eDate = null;
        if (startDate != null){
            sDate = new Date(startDate);
        }
        if (endDate != null){
            eDate = new Date(endDate+86399999);
        }


        PageResult<Product> result = productService.queryListProduct(name, type, sDate, eDate, currentPage, pageSize, pageTotal);
        return ResponseEntity.ok(result);

    }


    /**
     * 根据产品id查询产品的名称
     * @return
     */
    @GetMapping("queryName/{id}")
    public ResponseEntity<String> queryNameById(@PathVariable("id") Integer id){
           String name = productService.queryNameById(id);
        System.out.println("name:"+name);
           return ResponseEntity.ok(name);
    }
}
