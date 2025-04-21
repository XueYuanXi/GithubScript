package com.xyx.boot.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

/**
 * Copyright: Copyright (c) 2023
 * ClassName: MyBatisPlusConfig.java
 *
 * @version 1
 * @author wufeng
 * @since 2023-02-07 08:58:15
 */
@Configuration
public class MyBatisPlusConfig implements MetaObjectHandler {
	final static String USER_NAME_SYSTEM = "system";// mybatis-plus自动填充，系统定时任务等的用户名
	final static String USER_ID_SYSTEM = "system_id";// mybatis-plus自动填充，系统定时任务等的用户名
	/**
	 * 新的分页插件，一缓和二缓遵循mybatis的规则，需要设置 MybatisConfiguration#useDeprecatedExecutor = false 避免缓存出现问题（该属性会在旧插件移除后一同移除）
	 */
	@Bean
	MybatisPlusInterceptor mybatisPlusInterceptor() {
		MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
		// 向Mybatis过滤器链中添加分页拦截器
		interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
		// 添加乐观锁插件
		 interceptor.addInnerInterceptor(new OptimisticLockerInnerInterceptor());
		// 还可以添加其他的拦截器
		return interceptor;
	}

	@Override
	public void insertFill(MetaObject metaObject) {
        setFieldValByNameIfAbsent("createdName", USER_NAME_SYSTEM, metaObject);
		setFieldValByNameIfAbsent("createdBy", USER_ID_SYSTEM, metaObject);
		setFieldValByNameIfAbsent("createdTime", LocalDateTime.now(), metaObject);
	}
	
	@Override
	public void updateFill(MetaObject metaObject) {
        setFieldValByName("updatedName", USER_NAME_SYSTEM, metaObject);
		setFieldValByName("updatedBy", USER_ID_SYSTEM, metaObject);
		setFieldValByName("updatedTime", LocalDateTime.now(), metaObject);
	}
	
	public void setFieldValByNameIfAbsent(String fieldName, Object fieldVal, MetaObject metaObject) {
		// 保存对象时候，获取属性值；如果属性值为NULL，才进行填充
		// 调用的这个方法会判断是否有这个参数，如果有就返回参数的值，都没有就返回null
		Object field = getFieldValByName(fieldName, metaObject);
		if (field == null) {
			setFieldValByName(fieldName, fieldVal, metaObject);
		}
	}
}
