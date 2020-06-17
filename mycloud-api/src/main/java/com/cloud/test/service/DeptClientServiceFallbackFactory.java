package com.cloud.test.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.cloud.test.bean.Dept;

import feign.hystrix.FallbackFactory;

@Component	// 需要添加该注解，否则将不起作用
public class DeptClientServiceFallbackFactory implements FallbackFactory<DeptClientService> {

	@Override
	public DeptClientService create(Throwable cause) {
		return new DeptClientService() {
			
			@Override
			public List<Dept> list() {
				return null;
			}
			
			@Override
			public Dept get(Long id) {
				return new Dept().setDeptno(id).setDname("该ID：" + id + "没有对应信息, Consumer 客户端提供的降级信息，此刻服务Provider已经关闭。")
						.setDb_source("no this database in MySQL.");
			}
			
			@Override
			public boolean add(Dept dept) {
				return false;
			}
		};
	}

}
