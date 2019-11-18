package com.ly.factory.domain;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.HashMap;

/**
 * @author Tarry
 * @create 2019/10/18 15:34
 */
@Data
@Table(name = "tb_product")
public class Products  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String param;
    private String type;
    private String fuzeren;
    private String shenheren;



}
