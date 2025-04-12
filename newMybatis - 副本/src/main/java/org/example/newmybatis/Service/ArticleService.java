package org.example.newmybatis.Service;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.example.newmybatis.entity.Account;
import org.example.newmybatis.entity.Article;
import org.example.newmybatis.mapper.ArticleMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 通过service并且标注为spring里面的bean
 */
@Service
public class ArticleService {

@Resource
private ArticleMapper articleMapper;

    public List<Article> selectAll(Article article) {
        List<Article> list=articleMapper.selectAll(article);
        return list;
    }

    public Article selectById(Integer id) {

        return articleMapper.selectById(id);
    }

    public List<Article> selectlist(Article article) {
        System.out.println(article);
        return null;
    }

    public PageInfo<Article> selectPage(Article article,Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Article> list=articleMapper.selectAll(article);
        return PageInfo.of(list);
    }


    public void add(Article article)
    {
        article.setTime(DateUtil.now());
        articleMapper.insert(article);
    }

    public void deleteById(Integer id) {
        articleMapper.delete(id);
    }

    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            this.deleteById(id);
        }
    }


    public Integer selectCountByDate(String date) {
        return articleMapper.selectCountByDate(date);

    }
}
