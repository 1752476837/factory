package com.ly.factory.domain;

import lombok.Data;

import java.util.HashMap;
import java.util.List;

/**
 * @author Tarry
 * @create 2019/10/19 13:35
 */
@Data
public class ProductVo extends Products {
    private List<HashMap<String,String>> params;
}
