package com.shinhan.model;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.shinhan.vo.DeptVO;

@Repository
public class DeptDAOMybatis {
	@Autowired
	SqlSession sqlSession; 
	String namespace = "com.shinhan.dept.";
	
	Logger logger = LoggerFactory.getLogger(DeptDAOMybatis.class);
	 
	//부서를 전부 조회해라
	public List<DeptVO> deptSelectAll() {
		List<DeptVO> deltlist = sqlSession.selectList(namespace+"selectAll");
		logger.info("------ Mybatis : deptSelectAll "+deltlist.size()+"건------");
		return deltlist; 
	}
	
	//부서 상세보기
	public DeptVO deptByID(int deptid) {
		logger.info("------ Mybatis : deptByID ------");
		//한건을 조회해라 : selectOne
		return sqlSession.selectOne(namespace+"selectById", deptid); 
	}
	
	//부서 수정하기
	public int deptUpdate(DeptVO dept) { 		
		logger.info("------ Mybatis : deptUpdate ------");
		return sqlSession.update(namespace+"update", dept);
	}

	//새로운 부서 등록
	public int deptInsert(DeptVO dept) {
		logger.info("------ Mybatis : deptInsert ------");
		return sqlSession.insert(namespace+"insert", dept);
	}
	
	//부서 삭제
	public int deptDelete(int deptid) {
		logger.info("------ Mybatis : deptDelete ------");
		return sqlSession.delete(namespace+"delete", deptid); 
	}

}
