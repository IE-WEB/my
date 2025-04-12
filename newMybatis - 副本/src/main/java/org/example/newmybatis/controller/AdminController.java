package org.example.newmybatis.controller;

import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.example.newmybatis.Service.AdminService;
import org.example.newmybatis.common.Result;
import org.example.newmybatis.entity.Admin;
import org.example.newmybatis.mapper.AdminMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    /**
     * 为了引入service
     * @return
     */
    @Resource
    private AdminService adminService;
    @Autowired
    private AdminMapper adminMapper;

    /**
     * 查询所有的employ的数据
     * @return
     */

    @GetMapping("/selectAll")
    public Result selectAll(Admin admin) {
        List<Admin> list=adminService.selectAll(admin );
        return Result.success(list);
    }

    /**
     * 通过路径查询
     * @param id
     * @return
     */

    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id){
           Admin admin=adminService.selectById(id);
           return Result.success(admin);
    }

    /**
     * 通过url传参
     */
    @GetMapping("/selectByone")
    public Result selectone(@RequestParam Integer id){
        Admin admin=adminService.selectById(id);
        return Result.success(admin);
    }

    /**
     * get请求对象可以接收多个参数
     *
     * @param admin
     * @return
     */
    @GetMapping("/selectList")
    public Result selectList( Admin admin){
        List<Admin> list=adminService.selectlist(admin);
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
    public Result selectPage(Admin admin,@RequestParam(defaultValue = "1") Integer num,@RequestParam(defaultValue = "10") Integer size){
        PageInfo<Admin> pageinfo = adminService.selectPage(admin,num, size);
        return Result.success(pageinfo);

    }



    @PostMapping("/add")
    /**
     * requestbody将前端传来的json字符串映射成Java对象
     */
    public Result add(@RequestBody Admin admin){
        adminService.add(admin);
        /**
         * 新增数据不需要返回值
         */
        return Result.success();
    }
    /**
     * 更新数据
     */
    @PutMapping("/update")
    public Result update(@RequestBody Admin admin){
        adminMapper.updateById(admin);
        return Result.success();
    }
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id){
        adminService.deleteById(id);
        return Result.success();
    }
    @DeleteMapping("/deleteBatch")
    public Result deleteBatch(@RequestBody List<Integer> ids){
        adminService.deleteBatch(ids);
        return Result.success();
    }
}
