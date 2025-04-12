package org.example.newmybatis.controller;

import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.example.newmybatis.Service.EmployeeService;
import org.example.newmybatis.common.Result;
import org.example.newmybatis.entity.Employee;
import org.example.newmybatis.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    /**
     * 为了引入service
     * @return
     */
    @Resource
    private EmployeeService employeeService;
    @Autowired
    private EmployeeMapper employeeMapper;

    /**
     * 查询所有的employ的数据
     * @return
     */

    @GetMapping("/selectAll")
    public Result selectAll(Employee employee) {
        List<Employee> list=employeeService.selectAll(employee );
        return Result.success(list);
    }

    /**
     * 通过路径查询
     * @param id
     * @return
     */

    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id){
           Employee employee=employeeService.selectById(id);
           return Result.success(employee);
    }

    /**
     * 通过url传参
     */
    @GetMapping("/selectByone")
    public Result selectone(@RequestParam Integer id){
        Employee employee=employeeService.selectById(id);
        return Result.success(employee);
    }

    /**
     * get请求对象可以接收多个参数
     *
     * @param employee
     * @return
     */
    @GetMapping("/selectList")
    public Result selectList( Employee employee){
        List<Employee> list=employeeService.selectlist(employee);
        return Result.success(list);
    }

    /**
     * 分页查询数据
     * num；当前页码
     * size:每页个数
     * @param num
     * @return
     */
    @GetMapping("/selectPage")
    public Result selectPage(Employee employee,@RequestParam(defaultValue = "1") Integer num,@RequestParam(defaultValue = "10") Integer size){
        PageInfo<Employee> pageinfo = employeeService.selectPage(employee,num, size);
        return Result.success(pageinfo);

    }



    @PostMapping("/add")
    /**
     * requestbody将前端传来的json字符串映射成Java对象
     */
    public Result add(@RequestBody Employee employee){
        employeeService.add(employee);
        /**
         * 新增数据不需要返回值
         */
        return Result.success();
    }
    /**
     * 更新数据
     */
    @PutMapping("/update")
    public Result update(@RequestBody Employee employee){
        employeeMapper.updateById(employee);
        return Result.success();
    }
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id){
        employeeService.deleteById(id);
        return Result.success();
    }
    @DeleteMapping("/deleteBatch")
    public Result deleteBatch(@RequestBody List<Integer> ids){
        employeeService.deleteBatch(ids);
        return Result.success();
    }

    /**
     * 导出excel
     *
     */

    @GetMapping("/export")
    public void  export(HttpServletResponse response) throws IOException {
        List<Employee> employeeList = employeeService.selectAll(null);
        //构建ExcelWriter
        ExcelWriter writer = ExcelUtil.getWriter(true);
        //设置中文表头
        writer.addHeaderAlias("username","账号");
        writer.addHeaderAlias("name","名称");
        //写数据到writer
        writer.setOnlyAlias(true);
        writer.write(employeeList,true);
        //设置输出文件的名称
        //设置浏览器的响应格式
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        String fileName= URLEncoder.encode("员工信息","UTF-8");
        response.setHeader("Content-disposition","attachment;filename="+fileName+".xlsx");
        ServletOutputStream os = response.getOutputStream();
        writer.flush(os);
        writer.close();
    }
    @PostMapping("import")
    public Result importData(MultipartFile file) throws IOException {
        InputStream inputStream = file.getInputStream();
        ExcelReader reader = ExcelUtil.getReader(inputStream);
        reader.addHeaderAlias("名称","name");
        reader.addHeaderAlias("账号","username");

        List<Employee> employeeList = reader.readAll(Employee.class);
        for (Employee employee : employeeList) {
            employeeService.add(employee);

        }
        return Result.success();
    }
}
