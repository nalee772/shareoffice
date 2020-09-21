<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Share Office | 예약목록</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name='viewport' content='width=device-width, initial-scale=1'>
<meta name="description" content="Share Office Portfolio">
<meta name="author" content="Dale">
<script src="https://kit.fontawesome.com/797af710b1.js"
	crossorigin="anonymous"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
<script
	src='https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js'></script>
<script
	src='https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js'></script>
<script src="javascript/javascript.js"></script>
<link
	href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap"
	rel="stylesheet">
<link rel="icon" href="img/favicon.png">
<link rel='stylesheet'
	href='https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css'>
<link rel='stylesheet' href='css/style.css'>
</head>

<body>
	<div class="header">
		<header class="head_main">
			<nav class="head_main_nav">
				<div class="heade_main_log_nav head_main_log_nav_img">
					<a href="mainLogin.do"> <img class="logo_img"
						src="img/logo.png" alt="logo_img"></a>
				</div>
				<div class="dummy"></div>
				<div class="heade_main_log_nav head_main_log_nav_container">
					<div class="heade_main_log_nav_content head_main_log_nav_search">
						<a href="booksSearchForm.do" class="nav_tab"> 지점보기 </a>
					</div>
					<div class="heade_main_log_nav_content head_main_log_nav_board">
						<a href="boardList.do" class="nav_tab"> 게시판 </a>
					</div>
					<div class="heade_main_log_nav_content head_main_log_nav_profile">
						<details class="user_item_details">
							<summary>
								<div class="user_item_details user_icon">
									<img src="img/user.png" alt="user">
								</div>
							</summary>
							<form action="logoutPro.do" method="post">
								<div class="user_item_details_list details_list_container">

									<!-- 관리자 로그인 후 main에서 admin 표시 -->
									<div class="details_list_container_content container_welcome">
										<input type="text" name="admin_mbr_id" id="admin_mbr_id"
											value="${user.mbr_id}" disabled="disabled"
											style="width: 90px; border: none; color: black;">
									</div>

									<!-- 관리자 로그인 후 main에서 회원관리 페이지 이동 -->
									<div class="details_list_container_content container_MyPage">
										<a class="userUpdate_label-link"
											href="memList.do?admin_mbr_id=${user.mbr_id}" id="userUpdate">Member</a>
									</div>

									<!-- 관리자 로그인 후 main에서 오피스관리 페이지 이동 -->
									<div class="details_list_container_content container_Office">
										<a href="officelist.do?admin_mbr_id=${user.mbr_id}">Office</a>
									</div>
									<div class="details_list_container_content container_Books">
										<a href="booksList.do?admin_mbr_id=${user.mbr_id}"
											id="booksList">Books</a>
									</div>

									<!-- 로그인 후 main에서 로그아웃-->
									<div class="details_list_container_content container_Signout">
										<input type="submit" class="btn btn-primary btn-block"
											value="Sign out" onclick="logoutPro.do"
											style="background-color: black; opacity: 0.8; border: none;">
									</div>
								</div>
							</form>
						</details>
					</div>
				</div>
			</nav>
		</header>
	</div>

	<main class="books_main"> <!-- 여기에 작성 -->
	<div class="madmin_container" id="wrap">
		<div class="madmin_container_main-body">
			<table class="books_list">
				<tr>
					<th class="col1">예약번호</th>
					<th class="col2">예약자명</th>
					<th class="col3">아이디</th>
					<th class="col4">오피스 이름</th>
					<th class="col5">유형 이름</th>
					<th class="col6">인원수</th>
					<th class="col7">예약 시작일</th>
					<th class="col8">예약 종료일</th>
				</tr>
				<tr>
				</tr>

				<c:if test="${BtotCnt > 0 }">
					<c:forEach var="books" items="${Blist }">
						<input type="hidden" name="ofc_code" value="${books.ofc_code }">
						<input type="hidden" name="ofctype_code"
							value="${books.ofctype_code }">
						<tr>
							<td>
								<a href="booksInfo.do?pageNum=${currentPage}&admin_mbr_id=${admin_mbr_id}&bks_idx=${books.bks_idx}">
									${books.bks_idx }
								</a>
							</td>
							<td>${books.bks_name}</td>
							<td>${books.mbr_id}</td>
							<td>${books.ofc_name}</td>
							<td>${books.ofctype_name}</td>
							<td>${books.bks_count}</td>
							<td>${books.bks_fromdate}</td>
							<td>${books.bks_todate}</td>
						</tr>
						<c:set var="BstartNum" value="${BstartNum - 1 }" />
					</c:forEach>
				</c:if>
				<c:if test="${BtotCnt == 0 }">
					<tr>
						<td colspan=7>등록된 게시글이 없습니다.</td>
					</tr>
				</c:if>
			</table>
		</div>
		<div class="madmin_footer">
			<form action="bookMemSearch.do" name="frm" onsubmit="return chk()">
				<div class="madmin_footer_search">
					<!-- 리턴체크 script로 메소드 만들기 -->
					<div class="madmin_footer_search_select">
						<select class="bsearch_select" name="bsearch">
							<option name="book_name" value="book_name">예약자명</option>
							<option name="mbr_id" value="mbr_id">아이디</option>
							<option name="Bid_name" value="Bid_name">에약자명 + 아이디</option>
						</select> 
						<span class="search_bar"> 
							<input type="text" name="bookSearch" required="required">
						</span>
					</div>
					<input type="submit" class="search_btn" value="검색"
						onclick="location.href='bookSearch.do'">

				</div>
			</form>
			<div class="page">
				<div class="page_container">
					<c:if test="${BstartPage >BblockSize }">
						<div class="page_btn">
							<a href='booksList.do?BpageNum=${BstartPage-BblockSize}'>
								<div class="page_box page-btn-box"><</div>
							</a>
						</div>
					</c:if>
					<c:forEach var="i" begin="${BstartPage}" end="${BendPage}">
						<div class="page_btn">
							<a href='booksList.do?BpageNum=${i}'>
								<div class="page_box page-btn-box">${i}</div>
							</a>
						</div>
					</c:forEach>
					<c:if test="${BendPage < BpageCnt }">
						<div class="page_btn">
							<a href='booksList.do?BpageNum=${BstartPageB+blockSize}'>
								<div class="page_box page-btn-box">></div>
							</a>
						</div>
					</c:if>
				</div>
			</div>
		</div>
	</main>



	<footer class="footer">
		<div class="footer__nav-column footer-top">
			<span class="footer__nav-icon nav-icon"> <a
				href="https://www.youtube.com/"> <img src="img/youtube.png" />
			</a>
			</span> <span class="footer__nav-icon nav-icon"> <a
				href="https://www.facebook.com/"> <img
					src="https:/img.icons8.com/material-sharp/24/000000/facebook-f.png" />
			</a>
			</span> <span class="footer__nav-icon nav-icon"> <a
				href="https://www.instagram.com/"> <img
					src="https:/img.icons8.com/metro/26/000000/instagram-new.png" />
			</a>
			</span> <span class="footer__nav-icon nav-icon"> <a
				href="https://www.kakaocorp.com/service/KakaoTalk?lang=ko"> <img
					src="img/talk.png" />
			</a>
			</span>
		</div>

		<div class="footer__nav-column footer-bottom">
			<p class="nav-address">(주) SHARE OFFICE | 서울시 석촌동 246-19 5층 | 사업자
				등록번호 : 676-87-00055 | 대표 이준호</p>
			<p class="nav-address">통신판매업 신고번호: 종로 제 2015-서울종로-0499 호 |
				개인정보관리책임자 : 허지예</p>
			<p class="nav-address">© SHARE OFFICE. +82 010 4830 9376.
				molly.daleo3o@gmail.com</p>
		</div>
	</footer>

</body>
</html>