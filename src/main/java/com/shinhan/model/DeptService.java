package com.shinhan.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shinhan.vo.DeptVO;

@Service
public class DeptService {
	
	@Autowired
	DeptDAO dao;
	
	public List<DeptVO> deptSelectAll() {
		return dao.deptSelectAll();
	}
	
	//public List<DeptVO> deptByID(int deptid) {
	public DeptVO deptByID(int deptid) {
		return dao.deptByID(deptid);
	}
	
	public int deptUpdate(DeptVO dept) {
		return dao.deptUpdate(dept);
	}
	
	public int deptInsert(DeptVO dept) {
		return dao.deptInsert(dept);
	}
	
	public int deptDelete(int deptid) {
		return dao.deptDelete(deptid);
	}
}
