package com.ly.factory.service;

import com.ly.factory.domain.Employee;
import com.ly.factory.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    EmployeeMapper employeeMapper;
    public List<Employee> getEmployees(){
        return employeeMapper.getEmployees();
    }

    public int addEmployee(Employee employee){
        return employeeMapper.insert(employee);
    }

    public int loginService(Employee employee){
        System.out.println(employee);
        return employeeMapper.login(employee);
    }

    public int regEmployee(Employee employee){
        System.out.println("regEmployee...."+employee);
        return employeeMapper.reg(employee);
    }

    //查询员工根据姓名service
    public List<Employee> searchEmp(String name){
        System.out.println("searchEmp..."+name);
        return employeeMapper.searchEmps(name);
    }

    /**
     * 查询员工身份的列表
     * 1负责人
     * 2审核人
     * @return
     */
    public List<Employee> queryIdentityList(Integer identity) {
        return employeeMapper.queryByIdentity(identity);
    }

}
