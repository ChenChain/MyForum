package com.forum.friend.client;

import entity.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

/**
 * 微服务调用用户服务
 * 关注数+1 -1
 * 粉丝数+1 -1
 * @author chenqian091
 * @date 2020-07-19
 */

@FeignClient(value = "forum-user",fallback = UserClientImpl.class)
public interface UserClient {
    @PutMapping("/user/{userId}/{friendId}/{x}")
    public Result updateFansCountAndFollowCount(@PathVariable("userId") String userId, @PathVariable("friendId") String friendId, @PathVariable("x") int x);
}
