<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<style>
	form{
		border: 2px solid orange;
		padding: 5px;
	}
	label{
		display: block;
		margin: 5px;
	}
	.link{
		border: 2px solid skyblue;
		padding: 5px;
	}
	a {
		display: block;
		margin: 5px;
		text-decoration: none;
		text-align: center;
		background-color: whitesmoke;
		padding: 5px;
	}
	a:hover {
		background-color: lightgray;
	}
	a:after {
		content: '(링크입니당)';
		position: absolute;
		margin-left: 5px;
		color: red;
		font-size: 11px;
	}
	.info{
		border: 2px solid lightgreen;
		margin-top: 10px;
	}
	.info p {
		margin: 5px;
	}
</style>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Hello world!  
</h1>

<form action="first/paramTest">
	<label>
		<span>아이디:</span>
		<input type="text" name="userid" value="은정">
	</label>
	<label>
		<span>비밀번호:</span>
		<input type="text" name="userpass" value="1998">
	</label>
	<label>
		<span>이메일:</span>
		<input type="text" name="email2" value="ej@naver.com">
	</label>
	<label>
		<span>주소:</span>
		<input type="text" name="address" value="경기도">
	</label> 
	<label>
		<span>나이:</span>
		<input type="text" name="age" value="26">
	</label> 
	<input type="submit" value="전송"/>
</form>

<div class="link">
	<a href="first/sample1">sample1 연결</a>
	<a href="first/sample2">sample2 연결</a>
	<a href="first/a.do">a 연결</a>
	<a href="first/b.do">b 연결</a>
</div>

<div class="info">
	<p>이름은 ${myname}</p>
	<p>나이는 ${myage}</p>
	<p>자동차는 ${mycar}</p>
	<p>자동차는 ${mycar.model}</p>
	<p>자동차는 ${mycar.price}</p>
	<P>  The time on the server is ${serverTime}. </P>
</div>

</body>
</html>
