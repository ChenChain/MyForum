package com.chain.qa.config;

import com.chain.qa.interceptor.JWTInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * TODO
 *
 * @author chain
 * @date 2020/6/30
 */
@Configuration
public class InterceptorConfig extends WebMvcConfigurationSupport {


    private JWTInterceptor jwtInterceptor = new JWTInterceptor();

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/**/login/**");
    }
}
