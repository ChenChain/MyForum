package com.chain.qa.controller;
import java.util.List;
import java.util.Map;

import com.chain.qa.client.BaseClient;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import com.chain.qa.pojo.Problem;
import com.chain.qa.service.ProblemService;

import entity.PageResult;
import entity.Result;
import entity.StatusCode;

import javax.servlet.http.HttpServletRequest;

/**
 * 控制器层
 * @author Administrator
 *
 */
@Api("ProblemController")
@RestController
@CrossOrigin
@RequestMapping("/problem")
public class ProblemController {

	@Autowired
	private HttpServletRequest httpServletRequest;

	@Autowired
	private ProblemService problemService;

	@Autowired
	private BaseClient baseClient;

	/**
	 * 测试微服务调用方法
	 * @param labelId
	 * @return
	 */
	@ApiOperation("测试微服务调用方法")
	@GetMapping("/find/{labelId}")
	public Result findByIdz(@ApiParam("标签id") @PathVariable("labelId") String labelId){
		return  baseClient.findById(labelId);
	}


	/**
	 * 问题的最新回答
	 * @param labelId
	 * @param page
	 * @param size
	 * @return
	 */
	@ApiOperation("问题的最新回答")
	@GetMapping("/newlist/{labelId}/{page}/{size}")
	public Result newList(@ApiParam("标签id") @PathVariable String labelId,@ApiParam("页码") @PathVariable int page,@ApiParam("页大小") @PathVariable int size) {
		Page<Problem> pageData=problemService.newlist(labelId,page,size);
		return new Result(true,StatusCode.OK,"查询成功",new PageResult<Problem>(pageData.getTotalElements(),pageData.getContent()));
	}

	@ApiOperation("问题的最热回答")
	@GetMapping("/hostlist/{labelId}/{page}/{size}")
	public Result hotList(@ApiParam("标签id") @PathVariable String labelId,@ApiParam("页码") @PathVariable int page,@ApiParam("页大小") @PathVariable int size) {
		Page<Problem> pageData=problemService.hotlist(labelId,page,size);
		return new Result(true,StatusCode.OK,"查询成功",new PageResult<Problem>(pageData.getTotalElements(),pageData.getContent()));
	}

	@ApiOperation("等待回答")
	@GetMapping("/waitlist/{labelId}/{page}/{size}")
	public Result waitList(@ApiParam("标签id") @PathVariable String labelId,@ApiParam("页码") @PathVariable int page,@ApiParam("页大小") @PathVariable int size) {
		Page<Problem> pageData=problemService.waitlist(labelId,page,size);
		return new Result(true,StatusCode.OK,"查询成功",new PageResult<Problem>(pageData.getTotalElements(),pageData.getContent()));
	}



	/**
	 * 查询全部数据
	 * @return
	 */
	@ApiOperation("查询全部数据")
	@RequestMapping(method= RequestMethod.GET)
	public Result findAll(){
		return new Result(true,StatusCode.OK,"查询成功",problemService.findAll());
	}
	
	/**
	 * 根据ID查询
	 * @param id ID
	 * @return
	 */
	@ApiOperation("根据id查询")
	@RequestMapping(value="/{id}",method= RequestMethod.GET)
	public Result findById(@ApiParam("id") @PathVariable String id){
		return new Result(true,StatusCode.OK,"查询成功",problemService.findById(id));
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
		Page<Problem> pageList = problemService.findSearch(searchMap, page, size);
		return  new Result(true,StatusCode.OK,"查询成功",  new PageResult<Problem>(pageList.getTotalElements(), pageList.getContent()) );
	}

	/**
     * 根据条件查询
     * @param searchMap
     * @return
     */
	@ApiOperation("根据条件查询")
    @RequestMapping(value="/search",method = RequestMethod.POST)
    public Result findSearch( @RequestBody Map searchMap){
        return new Result(true,StatusCode.OK,"查询成功",problemService.findSearch(searchMap));
    }
	
	/**
	 * 增加
	 * @param problem
	 */
	@ApiOperation("增加问题")
	@RequestMapping(method=RequestMethod.POST)
	public Result add(@RequestBody Problem problem  ){
		String token= (String) httpServletRequest.getAttribute("claims_user");
		if (token==null||token.equals("")){
			return new Result(false,StatusCode.ACCESSERROR,"权限不足");
		}
		problemService.add(problem);
		return new Result(true,StatusCode.OK,"增加成功");
	}
	
	/**
	 * 修改
	 * @param problem
	 */
	@ApiOperation("修改问题")
	@RequestMapping(value="/{id}",method= RequestMethod.PUT)
	public Result update(@RequestBody Problem problem,@ApiParam("id") @PathVariable String id ){
		problem.setId(id);
		problemService.update(problem);		
		return new Result(true,StatusCode.OK,"修改成功");
	}
	
	/**
	 * 删除
	 * @param id
	 */
	@ApiOperation("删除问题")
	@RequestMapping(value="/{id}",method= RequestMethod.DELETE)
	public Result delete(@ApiParam("id") @PathVariable String id ){
		problemService.deleteById(id);
		return new Result(true,StatusCode.OK,"删除成功");
	}
	
}
