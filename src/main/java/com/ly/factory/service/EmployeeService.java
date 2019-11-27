package com.ly.factory.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ly.factory.domain.Component;
import com.ly.factory.domain.Employee;
import com.ly.factory.exception.ExceptionEnum;
import com.ly.factory.exception.LyException;
import com.ly.factory.mapper.ComponentMapper;
import com.ly.factory.mapper.EmployeeMapper;
import com.ly.factory.utils.PageResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    EmployeeMapper employeeMapper;
    @Autowired
    ComponentMapper componentMapper;

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
    public List<Employee> queryIdentityList(Integer identity,Integer componentId) {
        String comp = String.valueOf(componentId);
        String skill = ","+comp+",";
        return employeeMapper.queryByIdentity(identity,skill);
    }

    /**
     * 员工注册
     * @param employee
     */
    public void register(Employee employee) {
        //验证手机号是否已经被注册
        Integer integer = employeeMapper.selectByPhone(employee.getPhone());
        if (integer != 0){
            throw new LyException(ExceptionEnum.PHONE_IS_EXIST);
        }


        //密码应该进行加密，开发阶段略过
        employeeMapper.insert(employee);
    }

    public PageResult<Employee> queryList(String name, Integer type, Integer currentPage, Integer pageSize, Integer pageTotal) {
        //分页
        PageHelper.startPage(currentPage,pageSize);


        List<Employee> employeeList = new ArrayList<>();
        //无条件查询列表
        if (StringUtils.isBlank(name) && type == null){
            employeeList = employeeMapper.queryList();
        }else if (StringUtils.isNotBlank(name) && type == null){
            //只按名称查询
            employeeList = employeeMapper.queryListByName(name);
        }else if (StringUtils.isBlank(name) && type != null){
            //只按状态查询
            employeeList = employeeMapper.queryListByState(type);
        }else if (StringUtils.isNotBlank(name) && type != null){
            //名称+状态查询
            employeeList = employeeMapper.queryListByNameAndState(name,type);

        }





        for (Employee employee : employeeList) {
            String str = employee.getSkill().substring(1, employee.getSkill().length() - 1);
            String[] arr = str.split(",");
            List<Integer> list = new ArrayList<>();
            for (String s : arr) {
                list.add(Integer.valueOf(s));
            }

            List<Component> comList = componentMapper.selectByIdList(list);
            List<String> strList = new ArrayList<>();
            for (Component component : comList) {
                strList.add(component.getTitle());
            }
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                String s = objectMapper.writeValueAsString(strList);
                employee.setSkill(s);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }

        }


        //解析分页结果
        PageInfo<Employee> employeePageInfo = new PageInfo<Employee>(employeeList);

        return new PageResult<Employee>(employeePageInfo.getTotal(), employeeList);
    }

    //根据id，修改员工状态
    public void updateState(Integer id, Integer state) {
        employeeMapper.updateStateById(id,state);
    }
}
