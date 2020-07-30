package com.chain.gathering.controller;
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

import com.chain.gathering.pojo.Gathering;
import com.chain.gathering.service.GatheringService;

import entity.PageResult;
import entity.Result;
import entity.StatusCode;
/**
 * 控制器层
 * @author Administrator
 *
 */
@Api("GatheringController")
@RestController
@CrossOrigin
@RequestMapping("/gathering")
public class GatheringController {

	@Autowired
	private GatheringService gatheringService;
	
	
	/**
	 * 查询全部数据
	 * @return
	 */
	@ApiOperation("查询全部数据")
	@RequestMapping(method= RequestMethod.GET)
	public Result findAll(){
		return new Result(true,StatusCode.OK,"查询成功",gatheringService.findAll());
	}
	
	/**
	 * 根据ID查询
	 * @param id ID
	 * @return
	 */
	@ApiOperation("根据id查询")
	@RequestMapping(value="/{id}",method= RequestMethod.GET)
	public Result findById(@ApiParam("id") @PathVariable String id){
		return new Result(true,StatusCode.OK,"查询成功",gatheringService.findById(id));
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
		Page<Gathering> pageList = gatheringService.findSearch(searchMap, page, size);
		return  new Result(true,StatusCode.OK,"查询成功",  new PageResult<Gathering>(pageList.getTotalElements(), pageList.getContent()) );
	}

	/**
     * 根据条件查询
     * @param searchMap
     * @return
     */
	@ApiOperation("根据条件查询")
    @RequestMapping(value="/search",method = RequestMethod.POST)
    public Result findSearch( @RequestBody Map searchMap){
        return new Result(true,StatusCode.OK,"查询成功",gatheringService.findSearch(searchMap));
    }
	
	/**
	 * 增加
	 * @param gathering
	 */
	@ApiOperation("增加gathering")
	@RequestMapping(method=RequestMethod.POST)
	public Result add(@RequestBody Gathering gathering  ){
		gatheringService.add(gathering);
		return new Result(true,StatusCode.OK,"增加成功");
	}
	
	/**
	 * 修改
	 * @param gathering
	 */
	@ApiOperation("修改gathering")
	@RequestMapping(value="/{id}",method= RequestMethod.PUT)
	public Result update(@RequestBody Gathering gathering,@ApiParam("id") @PathVariable String id ){
		gathering.setId(id);
		gatheringService.update(gathering);		
		return new Result(true,StatusCode.OK,"修改成功");
	}
	
	/**
	 * 删除
	 * @param id
	 */
	@ApiOperation("删除gathering")
	@RequestMapping(value="/{id}",method= RequestMethod.DELETE)
	public Result delete(@ApiParam("id") @PathVariable String id ){
		gatheringService.deleteById(id);
		return new Result(true,StatusCode.OK,"删除成功");
	}
	
}
