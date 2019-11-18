package com.ly.factory.controller;

import com.ly.factory.domain.Component;
import com.ly.factory.domain.Params;
import com.ly.factory.service.ParamsService;
import com.ly.factory.utils.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * @author Tarry
 * @create 2019/11/15 16:20
 */
@RestController
@RequestMapping("params")
public class ParamsController {

    @Autowired
    private ParamsService paramsService;

    /**
     * 新增模板
     * @return
     */
    @PostMapping("insert")
    public ResponseEntity<Void> insertParams(@RequestBody Params params){
        paramsService.insertParams(params);

        return ResponseEntity.ok().build();
    }

    /**
     * 按条件查询参数模板列表
     * @return
     */
    @GetMapping("queryList")
    public ResponseEntity<PageResult<Params>> queryListParams(
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


        PageResult<Params> result = paramsService.queryListParams(name, type, sDate, eDate, currentPage, pageSize, pageTotal);
        return ResponseEntity.ok(result);

    }

    /**
     * 更新参数模板数据
     * @param params
     * @return
     */
    @PutMapping("update")
    public ResponseEntity<Void> updateParams(@RequestBody Params params){
        paramsService.updateParams(params);

        return ResponseEntity.ok().build();
    }

    /**
     * 根据Component组件的id 查询对应的参数模板
     * @param id
     * @return
     */
    @GetMapping("queryListByComponentId")
    public ResponseEntity<List<Params>> queryListByComponentId(@RequestParam("id") Integer id){
        List<Params> list = paramsService.queryListByComponentId(id);
        return ResponseEntity.ok(list);
    }

}
