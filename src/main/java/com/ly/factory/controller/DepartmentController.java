package com.ly.factory.controller;

import com.ly.factory.domain.Department;
import com.ly.factory.service.DepartmentService;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Tarry
 * @create 2019/11/26 16:40
 */
@RestController
@RequestMapping("dept")
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    //查询部门树结构列表
    @GetMapping("deptList")
    public ResponseEntity<List<Department>> getDeptList(){
        List<Department> list = departmentService.getDeptList();
        return ResponseEntity.ok(list);
    }

//    新增部门
    @PostMapping("add")
    public ResponseEntity<Void> insertDept(@RequestBody Department department){
        departmentService.insertDept(department);
        return ResponseEntity.ok().build();
    }

//    修改部门
    @PutMapping("update")
    public ResponseEntity<Void> updateDept(@RequestBody Department department){
        departmentService.updateDept(department);
        return ResponseEntity.ok().build();
    }

//    删除部门
    @DeleteMapping("del")
    public ResponseEntity<Void> delDept(@RequestParam("id") Integer id){
        departmentService.delDept(id);
        return ResponseEntity.ok().build();
    }


}
