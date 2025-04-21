/**
 * Copyright © 2023 All rights reserved.
 *
 * @Description:
 * @author: wufeng
 * @date: 2023-02-15 16:32:38
 */
package com.xyx.boot.config;

import cn.dev33.satoken.interceptor.SaInterceptor;
import cn.dev33.satoken.router.SaRouter;
import cn.dev33.satoken.stp.StpUtil;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Copyright: Copyright (c) 2023 Windhill
 *
 * @author wufeng
 * @since 2023-02-15 16:32:38
 */
@Configuration
public class SaTokenConfig implements WebMvcConfigurer {

//    @Resource
//    LoginClient loginClient;

    String[] whiteList = new String[]{
            // "/**",// 需要登录才能访问
            "/auth/login/**",// 登录
            // swagger路径
            "/doc.html", "/swagger-ui.html", "/error", "/swagger-resources/**", "/webjars/bycdao-ui/**",
            "/v2/api-docs", "/webjars/springfox-swagger-ui/**"};

    /**
     * 重写方法，添加拦截器到拦截器注册表
     *
     * @param registry 拦截器注册表
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 添加拦截路径
        registry.addInterceptor(new SaInterceptor(handle -> {
                    SaRouter.match("/**").check(r -> StpUtil.checkLogin());
                    SaRouter.match("/user/info", "/role/permissions").check(r -> {
//                        SaToken刷新
                    });
                }))
                .addPathPatterns("/**").excludePathPatterns(whiteList);
    }

}
