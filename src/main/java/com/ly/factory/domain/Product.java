package com.ly.factory.domain;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import java.util.HashMap;

/**
 * @author Tarry
 * @create 2019/10/18 15:34
 */
@Data
@Table(name = "tb_product")
public class Product  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private Date createTime;
    private Date endTime;
    private Integer state;  //状态
    private Integer dutyId;
    private Integer curCount;  //当前完成数量
    private Integer count;     //总量


}
