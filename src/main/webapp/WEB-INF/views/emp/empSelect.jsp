<%@page import="com.shinhan.vo.EmpVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@include file="../common/commonFiles.jsp"%> 
 <link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/common.css" type="text/css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/select.css" type="text/css">
 
 <script>
	$(() => {
		$(".btnDel").on("click", function(){
			var empid = $(this).attr("data-del");
			location.href="${path}/emp/empDelete.do?empid="+empid;
		})
		
		
		 
		//조건조회 추가
		$("#btnCondition").on("click", function(e){
			e.preventDefault();
			$.ajax({
				url:"empCondition.do",
				data:{
					deptid:$("#deptSelect").val(),
					jobid:$("#jobSelect").val(),
					salary:$("#salSelect").val(),
					hiredate:$("#dateSelect").val() || undefined
				},
				success:(responseTable)=>{
					$("#here").html(responseTable);
				},
				error:(error)=>{
					$("#here").html(error);
				}
			})
		})
	});
</script>

 
 
</head>
<body>
	<div class="container mt-3">
		<!-- RestFul방식 연습  -->
		<div>
			<p>RestFul방식 연습</p>
			<div>
				<input type="number" id="empid" value="100">
				<div>
					<button id="empRetrive">1건의 직원 조회 </button>
					<button id="empAll">모든 직원 조회 </button>
				</div>
				<div id="resultMessage"></div>
			</div> 
		</div>
		
		<hr>
		
		<h1>직원목록</h1> 
		<div id="empbtn">
			<button onclick="location.href='empinsert.do'" type="button"
				class="btn btn-success">직원등록</button>
			<a type="button" class="btn btn-success" href="empinsert.do">직원등록</a>
		</div>
		<form method="post" action="${path}/emp/empinsert.do">
			<input type="submit" value="직원등록-폼">
		</form>
		
		<form method="post" action="${path}/downloadTest/result.jsp">
			<input type=hidden name="param1" value="kkoong2.jpg" /> <br> <input
				type=hidden name="param2"
				value="f66fb6f92d0142d1ad510f066a5cc43af2ca2567.jpg" /> <br> <input
				type="submit" value="이미지 다운로드">
		</form>


		<div id="stylebtn">
			<button>짝수 행 선택</button>
			<button>홀수 행 선택</button>
			<button>직원 이름으로 찾기</button>
			<button type="button" class="btn btn-primary" data-bs-toggle="modal"
					data-bs-target="#exampleModal" data-bs-whatever="@mdo">
					모달 이용한 직원등록
			</button>
			<%@ include file="empInsertModal.jsp" %>
 		</div>
 		
 		<div id="selectCondition">
 			<p>조건으로 직원 조회</p>
 			<form>
 				<label>
 					<span>부서</span>
					<select id="deptSelect"  multiple="multiple">
					<option name="deptid" value="0" selected="selected">전체</option>
 					<c:forEach items="${deptList}" var="dept">
 						<option name="deptid" value="${dept.department_id}" >${dept.department_name}</option>
 					</c:forEach>
					</select> 	
  				</label>
  				<label>
 					<span>직책</span>
 					<select id="jobSelect">
 					<option name="jobid" value="" selected="selected">전체</option>
 					<c:forEach items="${jobList}" var="job">
 						<option name="jobid" value="${job.job_id}" >${job.job_id}</option>
 					</c:forEach>
					</select> 	
 				</label>
 				<label>
 					<span>급여</span>
 					<input id="salSelect" name="salary"  type="number" value="1000">
 				</label>
 				<label>
 					<input id="dateSelect" type="date" name="hire_date" >
 					<span>이전 입사자</span>
 				</label> 
  				<button id="btnCondition" >조회하기</button>
<!--   				<input type="button" id="btnCondition" value="조회하기"> -->
 			</form>
 		</div>
 		 		 
		<table class="table table-hover" id="here">
			<thead>
				<tr>
					<th>순서</th>
					<th>직원번호</th>
					<th>이름</th>
					<th>성</th>
					<th>이메일</th>
					<th>급여</th>
					<th>누적급여</th>
					<th>입사일</th>
					<th>전화번호</th>
					<th>직책</th>
					<th>메니져</th>
					<th>커미션</th>
					<th>부서</th>
					<th>부서</th>
				</tr>
			</thead>
			<tbody>
				<!--  for(EmpVO emp:empAll -->
				<c:set var="totalSalary" value="0" />
				<c:forEach items="${empAll}" var="emp" varStatus="status">
					<c:set var="totalSalary" value="${totalSalary+emp.salary }" />
					<tr>
						<td
							style="background-color:${status.first||status.last ? 'gold':'lightgrey'}">${status.count}</td>
						<td><a href="empDetail.do?empid=${emp.employee_id}">${emp.employee_id}</a></td>
						<td><a href="empDetail.do?empid=${emp.employee_id}">${emp.first_name}</a></td>
						<td>${emp.last_name}</td>
						<td>${fn:indexOf(emp.email, "@") == -1 ? emp.email : fn:substring(emp.email, 0, fn:indexOf(emp.email, "@"))}
							 
						</td>
						<td><fmt:formatNumber value="${emp.salary}"
								groupingUsed="true" /></td>
						<td>${totalSalary}</td>
						<td><fmt:formatDate value="${emp.hire_date }"
								pattern="YYYY/MM/dd" /></td>
						<td>${emp.phone_number}</td>
						<td>${emp.job_id}</td>
						<td>${emp.manager_id}</td>
						<td><fmt:formatNumber value="${emp.commission_pct}"
								type="percent" /></td>
						<td>${emp.department_id}</td>
						<td>
							<button class="btnDel" data-del="${emp.employee_id}">삭제</button>
							<button class="btnDelRest" data-del="${emp.employee_id}">삭제(Rest)</button>
						</td>
					</tr>
				</c:forEach>
			</tbody>

		</table>
		
 	</div>
 	
 	<script>
	/* Restful 실습 */
	$(() => {
		
		//직원 삭제하기
		$(".btnDelRest").on("click", function(){
			var empid = $(this).attr("data-del");
			console.log("empid > " + empid);
			$.ajax({
				url:"${path}/restemp/empDelete.do/"+empid,
				method:"delete",
				success:(responseData)=>{
 					location.href="${path}/emp/emplist.do";
				},
				error:()=>{
					
				}
 			})
		})
		
		//모든 직원 조회
		$("#empAll").on("click", () => {
			$.ajax({
				url: "${path}/restemp/emplist.do",
				success: (responseData)=> {
					//응답된 데이터(responseData)는 배열임. 왜 ? 모든 직원 조회했으므로
					var output = "<ul>";
					//empAll은 Map의 key값임
					$.each(responseData.empAll, (index, item)=>{
						output += "<li>"+item.first_name+"</li>";
					});
  					$("#resultMessage").html(output+"</ul>");
 				},
				error: () => {
					
				}
 			})
		})
		
		//직원 한명 조회
		$("#empRetrive").on("click", () => {
			var empid = $("#empid").val();
			$.ajax({
				url: "${path}/restemp/empDetail.do/"+empid,
				success: (responseData)=> {
 					$("#resultMessage").html(responseData.first_name);
 				},
				error: () => {
					
				}
 			})
		})
		
	})
</script>
</body>
</html>