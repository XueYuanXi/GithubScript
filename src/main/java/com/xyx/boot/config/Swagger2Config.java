package com.xyx.boot.config;

import com.github.xiaoymin.swaggerbootstrapui.annotations.EnableSwaggerBootstrapUI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**   
 * Copyright: Copyright (c) 2020
 * ClassName: Swagger2Config.java
 * Description: 注解@EnableSwagger2：注解开启在线接口文档
 * 注解@EnableSwaggerBootstrapUI：注解如果不使用SwaggerBootstrapUi的增强功能，则无需开启
 * 注解@Profile：注解根据部署环境自动开启/关闭Swagger
 *
 * @version v1.0.0
 * @author simon
 * @since 2020-12-14 14:39:56
 */
@Configuration
@EnableSwagger2
@EnableSwaggerBootstrapUI
public class Swagger2Config implements WebMvcConfigurer {

	/**
	 * 创建一个Docket bean
	 * @param environment 环境配置
	 */
	@Bean
	Docket docket(Environment environment) {
        return new Docket(DocumentationType.SWAGGER_2)
        		.apiInfo(apiInfo())
        		.ignoredParameterTypes(HttpSession.class, HttpServletRequest.class, HttpServletResponse.class)
        		.select()
				.apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
				.paths(PathSelectors.any())
				.build();
	}
	
	/**
	 * 添加摘要信息(Docket)
	 */
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("Taskly_接口文档")
				.description("taskly")
				.contact(new Contact("win", null, null))
				.termsOfServiceUrl("http://localhost:8083/taskly/doc.html")
				.version("版本号:0.1")
				.build();
	}

	/**
	 * 配置静态资源
	 * @param registry 资源注册器
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// 解决Swagger无法访问
		registry.addResourceHandler("doc.html")
		.addResourceLocations("classpath:/META-INF/resources/");
		// 解决Swagger的JS文件无法访问
		registry.addResourceHandler("/webjars/**")
		.addResourceLocations("classpath:/META-INF/resources/webjars/");
	}

}
