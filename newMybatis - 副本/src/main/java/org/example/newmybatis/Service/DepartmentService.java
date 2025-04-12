package org.example.newmybatis.Service;

import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.example.newmybatis.entity.Account;
import org.example.newmybatis.entity.Department;
import org.example.newmybatis.mapper.DepartmentMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 通过service并且标注为spring里面的bean
 */
@Service
public class DepartmentService {

@Resource
private DepartmentMapper departmentMapper;

    public List<Department> selectAll(Department department) {
        List<Department>  list=departmentMapper.selectAll(department);
        return list;
    }

    public Department selectById(Integer id) {

        return departmentMapper.selectById(id);
    }

    public List<Department> selectlist(Department department) {
        System.out.println(department);
        return null;
    }

    public PageInfo<Department> selectPage(Department department,Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Department> list=departmentMapper.selectAll(department);
        return PageInfo.of(list);
    }


    public void add(Department department)
    {

        departmentMapper.insert(department);
    }

    public void deleteById(Integer id) {
        departmentMapper.delete(id);
    }

    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            this.deleteById(id);
        }
    }



    public void register(Department department) {
        this.add(department);
    }




}
