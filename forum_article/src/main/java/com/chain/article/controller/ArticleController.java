package com.chain.article.controller;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import com.chain.article.pojo.Article;
import com.chain.article.service.ArticleService;

import entity.PageResult;
import entity.Result;
import entity.StatusCode;
/**
 * 控制器层
 * @author Administrator
 *
 */
@Api("文章controller")
@RestController
@CrossOrigin
@RequestMapping("/article")
public class ArticleController {

	@Autowired
	private ArticleService articleService;


	/**
	 * 文章审核
	 * @return
	 */
	@ApiOperation("文章审核")
	@PutMapping("/examine/{articleId}")
	public Result examine(@ApiParam("文章id") @PathVariable String articleId){
		articleService.updateState(articleId);
		return new Result(true,StatusCode.OK,"审核成功");
	}

	/**
	 * 点赞
	 */
	@ApiOperation("点赞")
	@PutMapping("/thumbUp/{articleId}")
	public Result thumbUp(@ApiParam("文章id") @PathVariable String articleId){
		articleService.addThumbUp(articleId);
		return new Result(true,StatusCode.OK,"点赞成功");
	}


	/**
	 * 查询全部数据
	 * @return
	 */
	@ApiOperation("查询全部数据")
	@RequestMapping(method= RequestMethod.GET)
	public Result findAll(){
		return new Result(true,StatusCode.OK,"查询成功",articleService.findAll());
	}
	
	/**
	 * 根据ID查询
	 * @param id ID
	 * @return
	 */
	@ApiOperation("根据id查询")
	@RequestMapping(value="/{id}",method= RequestMethod.GET)
	public Result findById(@ApiParam("id") @PathVariable String id){
		return new Result(true,StatusCode.OK,"查询成功",articleService.findById(id));
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
		Page<Article> pageList = articleService.findSearch(searchMap, page, size);
		return  new Result(true,StatusCode.OK,"查询成功",  new PageResult<Article>(pageList.getTotalElements(), pageList.getContent()) );
	}

	/**
     * 根据条件查询
     * @param searchMap
     * @return
     */
	@ApiOperation("根据条件查询")
    @RequestMapping(value="/search",method = RequestMethod.POST)
    public Result findSearch(@RequestBody Map searchMap){
        return new Result(true,StatusCode.OK,"查询成功",articleService.findSearch(searchMap));
    }
	
	/**
	 * 增加
	 * @param article
	 */
	@ApiOperation("增加文章")
	@RequestMapping(method=RequestMethod.POST)
	public Result add(@RequestBody Article article  ){
		articleService.add(article);
		return new Result(true,StatusCode.OK,"增加成功");
	}
	
	/**
	 * 修改
	 * @param article
	 */
	@ApiOperation("修改文章")
	@RequestMapping(value="/{id}",method= RequestMethod.PUT)
	public Result update(@RequestBody Article article,@ApiParam("id") @PathVariable String id ){
		article.setId(id);
		articleService.update(article);		
		return new Result(true,StatusCode.OK,"修改成功");
	}
	
	/**
	 * 删除
	 * @param id
	 */
	@ApiOperation("删除文章")
	@RequestMapping(value="/{id}",method= RequestMethod.DELETE)
	public Result delete(@ApiParam("id") @PathVariable String id ){
		articleService.deleteById(id);
		return new Result(true,StatusCode.OK,"删除成功");
	}
	
}
