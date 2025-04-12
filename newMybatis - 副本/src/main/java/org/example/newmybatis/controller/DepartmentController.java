package org.example.newmybatis.controller;

import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.example.newmybatis.Service.DepartmentService;
import org.example.newmybatis.common.Result;
import org.example.newmybatis.entity.Department;
import org.example.newmybatis.mapper.DepartmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/department")
public class DepartmentController {
    /**
     * 为了引入service
     * @return
     */
    @Resource
    private DepartmentService departmentService;
    @Autowired
    private DepartmentMapper departmentMapper;

    /**
     * 查询所有的employ的数据
     * @return
     */

    @GetMapping("/selectAll")
    public Result selectAll(Department department) {
        List<Department> list=departmentService.selectAll(department );
        return Result.success(list);
    }

    /**
     * 通过路径查询
     * @param id
     * @return
     */

    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id){
           Department department=departmentService.selectById(id);
           return Result.success(department);
    }

    /**
     * 通过url传参
     */
    @GetMapping("/selectByone")
    public Result selectone(@RequestParam Integer id){
        Department department=departmentService.selectById(id);
        return Result.success(department);
    }

    /**
     * get请求对象可以接收多个参数
     *
     * @param department
     * @return
     */
    @GetMapping("/selectList")
    public Result selectList( Department department){
        List<Department> list=departmentService.selectlist(department);
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
    public Result selectPage(Department department,@RequestParam(defaultValue = "1") Integer num,@RequestParam(defaultValue = "10") Integer size){
        PageInfo<Department> pageinfo = departmentService.selectPage(department,num, size);
        return Result.success(pageinfo);

    }



    @PostMapping("/add")
    /**
     * requestbody将前端传来的json字符串映射成Java对象
     */
    public Result add(@RequestBody Department department){
        departmentService.add(department);
        /**
         * 新增数据不需要返回值
         */
        return Result.success();
    }
    /**
     * 更新数据
     */
    @PutMapping("/update")
    public Result update(@RequestBody Department department){
        departmentMapper.updateById(department);
        return Result.success();
    }
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id){
        departmentService.deleteById(id);
        return Result.success();
    }
    @DeleteMapping("/deleteBatch")
    public Result deleteBatch(@RequestBody List<Integer> ids){
        departmentService.deleteBatch(ids);
        return Result.success();
    }
}
