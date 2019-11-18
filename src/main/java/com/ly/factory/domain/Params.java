package com.ly.factory.domain;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * 参数模板的实体类
 */
@Data
@Table(name = "tb_param_template")
public class Params {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String content; //参数列表的json文本
    private Integer componentId;
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

//    不作为数据库字段
    @Transient
    private String componentTitle;
}
