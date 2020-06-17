package com.cloud.test.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cloud.test.bean.Dept;
import com.cloud.test.service.DeptClientService;

@RestController
@RequestMapping(value = "/consumer/dept", produces = { "application/json;charset=UTF-8" })
public class DeptConsumerController {
	
	@Autowired
	private DeptClientService deptClientService;

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public boolean add(@RequestBody Dept dept) {
		return deptClientService.add(dept);
	}

	@RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
	public Dept get(@PathVariable("id") Long id) {
		return deptClientService.get(id);
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public List<Dept> list() {
		return deptClientService.list();
	}
	
}
