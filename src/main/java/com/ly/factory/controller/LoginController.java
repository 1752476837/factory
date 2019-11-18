package com.ly.factory.controller;

import com.ly.factory.domain.Employee;
import com.ly.factory.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin
@RestController
public class LoginController {

    @Autowired
    EmployeeService employeeService;
    @GetMapping("login")
    public ResponseEntity login(@RequestParam("username")String username,@RequestParam("password")String password){
        Employee employee=new Employee();
        employee.setPassword(password);
        employee.setName(username);
        int ret = employeeService.loginService(employee);
        if(ret>0){
            return ResponseEntity.status(HttpStatus.OK).body("登录成功");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("账号或密码错误");

    }
}
