package com.chain.forum.controller;

import com.chain.forum.pojo.Spit;
import com.chain.forum.service.SpitService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

/**
 * TODO
 *
 * @author chain
 * @date 2020/5/24
 */
@Api("吐槽模块")
@RestController
@RequestMapping("/spit")
@CrossOrigin
public class SpitController {

    @Autowired
    private SpitService spitService;

    @Autowired
    private RedisTemplate redisTemplate;

    @ApiOperation("得到所有吐槽内容")
    @GetMapping
    public Result findAll(){
        return new Result(true, StatusCode.OK,"查询成功",spitService.findAll());
    }

    @GetMapping("/{spitId}")
    @ApiOperation("得到id的吐槽")
    public Result findById(@ApiParam("吐槽id") @PathVariable  String spitId ){
        return new Result(true,StatusCode.OK,"查询成功",spitService.findById(spitId));
    }


    @ApiOperation("保存吐槽内容")
    @PostMapping
    public Result save(@RequestBody Spit spit){
        spitService.save(spit);
        return new Result(true,StatusCode.OK,"保存成功");
    }

    @ApiOperation("更新吐槽内容")
    @PutMapping("/{spitId}")
    public Result update(@PathVariable("spitId")   String id,  @RequestBody Spit spit){
        spit.set_id(id);
        spitService.update(spit);
        return new Result(true,StatusCode.OK,"更新成功");
    }
    @ApiOperation("删除吐槽内容")
    @DeleteMapping(value = "/{spitId}")
    public Result delete(  @PathVariable("spitId") String id){
        spitService.deleteById(id);
        return new Result(true,StatusCode.OK,"删除成功");
    }

    @ApiOperation("分页查询子吐槽内容")
    @GetMapping("/comment/{pId}/{page}/{size}")
    public  Result findByParentId(@PathVariable String pId, @PathVariable int page, @PathVariable int size){
        Page<Spit> p=spitService.findByParentId(pId, page, size);
        return new Result(true,StatusCode.OK,"查询成功",new PageResult<Spit>(p.getTotalElements(),p.getContent()));
    }

    @PutMapping("/thumbup/{spitId}")
    @ApiOperation("给吐槽点赞")
    public Result thumbUp(@PathVariable String spitId){
        //判断当前用户是否已经点过赞
        String userid="111";

         if (  redisTemplate.opsForValue().get("thumbup_"+userid)!=null){
             return new Result(false,StatusCode.REPERROR,"不能重复点赞");
         }

         redisTemplate.opsForValue().set("thumbup_"+userid,userid);
        spitService.thumbup(spitId);
        return new Result(true,StatusCode.OK,"点赞成功");
    }




}
