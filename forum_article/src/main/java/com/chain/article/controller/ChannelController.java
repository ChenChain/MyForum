package com.chain.article.controller;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.chain.article.pojo.Channel;
import com.chain.article.service.ChannelService;

import entity.PageResult;
import entity.Result;
import entity.StatusCode;
/**
 * 控制器层
 * @author Administrator
 *
 */
@Api("ChannelController")
@RestController
@CrossOrigin
@RequestMapping("/channel")
public class ChannelController {

	@Autowired
	private ChannelService channelService;
	
	
	/**
	 * 查询全部数据
	 * @return
	 */
	@ApiOperation("查询全部数据")
	@RequestMapping(method= RequestMethod.GET)
	public Result findAll(){
		return new Result(true,StatusCode.OK,"查询成功",channelService.findAll());
	}
	
	/**
	 * 根据ID查询
	 * @param id ID
	 * @return
	 */
	@ApiOperation("根据id查询")
	@RequestMapping(value="/{id}",method= RequestMethod.GET)
	public Result findById(@ApiParam("id") @PathVariable String id){
		return new Result(true,StatusCode.OK,"查询成功",channelService.findById(id));
	}


	/**
	 * 分页+多条件查询
	 * @param searchMap 查询条件封装
	 * @param page 页码
	 * @param size 页大小
	 * @return 分页结果
	 */
	@ApiOperation("分页+多条件查询")
	@RequestMapping(value="/search/{page}/{size}",method=RequestMethod.POST)
	public Result findSearch(@RequestBody Map searchMap ,@ApiParam("页码") @PathVariable int page,@ApiParam("页大小") @PathVariable int size){
		Page<Channel> pageList = channelService.findSearch(searchMap, page, size);
		return  new Result(true,StatusCode.OK,"查询成功",  new PageResult<Channel>(pageList.getTotalElements(), pageList.getContent()) );
	}

	/**
     * 根据条件查询
     * @param searchMap
     * @return
     */
	@ApiOperation("根据条件查询")
    @RequestMapping(value="/search",method = RequestMethod.POST)
    public Result findSearch( @RequestBody Map searchMap){
        return new Result(true,StatusCode.OK,"查询成功",channelService.findSearch(searchMap));
    }
	
	/**
	 * 增加
	 * @param channel
	 */
	@ApiOperation("增加channel")
	@RequestMapping(method=RequestMethod.POST)
	public Result add(@RequestBody Channel channel  ){
		channelService.add(channel);
		return new Result(true,StatusCode.OK,"增加成功");
	}
	
	/**
	 * 修改
	 * @param channel
	 */
	@ApiOperation("修改channel")
	@RequestMapping(value="/{id}",method= RequestMethod.PUT)
	public Result update(@RequestBody Channel channel,@ApiParam("id") @PathVariable String id ){
		channel.setId(id);
		channelService.update(channel);		
		return new Result(true,StatusCode.OK,"修改成功");
	}
	
	/**
	 * 删除
	 * @param id
	 */
	@ApiOperation("删除channel")
	@RequestMapping(value="/{id}",method= RequestMethod.DELETE)
	public Result delete(@ApiParam("id") @PathVariable String id ){
		channelService.deleteById(id);
		return new Result(true,StatusCode.OK,"删除成功");
	}
	
}
