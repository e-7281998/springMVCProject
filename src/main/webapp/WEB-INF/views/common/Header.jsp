<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <c:set var="path" value="${pageContext.request.contextPath}" scope="application"></c:set>
 <%
 	String company = "신한DS 금융SW아카데미";
 %>
 
 <header>
<%-- 	<img src="${path}/images/kkoong2.jpg" width="30px" height="30px">
 --%>
		<span>로그인한 사용자 : ${loginUser == null ? 'guest' : loginUser.manager_name }</span>
		<img alt="" src="${path}/uploads/${loginUser.pic}" style="width: 30px; height: 30px;">
 	<!-- sessionScope. 세션에서 읽어라 -->
	<p>
		<span>세션에서 로그인 사용자 읽기(EL) : ${sessionScope.loginUser}</span>
		<br/>
		<%-- <span>세션에서 로그인 사용자 읽기(ScripitLet) : <%=session.getAttribute("loginUser")%></span>  --%>
	</p>
	<c:if test="${loginUser==null}">
		<input type="button" value="로그인" onclick="location.href='${path}/auth/loginCheck.do'" id="btnLogout">
	</c:if>
	<c:if test="${loginUser!=null}">
		<input type="button" value="로그아웃" id="btnLogout">
	</c:if>
</header>  
<script>
$(() => {
	
	 $("#btnLogout").on("click", () => {
		 $.ajax({
			url: "${path}/auth/logout.do",
			success: () => {
				alert("로그아웃 되었습니다.");
				location.href="${path}/auth/loginCheck.do";
			},
			error: (msg) => {
				alert(msg);
			}
		});
	}); 
});
</script>		
