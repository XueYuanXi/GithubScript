package com.xyx.boot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * 允许跨域访问配置
 *
 * @version v1.0.0
 * @author simon
 * @since 2020-12-14 14:37:22
 */
@Configuration
public class CorsConfig {
	
	private CorsConfiguration corsConfig() {
		CorsConfiguration corsConfiguration = new CorsConfiguration();
		// 允许任何域名使用
		corsConfiguration.addAllowedOriginPattern(CorsConfiguration.ALL);
		// 允许任何请求头
		corsConfiguration.addAllowedHeader(CorsConfiguration.ALL);
		// 允许任何方法（post、get等）
		corsConfiguration.addAllowedMethod(CorsConfiguration.ALL);
		// 允许跨域cookie
		corsConfiguration.setAllowCredentials(true);
		return corsConfiguration;
	}

    @Bean
    CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        // 对接口配置跨域设置
        source.registerCorsConfiguration("/**", corsConfig());
        return new CorsFilter(source);
    }
}
