package com.xyx.boot.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 线程池配置类
 * * 实现 AsyncConfigurer 接口将会是 @Async 默认的线程池。
 * @author zmf
 */
@Slf4j
@Configuration
public class ThreadPoolConfig implements AsyncConfigurer {

    /**
     * 实现 AsyncConfigurer 接口将会是 @Async 默认的线程池。
     */
    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        // Java虚拟机可用的处理器数
        int corePoolSize = Runtime.getRuntime().availableProcessors();
        // 配置核心线程数
        executor.setCorePoolSize(corePoolSize);
        // 配置最大线程数
        executor.setMaxPoolSize(corePoolSize * 2 + 1);
        // 配置队列大小
        executor.setQueueCapacity(100);
        //空闲的多余线程最大存货时间
        executor.setKeepAliveSeconds(3);
        // 配置线程池中的线程的名称前缀
        executor.setThreadNamePrefix("taskly-thread-execute-");
        // 拒绝策略 调用者执行
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        // 优雅关闭线程池
        executor.setWaitForTasksToCompleteOnShutdown(true);
        // 线程池关闭前最大等待时间，确保最后一定关闭
        executor.setAwaitTerminationSeconds(10);
        // 执行初始化
        executor.initialize();
        return executor;
    }
}
