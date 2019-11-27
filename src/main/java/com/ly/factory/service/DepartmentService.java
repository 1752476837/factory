package com.ly.factory.service;

import com.ly.factory.domain.Department;
import com.ly.factory.mapper.DepartmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Tarry
 * @create 2019/11/26 16:39
 */
@Service
public class DepartmentService {
    @Autowired
    private DepartmentMapper departmentMapper;

    //查询部门树结构列表
    public List<Department> getDeptList() {
        return  departmentMapper.getDeptList(0);
    }

    //新增部门
    public void insertDept(Department department) {
        departmentMapper.insert(department);
    }

    //修改部门
    public void updateDept(Department department) {
        departmentMapper.updateDept(department);
    }

    public void delDept(Integer id) {
        departmentMapper.deleteByPrimaryKey(id);
    }
}
