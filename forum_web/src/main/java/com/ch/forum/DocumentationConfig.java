package com.ch.forum;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chenqian091
 * @date 2020-07-26
 */
@Component
@Primary
public class DocumentationConfig implements SwaggerResourcesProvider {
    //整合每个微服务的swagger
    @Override
    public List<SwaggerResource> get() {
        List resources = new ArrayList<>();
        //一个 SwaggerResource对应一个微服务 ： 参数： 服务中文名 ， 路径：/zuul前缀/服务的routes访问路径//v2/api-docs  ； 版本
        resources.add(swaggerResource("user-api", "/user/v2/api-docs", "1.0"));
        resources.add(swaggerResource("label-api", "/base/v2/api-docs", "1.0"));
        return resources;

    }


    private SwaggerResource swaggerResource(String name, String location, String version) {
        SwaggerResource swaggerResource = new SwaggerResource();
        swaggerResource.setName(name);
        swaggerResource.setLocation(location);
        swaggerResource.setSwaggerVersion(version);
        return swaggerResource;
    }

}










