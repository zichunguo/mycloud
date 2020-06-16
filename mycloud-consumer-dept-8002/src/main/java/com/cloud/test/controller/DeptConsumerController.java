package com.cloud.test.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.cloud.test.bean.Dept;

@RestController
@RequestMapping(value = "/consumer/dept", produces = { "application/json;charset=UTF-8" })
public class DeptConsumerController {

//	private static final String REST_URL_PREFIX = "http://localhost:8001";
	private static final String REST_URL_PREFIX = "http://MYCLOUD-DEPT";

	/**
	 * 使用 使用 RestTemplate 访问 restful 接口非常简单粗暴无脑 主要参数：(url, requestMap,
	 * ResponseBean.class) url：请求地址；requestMap：请求参数；ResponseBean.class：HTTP
	 * 响应转换被转换成的对象类型。
	 */
	@Autowired
	private RestTemplate restTemplate;

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public boolean add(@RequestBody Dept dept) {
		return restTemplate.postForObject(REST_URL_PREFIX + "/dept/add", dept, Boolean.class);
	}

	@RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
	public Dept get(@PathVariable("id") Long id) {
		return restTemplate.getForObject(REST_URL_PREFIX + "/dept/get/" + id, Dept.class);
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public List<Dept> list() {
		return restTemplate.getForObject(REST_URL_PREFIX + "/dept/list", List.class);
	}

	@RequestMapping(value = "/discovery", method = RequestMethod.GET)
	public Object discovery() {
		return restTemplate.getForObject(REST_URL_PREFIX + "/dept/discovery", Object.class);
	}
}
