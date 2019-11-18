package com.ly.factory.controller;

import com.ly.factory.domain.Employee;
import com.ly.factory.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Tarry
 * @create 2019/11/16 13:56
 */
@RestController
@RequestMapping("employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    /**
     * 根据员工身份查询列表
     * 1 负责人
     * 2 审核人
     * @param identity
     * @return
     */
    @GetMapping("queryIdentity")
    public ResponseEntity<List<Employee>> queryIdentity(@RequestParam("identity") Integer identity){
        List<Employee> employeeList= employeeService.queryIdentityList(identity);
        return ResponseEntity.ok(employeeList);
    }
}
