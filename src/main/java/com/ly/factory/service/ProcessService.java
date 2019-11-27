package com.ly.factory.service;

import com.ly.factory.domain.EmpTask;
import com.ly.factory.domain.Process;
import com.ly.factory.domain.dto.EmpTaskDTO;
import com.ly.factory.mapper.EmpTaskMapper;
import com.ly.factory.mapper.ProcessMapper;
import com.ly.factory.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Tarry
 * @create 2019/10/18 17:47
 */
@Service
public class ProcessService {
    @Autowired
    private ProcessMapper processMapper;
    @Autowired
    private EmpTaskMapper empTaskMapper;
    @Autowired
    private ProductMapper productMapper;

    /**
     * 新增 一条流程,并将新增的流程返回
     * @param process
     */
    public void insertProcess(Process process) {
        process.setId(null);
        process.setState(0);
        process.setDutyCount(0);
        process.setCheckCount(0);
        process.setMistake(0);
        process.setPassCount(0);
        processMapper.insert(process);
    }


    /**
     * 查询全部流程的树结构【根据产品id】
     * @return
     */
    public List<Process> queryTree(Integer id) {
        return processMapper.queryBaseTree(id);
    }

    /**
     * 更新修改，process记录
     * @param process
     * @return
     */
    public void updateProcess(Process process){

        processMapper.updateProcess(process);

    }

    /**
     * 如果为叶子节点直接删除
     * @param id
     */
    public String deleteProcess(Integer id) {
//      查询是否包含 子节点
        List<Process> processes = processMapper.queryNextTree(id);
        System.out.println(processes);
//       如果为空直接删除返回leaves，不为空返回返回branch
        if (processes ==null || processes.size()==0){
            processMapper.deleteByPrimaryKey(id);
            return "leaves";
        }else {
            return "branch";
        }

    }

    /**
     *
     * 删除该process及其所有的子节点
     * @param id
     */
    public void deleteAllProcess(Integer id) {
        List<Process> processes = processMapper.queryBaseTree(id);

        ArrayList<Integer> idList = new ArrayList<>();
        idList.add(id);
        recursive(processes,idList);
        System.out.println("删除的id："+idList);
        processMapper.deleteByIdList(idList);
    }



    //递归获取所有节点的id
    private void recursive(List<Process> processes,ArrayList idList){
      for (Process item:processes){
          if (item.getChild().size() == 0){
              idList.add(item.getId());
          }else{
              recursive(item.getChild(),idList);
          }
      }
    }


    /**
     * 开始生产该产品
     * 生产员工任务数据
     * 通知员工该干活了
     * @return
     */
    @Transactional
    public void startCreate(Integer pid) {
        System.out.println("开始生产id："+pid);
//       1. 查询该产品相关的负责人员工的id 集合
        List<Process> dutyList = processMapper.queryDutyListByProductId(pid);
        List<EmpTask> taskList = new ArrayList<>();
        for (Process item : dutyList) {
            EmpTask empTask = new EmpTask();
            empTask.setProcessId(item.getId());
            empTask.setEmpId(item.getEmpId());
            empTask.setType(1); //负责人 1
            empTask.setState(1); //生成状态1执行中
            empTask.setCount(item.getCount());
            empTask.setProductId(pid);
            empTask.setComponentId(item.getComponentId());

            taskList.add(empTask);
        }

//      2.  查询该产品所有的 审核员 Id 集合
        List<Process> checkList = processMapper.queryCheckListByProductId(pid);
        for (Process item : checkList) {
            EmpTask empTask = new EmpTask();
            empTask.setProcessId(item.getId());
            empTask.setEmpId(item.getEmpId());
            empTask.setType(2); //审核人 2
            empTask.setState(1); //生成状态1执行中
            empTask.setCount(item.getCount());
            empTask.setProductId(pid);
            empTask.setComponentId(item.getComponentId());

            taskList.add(empTask);
        }

//        3.对emptask表进行批量插入
        empTaskMapper.insertList(taskList);

//        4.修改product表，标记该产品的状态
        productMapper.setState(pid,1);

//        5.修改表process的状态    0未开始，1 进行中未完成，2生产完成待审核，3不合格重新生产，4审核完成

        processMapper.updateState(pid,1);


    }
}
