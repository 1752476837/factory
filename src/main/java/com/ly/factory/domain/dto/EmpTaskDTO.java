package com.ly.factory.domain.dto;


import lombok.Data;

import javax.persistence.Transient;

/**
 * @author Tarry
 * @create 2019/11/21 14:58
 */
@Data
public class EmpTaskDTO {
    private Integer id;
    private Integer empId;
    @Transient
    private Integer type;
}
