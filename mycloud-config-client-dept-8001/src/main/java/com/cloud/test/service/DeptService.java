package com.cloud.test.service;

import java.util.List;

import com.cloud.test.bean.Dept;

public interface DeptService {
	
	public boolean add(Dept dept);
	
	public Dept get(Long id);
	
	public List<Dept> list();

}
