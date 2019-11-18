package com.ly.factory.controller;


import com.ly.factory.domain.Employee;
import com.ly.factory.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin   //解决跨域的注解
@RestController
public class SearchEmpController {

    @Autowired
    EmployeeService employeeService;
    //模糊查询员工根据姓名
    @GetMapping("/search")
    public ResponseEntity<List<Employee>> SearchEmp(@RequestParam("name") String name){
        List<Employee> emps=employeeService.searchEmp(name);
        return ResponseEntity.ok(emps);
    }
 }
