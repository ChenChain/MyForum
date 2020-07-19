package com.chain.user.controller;
import java.util.HashMap;
import java.util.Map;

import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import com.chain.user.pojo.User;
import com.chain.user.service.UserService;

import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import util.JWTUtil;

/**
 * 控制器层
 * @author Administrator
 *
 */
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
	 * @return
	 */
	@PutMapping("/{userId}/{friendId}/{x}")
	public void updateFansCountAndFollowCount(@PathVariable String userId,@PathVariable String friendId, @PathVariable int x){
		userService.updateFansCountAndFollowCount(x,userId,friendId);
	}



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

	/**
	 * 发送短信验证码
	 * @param mobile
	 */
	@PostMapping(value="/sendsms/{mobile}")
	public Result sendsms(@PathVariable String mobile ){
		userService.sendSms(mobile);
		return new Result(true,StatusCode.OK,"发送成功");
	}

	/**
	 * 用户注册
	 */
	@PostMapping(value = "/register/{code}")
	public Result register(@RequestBody User user,@PathVariable String code){
		userService.add(user, code);
		return new Result(true,StatusCode.OK,"注册成功");
	}

	/**
	 * 查询全部数据
	 * @return
	 */
	@RequestMapping(method= RequestMethod.GET)
	public Result findAll(){
		return new Result(true,StatusCode.OK,"查询成功",userService.findAll());
	}
	
	/**
	 * 根据ID查询
	 * @param id ID
	 * @return
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.GET)
	public Result findById(@PathVariable String id){
		return new Result(true,StatusCode.OK,"查询成功",userService.findById(id));
	}


	/**
	 * 分页+多条件查询
	 * @param searchMap 查询条件封装
	 * @param page 页码
	 * @param size 页大小
	 * @return 分页结果
	 */
	@RequestMapping(value="/search/{page}/{size}",method=RequestMethod.POST)
	public Result findSearch(@RequestBody Map searchMap , @PathVariable int page, @PathVariable int size){
		Page<User> pageList = userService.findSearch(searchMap, page, size);
		return  new Result(true,StatusCode.OK,"查询成功",  new PageResult<User>(pageList.getTotalElements(), pageList.getContent()) );
	}

	/**
     * 根据条件查询
     * @param searchMap
     * @return
     */
    @RequestMapping(value="/search",method = RequestMethod.POST)
    public Result findSearch( @RequestBody Map searchMap){
        return new Result(true,StatusCode.OK,"查询成功",userService.findSearch(searchMap));
    }
	
	/**
	 * 增加
	 * @param user
	 */
	@RequestMapping(method=RequestMethod.POST)
	public Result add(@RequestBody User user  ){
		userService.add(user);
		return new Result(true,StatusCode.OK,"增加成功");
	}
	
	/**
	 * 修改
	 * @param user
	 */
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
	@RequestMapping(value="/{id}",method= RequestMethod.DELETE)
	public Result delete(@PathVariable String id ){
		userService.deleteById(id);
		return new Result(true,StatusCode.OK,"删除成功");
	}
	
}
