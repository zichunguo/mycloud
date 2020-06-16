package com.cloud.test.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cloud.test.bean.Dept;
import com.cloud.test.service.DeptService;

@RestController
@RequestMapping(value = "/dept", produces = { "application/json;charset=UTF-8" })
public class DeptController {

	@Autowired
	private DeptService deptService;

	@Autowired
	private DiscoveryClient client;

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public boolean add(@RequestBody Dept dept) {
		return deptService.add(dept);
	}

	@RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
	public Dept get(@PathVariable("id") Long id) {
		return deptService.get(id);
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public List<Dept> list() {
		return deptService.list();
	}

	@RequestMapping(value = "/discovery", method = RequestMethod.GET)
	public Object discovery() {
		List<String> list = client.getServices();
		System.out.println("*** discovery *** :" + list);
		List<ServiceInstance> serviceList = client.getInstances("MYCLOUD-DEPT");
		for (ServiceInstance element : serviceList) {
			System.out.println(element.getServiceId() + "\t" + element.getHost() + "\t" + element.getPort() + "\t"
					+ element.getUri());
		}
		return this.client;
	}

}
