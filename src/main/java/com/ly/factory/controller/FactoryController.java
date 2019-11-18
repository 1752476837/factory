package com.ly.factory.controller;

import com.ly.factory.domain.Employee;
import com.ly.factory.domain.Process;
import com.ly.factory.domain.ProductVo;
import com.ly.factory.domain.Products;
import com.ly.factory.domain.RetDomain.LingjianLiucheng;
import com.ly.factory.service.EmployeeService;
import com.ly.factory.service.ProcessService;
import com.ly.factory.service.ProductService;
import com.ly.factory.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

/**
 * @author Tarry
 * @create 2019/10/18 14:56
 */
@CrossOrigin   //解决跨域的注解
@RestController
@RequestMapping("factory")
public class FactoryController {

    @Autowired
    ProductService productService;

    @Autowired
    ProcessService processService;
    @Autowired
    EmployeeService employeeService;

    @PostMapping("saveProduct")
    public ResponseEntity<String> getProduct(@RequestBody ProductVo productvo){



        List<HashMap<String, String>> params = productvo.getParams();


        System.out.println(productvo.getParams());
        String json = JsonUtils.serialize(params);
        Products products = new Products();
        products.setId(productvo.getId());
        products.setTitle(productvo.getTitle());
        products.setParam(json);

        System.out.println(products);

        return null;
    }
    @GetMapping("getProduct")
    public ResponseEntity<Products> getProduct(Long id){
        Products product = productService.getProduct(id);
        System.out.println(product);


        return ResponseEntity.ok(product);
    }

    public ResponseEntity<List<Employee>> getEmp(String productId){

        return null;
    }

    @PostMapping("saveList")
    public void saveProductList(@RequestBody Process process){
        System.out.println(process);

    }

//    @PostMapping("save")
//    public ResponseEntity saveProduct(@RequestBody LingjianLiucheng liucheng){
//        //String strParams = JsonUtils.serialize(liucheng.getParams());
//        System.out.println(liucheng);
//        Process process=new Process();
//        process.setId(liucheng.getId());
//        process.setOrgType(liucheng.getOrgType());
//        process.setDutyState(liucheng.getDutyState());
//        process.setCheckState(liucheng.getCheckState());
//        process.setParentId(liucheng.getParentId());
//        process.setParams(liucheng.getParams().toString());
//        process.setParamState(liucheng.getParamState());
//        processService.saveProcess(process);
//        return ResponseEntity.ok("保存成功");
//    }

//    @GetMapping("getProcess")
//    public ResponseEntity<List<Process>> getProcessList(String pid,Long productId){
//        //List<Process> list = processService.getProcessByPidAndProductId(pid, productId);
//        return ResponseEntity.ok(list);
//    }


    //获取负责人与审核人
    @GetMapping("getEmployees")
    public ResponseEntity<List<Employee>> getEmployee(){
        List<Employee> lists=employeeService.getEmployees();
        return ResponseEntity.ok(lists);
    }

//    //h获取产品流程线
//    @GetMapping("getProcess/{parent}")
//    private List<Process> getTree(@PathVariable("parent") String parent){
//
//        String next=processService.getProcessByParent(parent).getParentId();
//        System.out.println("controlllling"+next);
//        List<Process> pross=new ArrayList<Process>();
//        while(!next.trim().equals("0")){
//            Process p=processService.getProcessByParent(next);
//            pross.add(p);
//            next=processService.getProcessByParent(next).getParentId();
//        }
//        System.out.println(pross);
//        return pross;
//
//    }


    //注册
    @GetMapping("reg")
    public ResponseEntity register(@RequestBody Employee employee){
        return ResponseEntity.ok(employeeService.addEmployee(employee));
    }

    //登录
    @ResponseBody
    @PostMapping("login")
    public ResponseEntity login(@ModelAttribute Employee employee, HttpSession httpSession){


        System.out.println(employee.getName()+employee.getPassword());
        int ret=employeeService.loginService(employee);
        if(ret>=0){
            httpSession.setAttribute("username",employee.getName());
            return ResponseEntity.ok("登陆成功");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("用户名或密码错误");

    }





}
