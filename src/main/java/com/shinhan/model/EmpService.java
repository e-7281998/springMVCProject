package com.shinhan.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shinhan.vo.EmpVO;

@Service	//@Component + service임 : service => business 로직이 있음을 의미
public class EmpService {
	@Autowired
	EmpDAO empDAO;
	public List<EmpVO> selectAll() {
		return empDAO.selectAll();
	}
}
