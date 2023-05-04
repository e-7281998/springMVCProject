<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
<script
  src="https://code.jquery.com/jquery-3.6.4.min.js" ></script>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<c:set var="path" value="${pageContext.request.contextPath}" />
<img width="150px" alt="이미지" src="${path}/images/kkoong2.jpg">

<form method="post"   action="<%=request.getContextPath()%>/emp/insert.do" class="mb-3">
<table >
  <tr>
  	<td>추가 칼럼(주소)</td>
	<td>
		<input type="text" name="address"/>
	</td>
  </tr>
  <tr  class="form-floating">
    <td><label for="employee_id">직원번호</label></td>
    <td><input class="form-control"  type="number" 
    name="employee_id" id="employee_id" 
    required="required"
    placeholder="직원번호는sequence처리함"
    autofocus="autofocus"
    value="999"
    ></td>
  </tr>
  <tr>
    <td>firstName</td>
    <td><input type="text" name="first_name" maxlength="20" value="이"></td>
  </tr>
  <tr>
    <td>LastName</td>
    <td><input type="text" name="last_name" required="required" value="탬"></td>
  </tr>
  <tr>
    <td>이메일</td>
    <td><input type="email" name="email" value="wed@daum.net" required="required"></td>
  </tr>
  <tr>
    <td>phone</td>
    <td><input type="tel" name="phone_number" 
    value="010-7896-8963"
    pattern="010-[0-9]{4}-[0-9]{4}"/></td>
  </tr>
  <tr>
    <td>salary</td>
    <td><input type="number" name="salary" value="1500"></td>
  </tr>
  <tr>
    <td>부서</td>
    <td>
        <input type="number" name="department_id" value="10">
    </td>
  </tr>
  <tr>
    <td>메니져</td>
    <td><input type="number" name="manager_id" value="100">
       
     
    </td>
  </tr>
  <tr>
    <td>커미션</td>
    <td><input type="text" name="commission_pct"  value="0.2"></td>
  </tr>
  <tr>
    <td>입사일</td>
    <td><input type="date" name="hire_date" 
    value="2021-01-10"
    required="required"></td>
  </tr>
  <tr>
    <td>직급</td>
    <td><input type="text" name="job_id"  value="IT_PROG">
       
    </td>
  </tr>
  <tr style="text-align: center;">
    <td colspan="2">
       <input type="submit" value="직원등록">
    </td>
  </tr>
</table>
</form>
</body>
</html>