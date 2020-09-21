<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

	<c:if test="${result > 0 }">
		<script>
			alert('회원 정보가 변경되었습니다');
			location.href="userUpdateForm.do"; 
			
		</script>
	</c:if>

		<script>
			alert('회원 정보를 다시 입력해주세요');
			location.href="userUpdateForm.do"; 
		</script>
</head>
<body>

</body>
</html>