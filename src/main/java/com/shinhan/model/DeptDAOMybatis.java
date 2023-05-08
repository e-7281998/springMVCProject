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
	 
	//�μ��� ���� ��ȸ�ض�
	public List<DeptVO> deptSelectAll() {
		List<DeptVO> deltlist = sqlSession.selectList(namespace+"selectAll");
		logger.info("------ Mybatis : deptSelectAll "+deltlist.size()+"��------");
		return deltlist; 
	}
	
	//�μ� �󼼺���
	public DeptVO deptByID(int deptid) {
		logger.info("------ Mybatis : deptByID ------");
		//�Ѱ��� ��ȸ�ض� : selectOne
		return sqlSession.selectOne(namespace+"selectById", deptid); 
	}
	
	//�μ� �����ϱ�
	public int deptUpdate(DeptVO dept) { 		
		logger.info("------ Mybatis : deptUpdate ------");
		return sqlSession.update(namespace+"update", dept);
	}

	//���ο� �μ� ���
	public int deptInsert(DeptVO dept) {
		logger.info("------ Mybatis : deptInsert ------");
		return sqlSession.insert(namespace+"insert", dept);
	}
	
	//�μ� ����
	public int deptDelete(int deptid) {
		logger.info("------ Mybatis : deptDelete ------");
		return sqlSession.delete(namespace+"delete", deptid); 
	}

}
