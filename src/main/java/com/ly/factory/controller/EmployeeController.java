package com.ly.factory.controller;

import com.ly.factory.domain.Component;
import com.ly.factory.domain.Employee;
import com.ly.factory.service.EmployeeService;
import com.ly.factory.utils.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("queryList")
    public ResponseEntity<PageResult<Employee>> queryList(
            @RequestParam(required=false) String name,
            @RequestParam(required=false) Integer type,
            @RequestParam(required=false) Integer currentPage,
            @RequestParam(required=false) Integer pageSize,
            @RequestParam(required=false) Integer pageTotal
    ){
        PageResult<Employee> list = employeeService.queryList(name,type,currentPage,pageSize,pageTotal);
        return ResponseEntity.ok(list);
    }


    /**
     * 根据员工身份查询列表
     * 1 负责人
     * 2 审核人
     * @param identity
     * @return
     */
    @GetMapping("queryIdentity")
    public ResponseEntity<List<Employee>> queryIdentity(@RequestParam("identity") Integer identity,@RequestParam("componentId") Integer componentId){
        List<Employee> employeeList= employeeService.queryIdentityList(identity,componentId);
        return ResponseEntity.ok(employeeList);
    }

    /**
     * 员工注册
     * @param employee
     * @return
     */
    @PostMapping("register")
    public ResponseEntity<Void> register(@RequestBody Employee employee){
        employee.setCompany(employee.getFactoryId()[0]); //设置公司id
        employee.setFactory(employee.getFactoryId()[1]);  //设置工厂id
        employee.getSmscode(); //获取验证码，使用redis数据库做检验
        //修改skill的格式，方便后期查找
        String replace1 = employee.getSkill().replace("[", ",");
        String replace2 = replace1.replace("]", ",");
        employee.setSkill(replace2);
        employeeService.register(employee);
        return ResponseEntity.ok().build();
    }

    /**
     * 更改员工的状态
     * @param id
     * @param state
     * @return
     */
    @PutMapping("updateState/{id}/{state}")
    public ResponseEntity<Void> updateState(@PathVariable("id") Integer id,@PathVariable("state") Integer state){
        employeeService.updateState(id,state);
        return ResponseEntity.ok().build();
    }

}
