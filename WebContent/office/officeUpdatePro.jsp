<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<c:if test="${result > 0 }">
	<script type="text/javascript">
	alert("수정이 완료되었습니다.");
	location.href = "officelist.do?pageNum=${pageNum }";
	</script>
</c:if>
<c:if test="${result == 0 }">
	<script type="text/javascript">
		alert("다시 시도해주세요");
		location.href = "officeUpdateForm.do?ofc_code=${ofc_code}&pageNum=${pageNum}";
	</script>
</c:if>
</body>
</html>