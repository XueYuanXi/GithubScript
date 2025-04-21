package com.xyx.boot.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;
import java.util.Properties;
//

/**
 * <p>
 * 加载配置文件，项目配置文件
 * </p>
 */
@Slf4j
public class ProjectEnvironmentPostProcessor implements EnvironmentPostProcessor {

    /**
     * 重写postProcessEnvironment方法
     * 在Spring Boot应用程序启动之前，对环境进行后处理
     *
     * @param environment 可配置的环境对象
     * @param application Spring应用程序对象
     */
    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        String catalinaHome = System.getProperty("catalina.home");
        log.info("catalina.home: {}", catalinaHome);

        String confDir = catalinaHome + File.separator + "conf" + File.separator + "taskly";
        File directory = new File(confDir);

        if (directory.exists()) {
            Arrays.stream(Objects.requireNonNull(directory.listFiles())).forEach(file -> {
                log.info("Loading properties from {}", file.getPath());
                MutablePropertySources propertySources = environment.getPropertySources();
                Properties properties = loadProperties(file);
                propertySources.addFirst(new PropertiesPropertySource(file.getName(), properties));
            });
        }
    }

    /**
     * 加载文件的属性到Properties对象
     */
    private Properties loadProperties(File file) {
        FileSystemResource resource = new FileSystemResource(file);
        try {
            return PropertiesLoaderUtils.loadProperties(resource);
        } catch (IOException exception) {
            throw new IllegalStateException("Failed to load local settings from " + file.getAbsolutePath(), exception);
        }
    }

}