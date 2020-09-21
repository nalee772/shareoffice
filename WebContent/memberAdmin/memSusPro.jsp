<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Share Office | 회원관리 - 정지</title>
</head>
<body>
	<c:if test="${result > 0}">
		<script type="text/javascript">
			alert("회원 정지가 완료되었습니다");
			location.href="memList.do?pageNum=${pageNum}&admin_mbr_id=${admin_mbr_id}"; /* <!-- 경로 --> */
		</script>
	</c:if>
	<c:if test="${result < 0}">
		<script type="text/javascript">
			alert("회원 정지에 실패했습니다. 비밀번호를 확인해주세요");
			location.href="memSusForm.do?mbr_id=${mbr_id}&pageNum=${pageNum}&admin_mbr_id=${admin_mbr_id}"; /* <!-- 경로 --> */
		</script>
	</c:if>
	<c:if test="${result == 0}">
		<script type="text/javascript">
			alert("회원 정지에 실패했습니다. 추방 가능한 회원인지 확인하세요");
			location.href="memSusForm.do?mbr_id=${mbr_id}&pageNum=${pageNum}&admin_mbr_id=${admin_mbr_id}"; /* <!-- 경로 --> */
		</script>
	</c:if>
</body>
</html>