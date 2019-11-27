package com.ly.factory.domain;


import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * @author Tarry
 * @create 2019/10/18 16:06
 */
@Data
@Table(name = "tb_process")
public class Process {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer componentId;

    @Transient
    private Integer empId;
    @Transient
    private String componentTitle;   //组件名字，作为标题使用
    private Integer dutyId;
    private Integer checkId;
    private Integer paramId;
    private Integer parentId;
    private Integer state;
    private String content;
    private String productId;
    private List<Process> child;  //树结构，存放子流程
    private Integer count;   //总任务数量
    private Integer dutyCount; //生产的总量
    private Integer mistake;    //不合格的数量
    private Integer checkCount; //审核的总量
    private Integer passCount;    //审核之后合格的数量

}
