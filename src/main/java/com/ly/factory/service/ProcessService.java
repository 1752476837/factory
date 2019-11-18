package com.ly.factory.service;

import com.ly.factory.domain.Process;
import com.ly.factory.mapper.ProcessMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Tarry
 * @create 2019/10/18 17:47
 */
@Service
public class ProcessService {
    @Autowired
    ProcessMapper processMapper;

    /**
     * 新增 一条流程
     * @param process
     */
    public void insertProcess(Process process) {
        process.setId(null);
        System.out.println("插入process："+process);
        processMapper.insert(process);
    }


    /**
     * 查询全部流程的树结构
     * @return
     */
    public List<Process> queryTree() {
        return processMapper.queryBaseTree(0);
    }

    /**
     * 更新修改，process记录
     * @param process
     * @return
     */
    public void updateProcess(Process process){

        processMapper.updateProcess(process);

    }

}
