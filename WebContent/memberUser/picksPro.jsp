<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>


<c:if test="${result > 0 }">
	<script>
		alert('찜목록에 추가되었습니다');

		location.href = "booksSearchForm.do";
	</script>
</c:if>

<c:if test="${result == 0 }">
	<script>
		alert('이미 추가된 매장입니다');

		history.go(-1);
	</script>

</c:if>


</head>
<body>

</body>
</html>