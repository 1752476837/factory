package com.ly.factory.controller;


import com.ly.factory.domain.Employee;
import com.ly.factory.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin   //解决跨域的注解
@RestController
public class RegController {

    @Autowired
    EmployeeService employeeService;

    @PostMapping("reg")
    public ResponseEntity reg(@ModelAttribute Employee employee){
        int i = employeeService.regEmployee(employee);
        if(i>=0){
            return ResponseEntity.status(HttpStatus.OK).body("注册成功");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("注册失败");
    }
}
