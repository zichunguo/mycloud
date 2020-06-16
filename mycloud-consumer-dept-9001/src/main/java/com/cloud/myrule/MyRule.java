package com.cloud.myrule;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;

@Configuration
public class MyRule {

	@Bean
	public IRule ribbonRule() {
//		return new RandomRule();// 随机
		return new MyRoundRobinRule();// 随机
	}
}
