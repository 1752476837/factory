package com.ly.factory.domain.vo;

import lombok.Data;

import java.util.List;

/**
 * @author Tarry
 * @create 2019/11/19 17:45
 * 给前台的职能列表
 */
@Data
public class Skill {
    private Integer value;   //id
    private String label;    //名称
    private List<Skill> options; //子列表
}
