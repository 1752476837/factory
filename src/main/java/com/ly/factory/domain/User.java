package com.ly.factory.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * @author Tarry
 * @create 2019/11/27 9:31
 */
@Data
@Table(name = "tb_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String phone;
    private String password;
    private String salt;
    private String name;
    private Integer gender;
    private String department;
    private String position;

    @Transient
    private List<Permission> permissionList;

}
