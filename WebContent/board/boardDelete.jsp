<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Share Office | 게시글삭제 </title>
</head>
<body>
	<c:if test="${result > 0 }">
		<script type="text/javascript">
		alert("게시글 삭제가 완료되었습니다.");
		location.href = "boardList.do?pageNum=${pageNum}";
	</script>
	</c:if>
	<c:if test="${result == 0 }">
		<script type="text/javascript">
		alert("삭제 실패.");
		location.href = "boardArticle.do?pageNum=${pageNum}";
	</script>
	</c:if>
</body>
</html>