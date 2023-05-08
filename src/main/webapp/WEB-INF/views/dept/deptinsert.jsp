<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>${dept.department_name}신규부서 등록</h1>
	<form action="insert.do" method="post">
		<label>
			<span>부서 코드:</span>
			<input type="number" name="department_id" >
		</label>
		<label>
			<span>부서 이름:</span>
			<input type="text" name="department_name" >
		</label>
		<label>
			<span>매니저 아이디:</span>
			<input type="number" name="manager_id" value="100" >
		</label>
		<label>
			<span>위치:</span>
			<input type="number" name="location_id" value="1700" >
		</label>
		<input type="submit" value="등록하기">
	</form>
</body>
</html>