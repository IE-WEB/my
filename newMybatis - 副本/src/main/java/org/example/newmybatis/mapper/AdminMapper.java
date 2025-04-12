package org.example.newmybatis.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.example.newmybatis.entity.Admin;

import java.util.List;

public interface AdminMapper {

    List<Admin> selectAll(Admin admin);

    @Select("select * from admin where id=#{id}")
    Admin selectById(Integer id);


    void insert(Admin admin);

    void updateById(Admin admin);
@Delete("delete from `admin` where id=#{id}")
    void delete(Integer id);
@Select("select * from `admin` where username=#{username}")
    Admin selectUsername(String username);
}
