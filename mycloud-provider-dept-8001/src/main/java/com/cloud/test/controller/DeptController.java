package com.cloud.test.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
public class DeptController {
	
	@Autowired
	private DeptService deptService;
	
	@Autowired
	private DiscoveryClient client;
	
	@RequestMapping(value="/dept/add", method=RequestMethod.POST)
	public boolean add(@RequestBody Dept dept) {
		return deptService.add(dept);
	}
	
	@RequestMapping(value="/dept/get/{id}", method=RequestMethod.GET)
	public Dept get(@PathVariable("id") Long id) {
		return deptService.get(id);
	}
	
	@RequestMapping(value="/dept/list", method=RequestMethod.GET)
	public List<Dept> list() {
		return deptService.list();
	}
	
	@RequestMapping(value="/down", method=RequestMethod.GET)
	public void down(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("down");
		response.setHeader("Access-Control-Allow-Origin", "*");
		try {
			ResponseUtil.download(response, "bb.mp4", "/Users/chun/g/masdata/sv/2020/06/12/20200612_172a7609738_r1.mp4");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="/dept/discovery", method=RequestMethod.GET, produces = { "application/json;charset=UTF-8" })
	public Object discovery() {
		List<String> list = client.getServices();
		System.out.println("*** discovery *** :" + list);
		List<ServiceInstance> serviceList = client.getInstances("MYCLOUD-DEPT");
		for (ServiceInstance element : serviceList) {
			System.out.println(element.getServiceId() + "\t" + element.getHost() + "\t" 
					+ element.getPort() + "\t" + element.getUri());
		}
		return this.client;
	}

}
