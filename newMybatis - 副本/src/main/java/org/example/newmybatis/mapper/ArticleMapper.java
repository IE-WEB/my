package org.example.newmybatis.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.example.newmybatis.entity.Article;

import java.util.List;

public interface ArticleMapper {

    List<Article> selectAll(Article article);

    @Select("select * from article where id=#{id}")
    Article selectById(Integer id);


    void insert(Article article);

    void updateById(Article article);
@Delete("delete from `article` where id=#{id}")
    void delete(Integer id);
    @Select("select count(*) from `article` where time like '%${date}%'")
    Integer selectCountByDate(String date);

}
