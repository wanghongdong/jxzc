//package com.jxzc.web.web;
//
//import com.alibaba.druid.support.json.JSONUtils;
//import com.jxzc.web.dao.EmployeeRepository;
//import com.jxzc.web.entity.Employee;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//
///**
// * @author whd
// */
//@Controller
//public class EmployeeController {
//
//    @Autowired
//    private EmployeeRepository employeeRepository;
//
//    /**
//     * 添加
//     * @return
//     */
//    @RequestMapping("/es/add")
//    @ResponseBody
//    public String add() {
//        Employee employee = new Employee();
//        employee.setId("1");
//        employee.setFirstName("xuxu");
//        employee.setLastName("zh");
//        employee.setAge(26);
//        employee.setAbout("i am in peking");
//        employeeRepository.save(employee);
//        System.err.println("add a obj");
//        return "success";
//    }
//
//    /**
//     * 删除
//     * @return
//     */
//    @RequestMapping("/delete")
//    public String delete() {
//        Employee employee = employeeRepository.queryEmployeeById("1");
//        employeeRepository.delete(employee);
//        return "success";
//    }
//
//    /**
//     * 局部更新
//     * @return
//     */
//    @RequestMapping("update")
//    public String update() {
//        Employee employee = employeeRepository.queryEmployeeById("1");
//        employee.setFirstName("哈哈");
//        employeeRepository.save(employee);
//        System.err.println("update a obj");
//        return "success";
//    }
//    /**
//     * 查询
//     * @return
//     */
//    @RequestMapping("query")
//    public Employee query() {
//        Employee accountInfo = employeeRepository.queryEmployeeById("1");
//        System.err.println(JSONUtils.toJSONString(accountInfo));
//        return accountInfo;
//    }
//}