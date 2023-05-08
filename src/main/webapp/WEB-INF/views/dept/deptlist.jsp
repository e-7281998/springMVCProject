<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	table{
		border: 2px solid #333;
	}
</style>
</head>
<body>
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
	<div>
		<h1>부서 조회 페이지</h1>
		<a href="insert.do">신규 부서 등록</a>
		<table>
			<tr>
				<td>부서코드</td>
				<td>부서이름</td>
				<td>매니저</td>
				<td>위치</td>
				<td></td>
			</tr>
			<c:forEach items="${deptList}" var="dept">
			<tr>
				<td><a href="${path}/dept/update.do?deptid=${dept.department_id}">${dept.department_id}</td>
				<td>${dept.department_name}</td>
				<td>${dept.manager_id}</td>
				<td>${dept.location_id}</td>
				<td><a href="${path}/dept/delete.do?deptid=${dept.department_id}">삭제</a></td>
			</tr>
			</c:forEach>
		</table>
	</div> 
</body>
</html>