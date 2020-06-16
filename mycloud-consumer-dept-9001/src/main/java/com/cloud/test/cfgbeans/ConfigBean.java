package com.cloud.test.cfgbeans;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.netflix.loadbalancer.AvailabilityFilteringRule;
import com.netflix.loadbalancer.BestAvailableRule;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import com.netflix.loadbalancer.RetryRule;
import com.netflix.loadbalancer.RoundRobinRule;

@Configuration
public class ConfigBean {
	
	@Bean
	@LoadBalanced // Spring Cloud Ribbon 是基于Netflix Ribbon 实现的一套客户端 负载均衡的工具
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

	/*
	@Bean
	public IRule ribbonRule() {
		// 用此处的算法，替代默认的随机选择算法
//		return new RoundRobinRule();// 默认算法，轮询算法
//		return new RandomRule();// 随机算法
		return new RetryRule();// 重试，先按照 RoundRobinRule（轮询）的策略获取服务，如果获取服务失败则在指定时间内会进行重试，获取可用服务
//		return new AvailabilityFilteringRule();// 会先过滤由于多次访问故障而处于断路由跳闸状态的服务，还有并发的连接数超过阈值的服务，然后对剩余的服务列表按照轮询策略进行访问
//		return new WeightedResponseTimeRule();// 根据平均响应的时间计算所有服务的权重，响应时间越快服务权重越大被选中的概率越高，刚启动时如果统计信息不足则使用 RoundRobinRule 策略，等统计信息足够会切换到 WeightedResponseTimeRule
//		return new BestAvailableRule();// 会先过滤由于多次访问故障而处于断路由跳闸状态的服务，然后选中一个并发量最小的服务
//		return new ZoneAvoidanceRule();// 默认规则，符合判断 server 所在区域的性能和 server 的可用性选择服务器
	}
	*/
}
