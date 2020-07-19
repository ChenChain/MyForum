package com.forum.friend.interceptor;

import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import util.JWTUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * TODO
 * jwt权限过滤器
 * @author chain
 * @date 2020/6/30
 */
@Component
public class JWTInterceptor implements HandlerInterceptor {
    @Autowired
    private JWTUtil jwtUtil;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String header=request.getHeader("Authorization");

        if (header!=null&&!"".equals(header)){
            if (header.startsWith("Bearer ")){

                String token=header.substring(7);
                try {
                    Claims claims = jwtUtil.parseJWT(token);
                    String role = (String) claims.get("roles");
                    if (role == null || token.equals("admin")) {
                        //为管理员
                        request.setAttribute("claims_admin",token);
                    }
                    if (role == null || token.equals("user")) {
                        //为管理员
                        request.setAttribute("claims_user",token);
                    }
                } catch (Exception e) {
                    throw new RuntimeException("令牌错误");
                }
            }
        }

        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
