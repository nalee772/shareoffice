<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>


<c:if test="${result == 1 }">
	<c:if test="${user.mbr_level==2}">
		<script>
			alert('관리자님 환영합니다');
			location.href = "mainLogin.do";
		</script>
	</c:if>
	<script>
		alert('로그인 성공');
		location.href = "mainLogin.do";
	</script>

</c:if>


<c:if test="${result == 0 }">
	<script>
		alert('아이디 또는 비밀번호를 확인하세요');
		location.href = "loginForm.do";
	</script>
</c:if>


<c:if test="${result == null }">
	<script>
		alert('탈퇴하거나 정지된 회원입니다');
		location.href = "loginForm.do";
	</script>
</c:if>




</head>
<body>

</body>
</html>