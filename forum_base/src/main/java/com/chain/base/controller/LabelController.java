package com.chain.base.controller;

import com.chain.base.pojo.Label;
import com.chain.base.service.LabelService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * TODO
 *
 * @author chain
 * @date 2020/5/16
 */
@CrossOrigin
@RestController
@RequestMapping("/label")
public class LabelController {

    @Autowired
    private LabelService labelService;

    @GetMapping
    public Result findAll(){
        return new Result(true, StatusCode.OK, "查询成功",labelService.findAll());
    }

    @GetMapping("/find/{labelId}")
    public Result findById(@PathVariable("labelId") String labelId){
        return new Result(true, StatusCode.OK, "查询成功",labelService.findById(labelId));

    }

    @PostMapping
    public Result save(@RequestBody Label label){
        labelService.save(label);
        return new Result(true, StatusCode.OK, "tianjia成功");

    }

    @PostMapping("/update/{labelId}")
    public Result update(@RequestBody Label label, @PathVariable("labelId") String labelId){
        label.setId(labelId);
        labelService.update(label);
        return new Result(true, StatusCode.OK, "gengxin成功");

    }

    @GetMapping("/delete/{labelId}")
    public Result deleteById(@RequestBody Label label,@PathVariable("labelId") String labelId){
       labelService.deleteById(labelId);
        return new Result(true, StatusCode.OK, "shanchu成功");
    }

    /**
     * 条件查询 label为条件 查出相似的
     * @return
     */
    @PostMapping("/search")
    public Result findSearch(@RequestBody Label label){
        List<Label> list= labelService.findSearch(label);
        return new Result(true,StatusCode.OK,"查询成功",list);
    }

    /**
     * 条件查询 并且分页
     * @param label
     * @return
     */
    @PostMapping("/search/{page}/{size}")
    public Result pageQuery(@RequestBody Label label, @PathVariable int page ,@PathVariable int size){
        Page<Label> pageData = labelService.pageQuery(label,page,size);
        return new Result(true,StatusCode.OK,"查询成功",new PageResult<Label>(pageData.getTotalElements(),pageData.getContent()));
    }



}
