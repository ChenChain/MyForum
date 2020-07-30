package com.chain.recruit.controller;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import com.chain.recruit.pojo.Enterprise;
import com.chain.recruit.service.EnterpriseService;

import entity.PageResult;
import entity.Result;
import entity.StatusCode;
/**
 * 控制器层
 * @author Administrator
 *
 */
@Api("EnterpriseController")
@RestController
@CrossOrigin
@RequestMapping("/enterprise")
public class EnterpriseController {

	@Autowired
	private EnterpriseService enterpriseService;


	/**
	 * 热门数据
	 * @return
	 */
	@ApiOperation("热门数据")
	@GetMapping("/search/hotlist")
	public Result hotlist(){
		List<Enterprise> list=enterpriseService.hotList("1");
		return new Result(true,StatusCode.OK,"查询成功",list);
	}

	/**
	 * 查询全部数据
	 * @return
	 */
	@ApiOperation("查询全部数据")
	@RequestMapping(method= RequestMethod.GET)
	public Result findAll(){
		return new Result(true,StatusCode.OK,"查询成功",enterpriseService.findAll());
	}
	
	/**
	 * 根据ID查询
	 * @param id ID
	 * @return
	 */
	@ApiOperation("根据id查询")
	@RequestMapping(value="/{id}",method= RequestMethod.GET)
	public Result findById(@ApiParam("id") @PathVariable String id){
		return new Result(true,StatusCode.OK,"查询成功",enterpriseService.findById(id));
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
		Page<Enterprise> pageList = enterpriseService.findSearch(searchMap, page, size);
		return  new Result(true,StatusCode.OK,"查询成功",  new PageResult<Enterprise>(pageList.getTotalElements(), pageList.getContent()) );
	}

	/**
     * 根据条件查询
     * @param searchMap
     * @return
     */
	@ApiOperation("根据条件查询")
    @RequestMapping(value="/search",method = RequestMethod.POST)
    public Result findSearch( @RequestBody Map searchMap){
        return new Result(true,StatusCode.OK,"查询成功",enterpriseService.findSearch(searchMap));
    }
	
	/**
	 * 增加
	 * @param enterprise
	 */
	@ApiOperation("增加enterprise")
	@RequestMapping(method=RequestMethod.POST)
	public Result add(@RequestBody Enterprise enterprise  ){
		enterpriseService.add(enterprise);
		return new Result(true,StatusCode.OK,"增加成功");
	}
	
	/**
	 * 修改
	 * @param enterprise
	 */
	@ApiOperation("修改enterprise")
	@RequestMapping(value="/{id}",method= RequestMethod.PUT)
	public Result update(@RequestBody Enterprise enterprise,@ApiParam("id") @PathVariable String id ){
		enterprise.setId(id);
		enterpriseService.update(enterprise);		
		return new Result(true,StatusCode.OK,"修改成功");
	}
	
	/**
	 * 删除
	 * @param id
	 */
	@ApiOperation("删除enterprise")
	@RequestMapping(value="/{id}",method= RequestMethod.DELETE)
	public Result delete(@ApiParam("id") @PathVariable String id ){
		enterpriseService.deleteById(id);
		return new Result(true,StatusCode.OK,"删除成功");
	}
	
}
