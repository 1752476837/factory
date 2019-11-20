package com.ly.factory.controller;

import com.ly.factory.domain.Process;
import com.ly.factory.service.ProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
     * 将新增的这个流程返回给前台 显示
     * @param process
     * @return
     */
    @PostMapping("insert")
    public ResponseEntity<Void> insertProcess(@RequestBody Process process){
        processService.insertProcess(process);
        return ResponseEntity.ok().build();
    }


    /**
     * 查询全部流程的树结构【根据产品id】
     * @return
     */
    @GetMapping("queryTree/{id}")
    public ResponseEntity<List<Process>> queryTree(@PathVariable("id") Integer id){
        List<Process> treeList = processService.queryTree(id);
        return ResponseEntity.ok(treeList);
    }

    /**
     * 更新process的记录
     * @param process
     * @return
     */
    @PostMapping("update")
    public ResponseEntity<Void> updateProcess(@RequestBody Process process){
        processService.updateProcess(process);
        return ResponseEntity.ok().build();
    }

    /**
     * 根据id删除process
     * 如果该process包含子process则返回提示
     * 返回leaves 代表叶子节点， 已经直接删除
     * 返回branch 表示包含子节点，需要再次确认
     * @param id
     * @return
     */
    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteProcess(@PathVariable("id") Integer id){
        String msg = processService.deleteProcess(id);

        return ResponseEntity.ok(msg);
    }

    /**
     * 删除，该节点及其所有子节点
     * @param id
     * @return
     */
    @DeleteMapping("deleteAll/{id}")
    public ResponseEntity<Void> deleteAllProcess(@PathVariable("id") Integer id){
        processService.deleteAllProcess(id);

        return ResponseEntity.ok().build();
    }

    /**
     * 开始生产该产品
     * 生产员工任务数据
     * 通知员工该干活了
     * @return
     */
    @PostMapping("startCreate/{pid}")
    public ResponseEntity<Void> startCreate(@PathVariable("pid") Integer pid){

        processService.startCreate(pid);
        return ResponseEntity.ok().build();
    }


}
