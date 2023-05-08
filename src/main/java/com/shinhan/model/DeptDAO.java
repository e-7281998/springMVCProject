package com.shinhan.model;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.shinhan.util.OracleUtil;
import com.shinhan.vo.DeptVO;

@Repository
public class DeptDAO {
	
	@Autowired
	DataSource ds;
	
	Connection conn;
	Statement st;
	PreparedStatement pst;
	CallableStatement cst; 
	ResultSet rs;
	int resultCount;
	
	//부서를 전부 조회해라
	public List<DeptVO> deptSelectAll() {
		List<DeptVO> deptList = new ArrayList<>();
		
		String sql = "select * from departments order by 1";
 		
		try {
			conn = ds.getConnection();

			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				DeptVO dept = new DeptVO();
				dept.setDepartment_id(rs.getInt("department_id"));
				dept.setDepartment_name(rs.getString("department_name"));
				dept.setManager_id(rs.getInt("manager_id"));
				dept.setLocation_id(rs.getInt("location_id"));
				deptList.add(dept);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			OracleUtil.dbDisconnect(rs, st, conn);
		}
		return deptList;
	}
	
	//부서 상세보기
	public DeptVO deptByID(int deptid) {
		//public List<DeptVO> deptByID(int deptid) {
		//List<DeptVO> deptList = new ArrayList<>();
		
		String sql = "select * from departments where department_id = " + deptid;
		DeptVO dept = null;
 		
		try {
			conn = ds.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			
			while(rs.next()) {
				dept = new DeptVO();
				dept.setDepartment_id(rs.getInt("department_id"));
				dept.setDepartment_name(rs.getString("department_name"));
				dept.setManager_id(rs.getInt("manager_id"));
				dept.setLocation_id(rs.getInt("location_id"));
 			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			OracleUtil.dbDisconnect(rs, pst, conn);
		}
		return dept;
	}
	
	//부서 수정하기
	public int deptUpdate(DeptVO dept) { 		
		String sql = "update departments set Department_name =?, location_id=?, manager_id=? where department_id =?";
 		
		try {
			conn = ds.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setString(1, dept.getDepartment_name());
			pst.setInt(2, dept.getLocation_id());
			pst.setInt(3, dept.getManager_id());
			pst.setInt(4, dept.getDepartment_id());
			
			resultCount = pst.executeUpdate();
			 
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			OracleUtil.dbDisconnect(rs, pst, conn);
		}
		return resultCount;
	}

	//새로운 부서 등록
	public int deptInsert(DeptVO dept) {
		
		String sql = "insert into departments values(?, ?, ?, ?)";
		
		try {
			
			conn = ds.getConnection();
			pst = conn.prepareStatement(sql);
			
			pst.setInt(1, dept.getDepartment_id());
			pst.setString(2,  dept.getDepartment_name());
			pst.setInt(3, dept.getManager_id());
			pst.setInt(4,  dept.getLocation_id());
			
			resultCount = pst.executeUpdate();
			
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			OracleUtil.dbDisconnect(null, pst, conn);
		}
		
		return resultCount;
	}
	
	//부서 삭제
	public int deptDelete(int deptid) {
		List<DeptVO> deptList = new ArrayList<>();
		
		String sql = "delete from departments where department_id =?";
 		
		try {
			conn = ds.getConnection();

			pst = conn.prepareStatement(sql);
 			pst.setInt(1,deptid); 
			
			resultCount = pst.executeUpdate();
			 
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			OracleUtil.dbDisconnect(rs, pst, conn);
		}
		return resultCount;
	}

}
