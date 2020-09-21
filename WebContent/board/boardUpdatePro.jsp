<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Share Office | 게시글수정</title>
</head>
<body>
	<c:if test="${result > 0 }">
		<script type="text/javascript">
			alert("수정이 완료 되었습니다.");
			location.href = "boardList.do?pageNum=${pageNum}";
		</script>
	</c:if>
	<c:if test="${result == null }">
		<script type="text/javascript">
			alert("수정에 실패하였습니다.");
			location.href = "boardBUpdateForm.do?art_idx=${art_idx}&pageNum=${pageNum}";
		</script>
	</c:if>
</body>
</html>