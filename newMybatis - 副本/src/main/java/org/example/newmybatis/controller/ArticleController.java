package org.example.newmybatis.controller;

import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.example.newmybatis.Service.ArticleService;
import org.example.newmybatis.common.Result;
import org.example.newmybatis.entity.Article;
import org.example.newmybatis.mapper.ArticleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/article")
public class ArticleController {
    /**
     * 为了引入service
     * @return
     */
    @Resource
    private ArticleService articleService;
    @Autowired
    private ArticleMapper articleMapper;

    /**
     * 查询所有的employ的数据
     * @return
     */

    @GetMapping("/selectAll")
    public Result selectAll(Article article) {
        List<Article> list=articleService.selectAll(article );
        return Result.success(list);
    }

    /**
     * 通过路径查询
     * @param id
     * @return
     */

    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id){
        Article article=articleService.selectById(id);
        return Result.success(article);
    }

    /**
     * 通过url传参
     */
    @GetMapping("/selectByone")
    public Result selectone(@RequestParam Integer id){
        Article article=articleService.selectById(id);
        return Result.success(article);
    }

    /**
     * get请求对象可以接收多个参数
     *
     * @param article
     * @return
     */
    @GetMapping("/selectList")
    public Result selectList( Article article){
        List<Article> list=articleService.selectlist(article);
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
    public Result selectPage(Article article,@RequestParam(defaultValue = "1") Integer num,@RequestParam(defaultValue = "10") Integer size){
        PageInfo<Article> pageinfo = articleService.selectPage(article,num, size);
        return Result.success(pageinfo);

    }



    @PostMapping("/add")
    /**
     * requestbody将前端传来的json字符串映射成Java对象
     */
    public Result add(@RequestBody Article article){
        articleService.add(article);
        /**
         * 新增数据不需要返回值
         */
        return Result.success();
    }
    /**
     * 更新数据
     */
    @PutMapping("/update")
    public Result update(@RequestBody Article article){
        articleMapper.updateById(article);
        return Result.success();
    }
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id){
        articleService.deleteById(id);
        return Result.success();
    }
    @DeleteMapping("/deleteBatch")
    public Result deleteBatch(@RequestBody List<Integer> ids){
        articleService.deleteBatch(ids);
        return Result.success();
    }
}
