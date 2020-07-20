package com.forum.friend.client;

import entity.Result;
import entity.StatusCode;

/**
 * @author chenqian091
 * @date 2020-07-20
 */
public class UserClientImpl  implements  UserClient{
    @Override
    public Result updateFansCountAndFollowCount(String userId, String friendId, int x) {
        return new Result(false, StatusCode.ERROR,"调用失败，触发熔断器");
    }
}
