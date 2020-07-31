package com.chain.search.controller;

import com.chain.search.pojo.Article;
import com.chain.search.service.ArticleService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

/**
 * TODO
 *
 * @author chain
 * @date 2020/6/2
 */
@Api("ArticleController")
@RestController
@RequestMapping("/article")
@CrossOrigin
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @ApiOperation("保存文章")
    @PostMapping("save")
    public Result save(@ApiParam("文章") @RequestBody Article article){
        articleService.save(article);
        return new Result(true, StatusCode.OK,"添加成功");
    }


    @ApiOperation("关键字查找")
    @GetMapping("/{key}/{pageNum}/{size}")
    public  Result findByKey(@ApiParam("关键字") @PathVariable String key,
                             @ApiParam("pageNum")@PathVariable int pageNum,
                             @ApiParam("size")@PathVariable int size){
        Page<Article> page= articleService.findByKey(key,pageNum,size);
        return new Result(true,StatusCode.OK,"查询成功",new PageResult<Article>(page.getTotalElements(),page.getContent()));
    }


}
