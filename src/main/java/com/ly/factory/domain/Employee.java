package com.ly.factory.domain;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Tarry
 * @create 2019/10/18 16:02
 */
@Data
@Table(name = "tb_employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;        //姓名
    private String factory;    //工厂
    private String company;    //公司
    private String phone;     //手机号
    private String password;   //密码
    private Integer identity;  //身份【审核员还是负责人】
    private String skill;     //职能【可以负责什么】


}
