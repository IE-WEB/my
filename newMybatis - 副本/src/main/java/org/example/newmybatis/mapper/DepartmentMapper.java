package org.example.newmybatis.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.example.newmybatis.entity.Department;

import java.util.List;

public interface DepartmentMapper {

    List<Department> selectAll(Department department);

    @Select("select * from  depttable where id=#{id}")
    Department selectById(Integer id);


    void insert(Department department);

    void updateById(Department department);
@Delete("delete from `depttable` where id=#{id}")
    void delete(Integer id);
@Select("select * from `depttable` where name=#{name}")
    Department selectUsername(String username);
}
