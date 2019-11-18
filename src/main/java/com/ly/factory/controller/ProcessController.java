package com.ly.factory.controller;

import com.ly.factory.domain.Process;
import com.ly.factory.service.ProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Tarry
 * @create 2019/11/16 10:24
 */
@RestController
@RequestMapping("process")
public class ProcessController {
    @Autowired
    private ProcessService processService;

    /**
     * 新添加一条 工业流程 记录
     * @param process
     * @return
     */
    @PostMapping("insert")
    public ResponseEntity<Void> insertProcess(@RequestBody Process process){
        processService.insertProcess(process);
        return ResponseEntity.ok().build();
    }


    /**
     * 查询全部流程的树结构
     * @return
     */
    @GetMapping("queryTree")
    public ResponseEntity<List<Process>> queryTree(){
        List<Process> treeList = processService.queryTree();
        return ResponseEntity.ok(treeList);
    }

}
