package com.shinhan.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.shinhan.vo.EmpVO;



//DAO(Data Access Object):DB업무 ..CRUD..Insert,Select,Update,Delete 
@Repository //Component + DAO
public class EmpDAOMybatis {

	@Autowired	//type이 같으면 자동으로 injection
	SqlSession sqlSession;
	static final String NAMESPACE = "co.kr.firstzone.emp.";
	static final Logger LOG = LoggerFactory.getLogger(EmpDAOMybatis.class);
	
	//직원 전체 조회
	public List<EmpVO> selectAll() {
		List<EmpVO> emplist = sqlSession.selectList(NAMESPACE+"selectAll");
		LOG.info(emplist.toString());
		return emplist;
 	} 
	
	//특정직원 조회
	public EmpVO selectById(int empid) {
		EmpVO emp = sqlSession.selectOne(NAMESPACE+"selectById", empid);
		LOG.info(emp.toString());
		return emp;
	}  
	
	//특정부서의 직원 조회
	public List<EmpVO> selectByDept(int deptid) {
		List<EmpVO> emplist = sqlSession.selectList(NAMESPACE+"selectByDept", deptid);
		LOG.info(emplist.toString());
		return emplist;
	} 
	
	//조건조회, 특정부서의 
	public List<EmpVO> selectByCondition(int deptid, String jobid, double salary) {
		Map<String, Object> mapData = new HashMap<>();
		mapData.put("deptid", deptid);
		mapData.put("jobid", jobid);
		mapData.put("salary", salary);
		List<EmpVO> emplist = 
				sqlSession.selectList(NAMESPACE+"selectByCondition", mapData);
		LOG.info(emplist.toString());
		return emplist;
	} 

	//신규직원 등록
	public int empInsert(EmpVO emp) {
		//int the numver of rows affected by the insert.
		int resultCount = sqlSession.insert(NAMESPACE+"insert", emp);
		LOG.info(resultCount+"건 입력");
		return resultCount;
	} 
	
	//직원정보 수정
	public int empUpdate(EmpVO emp) {
		int resultCount = sqlSession.update(NAMESPACE+"update", emp);
		LOG.info(resultCount+"건 수정");
		return resultCount;
	} 

	public int empDelete(int empid) {
		int resultCount = sqlSession.delete(NAMESPACE+"delete", empid);
		LOG.info(resultCount+"건 삭제");
		return resultCount;
	}  
 

}
 
