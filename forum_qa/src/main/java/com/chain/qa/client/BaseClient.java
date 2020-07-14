package com.chain.qa.client;

import entity.Result;
import entity.StatusCode;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 配合spring cloud调用base模块 服务
 * @author chenqian091
 * @date 2020-07-11
 */

@FeignClient("forum-base")
public interface BaseClient {

    @GetMapping("/label/find/{labelId}")
    public Result findById(@PathVariable("labelId") String labelId);

}
