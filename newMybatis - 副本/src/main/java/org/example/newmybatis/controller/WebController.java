package org.example.newmybatis.controller;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import jakarta.annotation.Resource;
import org.example.newmybatis.Service.AdminService;
import org.example.newmybatis.Service.ArticleService;
import org.example.newmybatis.Service.EmployeeService;
import org.example.newmybatis.common.Result;
import org.example.newmybatis.entity.Account;
import org.example.newmybatis.entity.Admin;
import org.example.newmybatis.entity.Employee;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 对外提供接口，返回格式为json
 */
@RestController
public class WebController {
    /**
     * get请求，返回数据
     * @return
     */
    @Resource
    private EmployeeService employeeService;

    @Resource
    private AdminService adminService;

    @Resource
    private ArticleService articleService;

    @GetMapping("/hello")
    public Result hello() {
        return Result.success("hello");
    }

    @PostMapping("/login")
    public Result login(@RequestBody Account account) {
        System.out.println(account.getClass());
        Account result=null;
        if("ADMIN".equals(account.getRole())) {
            result=adminService.login(account);
        }else if("EMP".equals(account.getRole())) {
            result=employeeService.login(account);
        }
        System.out.println(result);
        return Result.success(result);
    }
    @PostMapping("/register")
    public Result register(@RequestBody Employee employee){
         employeeService.register(employee);
        return Result.success();

    }
    @PutMapping("/updatePassword")
    public Result uddatePassword(@RequestBody Account account){
        if("ADMIN".equals(account.getRole())) {
            adminService.updatePassword(account);
        }else if("EMP".equals(account.getRole())) {
           employeeService.updatePassword(account);
        }
        return Result.success();
    }
    @GetMapping("/barData")
    public Result barData(){
        Map<String,Object> map=new HashMap<>();
        List<Employee> employeeList = employeeService.selectAll(null);
        Set<String> collect = employeeList.stream().map(Employee::getDepartmentName).collect(Collectors.toSet());
        map.put("department",collect);
       List<Long> countList= new ArrayList<>();
        for (String departmentName : collect) {
            long count=employeeList.stream().filter(employee -> employee.getDepartmentName().equals(departmentName)).count();
            countList.add(count);
        }
        map.put("count",countList);
        return Result.success(map);
    }
    @GetMapping("/lineData")
    public Result lineData(){
        Map<String,Object> map=new HashMap<>();
        Date date = new Date();
        DateTime start = DateUtil.offsetDay(date, -7);
        List<DateTime> dateTimes = DateUtil.rangeToList(start, date, DateField.DAY_OF_YEAR);
        List<String> dateStrList = dateTimes.stream().map(dateTime -> DateUtil.format(dateTime, "yyyy-MM-dd")).sorted(Comparator.naturalOrder()).collect(Collectors.toList());
        map.put("date",dateStrList);
        List<Integer> countList= new ArrayList<>();
        for (DateTime dateTime : dateTimes) {
            String dayFormat = DateUtil.formatDate(dateTime);
            System.out.println(dayFormat);
            Integer count = articleService.selectCountByDate(dayFormat);
            countList.add(count);

        }
        map.put("count",countList);
        return Result.success(map);
    }
}
