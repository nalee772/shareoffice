<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>

	<c:if test="${result eq 1 }">
		<script type="text/javascript">
			alert('회원 탈퇴가 완료되었습니다');
			location.href="main.do"; 
			
		</script>
	</c:if>
	<c:if test="${result ne 0 }">
		<script type="text/javascript">
			alert('비밀번호가 맞지 않습니다');
			location.href="userOutForm.do"; 
		</script>
	</c:if>
<body>

</body>
</html>