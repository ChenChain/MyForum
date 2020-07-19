package com.forum.friend.controller;

import com.forum.friend.client.UserClient;
import com.forum.friend.service.FriendService;
import entity.Result;
import entity.StatusCode;
import io.jsonwebtoken.Claims;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author chenqian091
 * @date 2020-07-14
 */
@Api("好友模块")
@RestController
@RequestMapping("/friend")
public class FriendController {

    @Resource
    private FriendService friendService;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private UserClient userClient;

    @ApiOperation("添加好友或者非好友")
    @PutMapping("like/{friendId}/{type}")
    public Result addFriend(@PathVariable String friendId,@PathVariable String type){
        //验证登录并拿到当前用户的id
        Claims token= (Claims) request.getAttribute("claims_user");
        if (token==null){
            return new Result(false, StatusCode.ERROR,"无权访问");
        }
        String userid=token.getId();
        if (type!=null){
            if (type.equals("1")){
                //添加好友
                int flag=   friendService.addFriend(userid,friendId);
                if (flag==0){
                    return new Result(false, StatusCode.ERROR, "重复添加");
                }
                if (flag==1){
                    //更新关注数与粉丝数
                    userClient.updateFansCountAndFollowCount(userid,friendId,1);
                    return new Result(true, StatusCode.OK, "添加成功");
                }
            }else if (type.equals("2")){
                //添加非好友
                int flag=  friendService.addNoFriend(userid,friendId);
                if (flag==0){
                    return new Result(false, StatusCode.ERROR, "重复添加不喜欢");
                }
                if (flag==1){
                    return new Result(true, StatusCode.OK, "不喜欢成功");
                }
            }else {
                return new Result(false, StatusCode.ERROR,"参数异常");
            }
        }
        return new Result(true, StatusCode.OK, "操作成功");
    }

    /**
     * 删除好友关系
     */
    @DeleteMapping("/{friendId}")
    public Result deleteFriend(@PathVariable String friendId){
        //验证登录并拿到当前用户的id
        Claims token= (Claims) request.getAttribute("claims_user");
        if (token==null){
            return new Result(false, StatusCode.ERROR,"无权访问");
        }
        String userid=token.getId();
        friendService.deleteFriend(userid,friendId);

        //更新关注数与粉丝数
        userClient.updateFansCountAndFollowCount(userid,friendId,-1);
        return new Result(true,StatusCode.OK,"删除成功");
    }
}
