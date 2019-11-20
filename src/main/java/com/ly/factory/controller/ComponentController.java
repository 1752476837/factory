package com.ly.factory.controller;

import com.ly.factory.domain.Component;
import com.ly.factory.domain.vo.Skill;
import com.ly.factory.service.ComponentService;
import com.ly.factory.utils.PageResult;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * @author Tarry
 * @create 2019/11/15 9:39
 */
@RestController
@RequestMapping("component")
public class ComponentController {

    @Autowired
    private ComponentService componentService;

    /**
     * 新增组件
     * @param component
     * @return
     */
    @PostMapping("insert")
    public ResponseEntity<Void> insertComponent(@RequestBody Component component){
        componentService.insertComponent(component);
        return ResponseEntity.ok().build();
    }

    /**
     * 修改组件
     * @param component
     * @return
     */
    @PutMapping("update")
    public ResponseEntity<Void> updateComponent(@RequestBody Component component){

        System.out.println(component);
        componentService.updateComponent(component);

        return ResponseEntity.ok().build();
    }

    /**
     * 按条件查询组件列表
     * @return
     */
    @GetMapping("queryList")
    public ResponseEntity<PageResult<Component>> queryListComponent(
            @RequestParam(required=false) String name,
            @RequestParam(required=false) Integer type,
            @RequestParam(required=false) Long startDate,
            @RequestParam(required=false) Long endDate,
            @RequestParam(required=false) Integer currentPage,
            @RequestParam(required=false) Integer pageSize,
            @RequestParam(required=false) Integer pageTotal
    ){
        Date sDate = null;
        Date eDate = null;
        if (startDate != null){
            sDate = new Date(startDate);
        }
        if (endDate != null){
            eDate = new Date(endDate+86399999);
        }


        PageResult<Component> result = componentService.queryListComponent(name, type, sDate, eDate, currentPage, pageSize, pageTotal);
        return ResponseEntity.ok(result);

    }

    /**
     * 查询id和名称 作为select下拉列表的选项【参数模板页】
     * @return
     */
    @GetMapping("querySelect")
    public ResponseEntity<List<Component>> querySelectComponent(){
        List<Component> list = componentService.querySelectComponent();
        return ResponseEntity.ok(list);
    }

    /**
     * 查询id和名称和类型名称 作为select下拉列表的选项【流程管理页】
     * @return
     */
    @GetMapping("querySelect2")
    public ResponseEntity<List<Component>> querySelectComponent2(){
        List<Component> list = componentService.querySelectComponent2();
        return ResponseEntity.ok(list);
    }

    /**
     * 注册页面获取职能列表
     * @return
     */
    @GetMapping("querySkillSelect")
    public ResponseEntity<List<Skill>> querySkillSelect(){
        List<Skill> list = componentService.querySkillSelect();
        return ResponseEntity.ok(list);
    }

}
