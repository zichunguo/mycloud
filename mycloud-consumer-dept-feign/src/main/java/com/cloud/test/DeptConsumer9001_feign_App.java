package com.cloud.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

import com.cloud.myrule.MyRule;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients(basePackages = {"com.cloud.test"})
@ComponentScan("com.cloud.test")
@RibbonClient(name = "MYCLOUD-DEPT", configuration = MyRule.class)
public class DeptConsumer9001_feign_App {
	
	public static void main(String[] args) {
		SpringApplication.run(DeptConsumer9001_feign_App.class, args);
	}

}
