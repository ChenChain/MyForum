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
@Api("标签controller")
@CrossOrigin
@RestController
@RequestMapping("/label")
public class LabelController {

    @Autowired
    private LabelService labelService;

    @ApiOperation("查找全部")
    @GetMapping
    public Result findAll(){
        return new Result(true, StatusCode.OK, "查询成功",labelService.findAll());
    }

    @ApiOperation("通过标签id查找")
    @GetMapping("/find/{labelId}")
    public Result findById(@ApiParam("标签id") @PathVariable("labelId") String labelId){
        return new Result(true, StatusCode.OK, "查询成功",labelService.findById(labelId));

    }

    @ApiOperation("保存label")
    @PostMapping
    public Result save(@RequestBody Label label){
        labelService.save(label);
        return new Result(true, StatusCode.OK, "tianjia成功");

    }

    @ApiOperation("更新label")
    @PostMapping("/update/{labelId}")
    public Result update(@RequestBody Label label,@ApiParam("标签id") @PathVariable("labelId") String labelId){
        label.setId(labelId);
        labelService.update(label);
        return new Result(true, StatusCode.OK, "gengxin成功");

    }

    @ApiOperation("删除label")
    @GetMapping("/delete/{labelId}")
    public Result deleteById(@RequestBody Label label,@ApiParam("标签id") @PathVariable("labelId") String labelId){
       labelService.deleteById(labelId);
        return new Result(true, StatusCode.OK, "shanchu成功");
    }

    /**
     * 条件查询 label为条件 查出相似的
     * @return
     */
    @ApiOperation("以label为条件查出相似")
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
    @ApiOperation("条件查询 并且分页")
    @PostMapping("/search/{page}/{size}")
    public Result pageQuery(@RequestBody Label label,@ApiParam("页码") @PathVariable int page ,@ApiParam("页大小") @PathVariable int size){
        Page<Label> pageData = labelService.pageQuery(label,page,size);
        return new Result(true,StatusCode.OK,"查询成功",new PageResult<Label>(pageData.getTotalElements(),pageData.getContent()));
    }



}
