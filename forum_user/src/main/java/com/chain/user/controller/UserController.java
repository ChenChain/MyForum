package com.chain.user.controller;
import java.util.HashMap;
import java.util.Map;

import io.lettuce.core.dynamic.annotation.Param;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import com.chain.user.pojo.User;
import com.chain.user.service.UserService;

import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import util.JWTUtil;

@Api("用户controller")
@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private JWTUtil jwtUtil;


	/**
	 * 微服务调用更新关注数与粉丝数
	 * 粉丝与用户关注数
	 * @param x -1或1 代表增加1个和减少1个
	 */
	@ApiOperation("微服务调用更新关注数与粉丝数，-1或+1")
	@PutMapping("/{userId}/{friendId}/{x}")
	public void updateFansCountAndFollowCount(@ApiParam("用户id") @PathVariable String userId,@Param("关注朋友id") @PathVariable String friendId, @Param("-1或+1") @PathVariable int x){
		userService.updateFansCountAndFollowCount(x,userId,friendId);
	}



	@ApiOperation("用户登录")
	@PostMapping("login")
	public Result login(@RequestBody User user){
		User u=userService.login(user);
		if (u==null){
			return new Result(false,StatusCode.LOGINERROR,"登陆失败");
		}
		String token=	jwtUtil.createJWT(user.getId(),user.getMobile(),user.getPassword());
		Map<String,Object> map=new HashMap<>();
		map.put("token",token);
		map.put("roles","user");
		return new Result(true,StatusCode.OK,"登录成功",map);
	}

	@ApiOperation("发送邮箱验证码")
	@GetMapping(value="/sendmail/{email}")
	public Result sendMail( @Param("邮箱") @PathVariable String email ){
		userService.sendMail(email);
		return new Result(true,StatusCode.OK,"发送成功");
	}

	@ApiOperation("用户注册")
	@PostMapping(value = "/register/{code}")
	public Result register(@RequestBody User user,@Param("邮箱验证码") @PathVariable String code){
		userService.add(user, code);
		return new Result(true,StatusCode.OK,"注册成功");
	}


	@ApiOperation("查询所有用户")
	@GetMapping
	public Result findAll(){
		return new Result(true,StatusCode.OK,"查询成功",userService.findAll());
	}

	@ApiOperation("根据ID查询用户")
	@RequestMapping(value="/{id}",method= RequestMethod.GET)
	public Result findById(@Param("用户ID") @PathVariable String id){
		return new Result(true,StatusCode.OK,"查询成功",userService.findById(id));
	}


	/**
	 * 分页+多条件查询
	 * @param searchMap 查询条件封装
	 * @param page 页码
	 * @param size 页大小
	 * @return 分页结果
	 */
	@ApiOperation("多条件查询用户")
	@RequestMapping(value="/search/{page}/{size}",method=RequestMethod.POST)
	public Result findSearch(@RequestBody Map searchMap ,@RequestParam(defaultValue = "1")   @PathVariable int page, @RequestParam(defaultValue = "10")  @PathVariable int size){
		Page<User> pageList = userService.findSearch(searchMap, page, size);
		return  new Result(true,StatusCode.OK,"查询成功",  new PageResult<User>(pageList.getTotalElements(), pageList.getContent()) );
	}

	/**
     * 根据条件查询
     * @param searchMap
     * @return
     */
	@ApiOperation("根据ID查询用户")
	@RequestMapping(value="/search",method = RequestMethod.POST)
    public Result findSearch( @RequestBody Map searchMap){
        return new Result(true,StatusCode.OK,"查询成功",userService.findSearch(searchMap));
    }

	/**
	 * 修改
	 * @param user
	 */
	@ApiOperation("根据ID修改用户信息")
	@RequestMapping(value="/{id}",method= RequestMethod.PUT)
	public Result update(@RequestBody User user, @PathVariable String id ){
		user.setId(id);
		userService.update(user);		
		return new Result(true,StatusCode.OK,"修改成功");
	}
	
	/**
	 * 删除
	 * @param id
	 */
	@ApiOperation("根据ID删除用户")
	@RequestMapping(value="/{id}",method= RequestMethod.DELETE)
	public Result delete(@PathVariable String id ){
		userService.deleteById(id);
		return new Result(true,StatusCode.OK,"删除成功");
	}
}
