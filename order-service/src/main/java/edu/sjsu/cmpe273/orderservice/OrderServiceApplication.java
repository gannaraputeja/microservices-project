package edu.sjsu.cmpe273.orderservice;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import io.micrometer.context.ContextExecutorService;
import io.micrometer.context.ContextSnapshot;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
//import org.springframework.cloud.sleuth.instrument.async.TraceableExecutorService;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Order Service Application
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@RequiredArgsConstructor
public class OrderServiceApplication {

	//private final BeanFactory beanFactory;

	public static void main(String[] args) {
		SpringApplication.run(OrderServiceApplication.class, args);
	}

	@Bean
	public RequestInterceptor requestTokenBearerInterceptor() {
		return new RequestInterceptor() {
			@Override
			public void apply(RequestTemplate requestTemplate) {
				JwtAuthenticationToken token = (JwtAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
				requestTemplate.header("Authorization", "Bearer " + token.getToken().getTokenValue());
				requestTemplate.header("Content-Type", "application/json");
			}
		};
	}

	/*@Bean
	public ExecutorService traceableExecutorService() {
		ExecutorService executorService = Executors.newCachedThreadPool();
		return new TraceableExecutorService(beanFactory, executorService);
	}*/

}
