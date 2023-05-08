<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>500오류</title>
</head>
<body>
	<h1>코드 오류(500)</h1>
	<p><%=exception%></p>
	<p><%=exception.getMessage() %></p>
</body>
</html>