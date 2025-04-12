package org.example.newmybatis.Service;

import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.example.newmybatis.entity.Account;
import org.example.newmybatis.entity.Admin;
import org.example.newmybatis.entity.Employee;
import org.example.newmybatis.exception.GlobalExceptionHandler;
import org.example.newmybatis.mapper.EmployeeMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 通过service并且标注为spring里面的bean
 */
@Service
public class EmployeeService {

@Resource
private EmployeeMapper employeeMapper;

    public List<Employee> selectAll(Employee employee) {
        List<Employee> list=employeeMapper.selectAll(employee);
        return list;
    }

    public Employee selectById(Integer id) {

        return employeeMapper.selectById(id);
    }

    public List<Employee> selectlist(Employee employee) {
        System.out.println(employee);
        return null;
    }

    public PageInfo<Employee> selectPage(Employee employee,Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Employee> list=employeeMapper.selectAll(employee);
        return PageInfo.of(list);
    }


    public void add(Employee employee)
    {
        String username = employee.getUsername();
        Employee dbEmployee = employeeMapper.selectUsername(username);
        if(dbEmployee!= null) {
            throw new RuntimeException("账号已存在！");
        }

        if(StrUtil.isBlank(  employee.getPassword())) {
   employee.setPassword("123");
       }
        if(StrUtil.isBlank(employee.getName())) {
           employee.setName(username);
       }
       employee.setRole("EMP");
        //employee.setNumber(0);
        employeeMapper.insert(employee);
    }

    public void deleteById(Integer id) {
        employeeMapper.delete(id);
    }

    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            this.deleteById(id);
        }
    }

    public Employee login(Account account) {
        String username = account.getUsername();
        Employee dbEmployee = employeeMapper.selectUsername(username);
        if(dbEmployee == null) {
            throw new RuntimeException("用户不存在");
        }
            String password = account.getPassword();
            if(!password.equals(dbEmployee.getPassword())) {
                throw new RuntimeException("密码错误");
            }

        return dbEmployee;

    }

    public void register(Employee employee) {
        this.add(employee);
    }
    public void update(Employee employee) {
        employeeMapper.updateById(employee);
    }


    public void updatePassword(Account account) {
        Integer id = account.getId();
       Employee employee = this.selectById(id);
        if(!employee.getPassword().equals(account.getPassword())){
            throw  new RuntimeException("原密码错误");
        }else{
            employee.setPassword(account.getPassword());
            this.update(employee);
        }
    }
}
