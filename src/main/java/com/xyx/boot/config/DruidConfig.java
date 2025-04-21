package com.xyx.boot.config;

import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * Copyright: Copyright (c) 2023
 * ClassName: DruidConfig.java
 *
 * @version 1
 * @author wufeng
 * @since 2023-02-07 08:57:47
 */
@Configuration
public class DruidConfig {
	
	/**
	 * 解决druid 日志报错：discard long time none received connection:xxx
	 *
	 * @author wufeng
	 * @since 2023-02-06 20:31:41
	 */
	@PostConstruct
	public void setProperties(){
	    System.setProperty("druid.mysql.usePingMethod", "false");
	}
}
