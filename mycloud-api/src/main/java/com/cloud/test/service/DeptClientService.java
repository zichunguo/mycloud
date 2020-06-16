package com.cloud.test.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cloud.test.bean.Dept;

@FeignClient(value = "MYCLOUD-DEPT")
public interface DeptClientService {
	
	@RequestMapping(value = "/dept/add", method = RequestMethod.POST)
	public boolean add(Dept dept);
	
	@RequestMapping(value = "/dept/get/{id}", method = RequestMethod.GET)
	public Dept get(Long id);
	
	@RequestMapping(value = "/dept/list", method = RequestMethod.GET)
	public List<Dept> list();

}
