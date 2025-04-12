package org.example.newmybatis.Service;

import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.example.newmybatis.entity.Account;
import org.example.newmybatis.entity.Admin;
import org.example.newmybatis.mapper.AdminMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 通过service并且标注为spring里面的bean
 */
@Service
public class AdminService {

@Resource
private AdminMapper adminMapper;

    public List<Admin> selectAll(Admin admin) {
        List<Admin> list=adminMapper.selectAll(admin);
        return list;
    }

    public Admin selectById(Integer id) {

        return adminMapper.selectById(id);
    }

    public List<Admin> selectlist(Admin admin) {
        System.out.println(admin);
        return null;
    }

    public PageInfo<Admin> selectPage(Admin admin,Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Admin> list=adminMapper.selectAll(admin);
        return PageInfo.of(list);
    }


    public void add(Admin admin)
    {
        String username = admin.getUsername();
        Admin dbAdmin = adminMapper.selectUsername(username);
        if(dbAdmin!= null) {
            throw new RuntimeException("账号已存在！");
        }

        if(StrUtil.isBlank(  admin.getPassword())) {
   admin.setPassword("admin");
       }
        if(StrUtil.isBlank(admin.getName())) {
           admin.setName(username);
       }
       admin.setRole("ADMIN");
        //admin.setNumber(0);
        adminMapper.insert(admin);
    }

    public void deleteById(Integer id) {
        adminMapper.delete(id);
    }

    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            this.deleteById(id);
        }
    }

    public Admin login(Account account) {
        String username = account.getUsername();
        Admin dbAdmin = adminMapper.selectUsername(username);
        if(dbAdmin == null) {
            throw new RuntimeException("用户不存在");
        }
            String password = account.getPassword();
            if(!password.equals(dbAdmin.getPassword())) {
                throw new RuntimeException("密码错误");
            }

        return dbAdmin;

    }
    public void update(Admin admin) {
        adminMapper.updateById(admin);
    }


    public void updatePassword(Account account) {
        Integer id = account.getId();
        Admin admin = this.selectById(id);
        if(!admin.getPassword().equals(account.getPassword())){
                  throw  new RuntimeException("原密码错误");
        }else{
            admin.setPassword(account.getPassword());
            this.update(admin);
        }
    }
}
