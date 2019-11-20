package com.ly.factory.domain;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Tarry
 * @create 2019/11/20 16:25
 * 对应 员工任务 的 实体类
 */
@Data
@Table(name = "tb_emp_task")
public class EmpTask {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer empId;   //员工的id
    private Integer processId; //该员工负责的流程Id
    private Integer state;    //当前任务的完成状态，任务状态，0未开始，1进行中，2待审核，3不合格，4已完成

}
