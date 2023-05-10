<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>직원 상세보기</title>
<%@include file="../common/commonFiles.jsp"%>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/common.css" type="text/css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/detail.css" type="text/css">

</head>
<body>
	<div id="container">
		<h1>직원 상세보기</h1>
		<%@ include file="../common/Header.jsp"%>
		<form method="post" id="myfrm"
			action="<%=request.getContextPath()%>/emp/empDetail.do" class="mb-3">
			<table>
				<tr class="form-floating">
					<td><label for="employee_id">직원번호</label></td>
					<td><span>${emp.employee_id}</span> <input
						class="form-control" type="hidden" name="employee_id"
						id="employee_id" value="${emp.employee_id}"></td>
				</tr>
				<tr>
					<td>firstName</td>
					<td><input type="text" name="first_name" maxlength="20"
						value="${emp.first_name}"></td>
				</tr>
				<tr>
					<td>LastName</td>
					<td><input type="text" name="last_name" required="required"
						value="${emp.last_name}"></td>
				</tr>
				<tr>
					<td>이메일</td>
					<td><input type="email" name="email" required="required"
						value="${emp.email}"></td>
				</tr>
				<tr>
					<td>phone</td>
					<td><input type="tel" name="phone_number"
						pattern="010-[0-9]{4}-[0-9]{4}" value="${emp.phone_number}"></td>
				</tr>
				<tr>
					<td>salary</td>
					<td><input type="number" name="salary" value="${emp.salary}"></td>
				</tr>
				 
				<tr>
					<td>부서</td>
					<td><select name="department_id">
							<option value="0">부서 없음</option>
							<c:forEach items="${deptList}" var="dept">
								<option
									${emp.department_id == dept.department_id ? 'selected':''}
									value="${dept.department_id}">${dept.department_name}</option>
							</c:forEach>
 					</select> <input type="number" name="department_id" value="60"></td>
				</tr>
				 
				<tr>
					<td>메니져</td>
					<td><select name="manager_id">
							<option value="0">매니저 없음</option> 
							<c:forEach items="${managerList}" var="manager">
								<option ${emp.manager_id == manager.employee_id ? 'selected':''} value="${manager.employee_id}">${manager.first_name}-${manager.last_name}</option>
							</c:forEach>
					</select></td>
				</tr>
				<tr>
					<td>커미션</td>
					<td><input type="text" name="commission_pct"
						value="${emp.commission_pct}"></td>
				</tr>
				<tr>
					<td>입사일</td>
					<td><input type="date" name="hire_date" required="required"
						value="${emp.hire_date}"></td>
				</tr>
				 
				<tr>
				<td>직급</td>
				<td>
					<select name="job_id">
						<c:forEach items="${jobList}" var="job">
							<option  ${emp.job_id == job.job_id ? 'selected':''} value="${job.job_id}">${job.job_title}</option>
						</c:forEach>
					</select>
 				</td> 
			</tr>
				<tr style="text-align: center;">
					<td colspan="2"><input type="submit" value="직원수정"></td>
				</tr>
				<tr style="text-align: center;">
					<td colspan="2"><input id="btnRestUpdate" type="button" value="직원수정(Rest)"></td>
				</tr>
				
			</table>
	</div>
	<script>
		//RestAPI로 직원 수정하기
		//contentType : 보내는 데이터 형식이 json이다. 
		$(()=>{
			$("#btnRestUpdate").on("click", ()=>{
				//var aa = myfrm.serlialize();
				var arr = $("#myfrm").serializeArray();
				var obj = {};
				 console.log(arr);
				 
				$.each(arr, (index, item)=>{
					obj[item.name] = item.value;
				}) 
				
				console.log(obj);
				$.ajax({
					url: "${path}/restemp/empDetail.do",
					method : "put",
					data: JSON.stringify(obj),
					contentType: "application/json",
					success: (responseData)=>{
 						location.href="emplist.do";
					},
					error: ()=>{
						
					}
				});
			})
		})
	</script>

	<script>
		function call() {
			//DOM(Document Object Model)
			document.getElementById("here").innerHTML = new Date();
			document.getElementById("employee_id").value = 888;
		}
	</script>
</body>
</html>
