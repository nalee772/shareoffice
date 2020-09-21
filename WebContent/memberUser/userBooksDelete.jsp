<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<c:if test="${result > 0 }">
	<script type="text/javascript">
		alert("예약이 취소되었습니다")
		location.href="userBooksList.do?pageNum=${pageNum}&mbr_id=${mbr_id}";
	</script>
</c:if>
<c:if test="${result == 0 }">
	<script type="text/javascript">
		alert("지예가 코드를 잘못짰습니다")
		location.href="userBooksList.do?pageNum=${pageNum}&mbr_id=${mbr_id}";
	</script>
</c:if>
</body>
</html>