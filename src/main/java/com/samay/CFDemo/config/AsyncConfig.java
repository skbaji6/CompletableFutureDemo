package com.samay.CFDemo.config;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AsyncConfig {

	@Bean(name = "taskExecutor")
	Executor taskExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		int coreCount = Runtime.getRuntime().availableProcessors();
		executor.setCorePoolSize(coreCount); // Initial pool size
		executor.setMaxPoolSize(coreCount * 2); // Maximum pool size
		executor.setQueueCapacity(500); // Queue capacity
		executor.setThreadNamePrefix("Async-");
		executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy()); // Policy
		executor.initialize();
		return executor;
	}

	@Bean(name = "restTemplate")
	RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
