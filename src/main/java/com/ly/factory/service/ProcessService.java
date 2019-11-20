package com.ly.factory.service;

import com.ly.factory.domain.EmpTask;
import com.ly.factory.domain.Process;
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
        List<Integer> dutyList = processMapper.queryDutyListByProductId(pid);

//      2.  查询该产品所有的 审核员 Id 集合
        List<Integer> checkList = processMapper.queryCheckListByProductId(pid);

//      3. 将任务数据，放到同一个 taskList中
//         两个list合并成一个
        dutyList.addAll(checkList);
        List<EmpTask> taskList = new ArrayList<>();
        for (Integer empId : dutyList) {
            EmpTask empTask = new EmpTask();
            empTask.setEmpId(empId);
            empTask.setProcessId(pid);
            empTask.setState(0);
            taskList.add(empTask);
        }

//        4.对emptask表进行批量插入
        empTaskMapper.insertList(taskList);

//        5.修改product表，标记该产品的状态
        productMapper.setState(pid,1);


    }
}
