<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>

<head>
<title>Share Office | 게시판</title>
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
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" />
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<link rel="stylesheet"
	href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css" />
<script src="javascript/javascript.js?v=<%=Math.random()%>"></script>
<link rel="icon" href="img/favicon.png">
<link rel='stylesheet' href='css/style.css?v=<%=Math.random()%>'>
</head>

<body>
	<div class="header">
		<header class="head_main">
			<nav class="head_main_nav">


				<!----------------------------- mbr_level = null------------------------------->
				<c:if test="${user.mbr_level == null }">

					<div class="head_main_nav_img">
						<a href="main.do"> <img class="logo_img" src="img/logo.png"
							alt="logo_img">
						</a>
					</div>
					<div class="dummy"></div>

					<ul class="head_main_nav_list">
						<li class="head_main_nav_list_item"><a
							href="booksSearchForm.do" class="nav_tab"> 지점 보기 </a></li>
						<li class="head_main_nav_list_item"><a href="boardList.do"
							class="nav_tab"> 게시판 </a></li>
						<li class="head_main_nav_list_item"><a href="loginForm.do"
							class="nav_tab"> <span>Sign in</span>
						</a></li>
						<li class="head_main_nav_list_item"><a href="joinForm.do"
							class="nav_tab"> <span>Sign up</span>
						</a></li>
					</ul>
				</c:if>


				<!-- mbr_level = 1----------------------------------------------------------------------------------------------------------------------------- -->
				<c:if test="${user.mbr_level == 1 }">
					<div class="head_main_nav_img">
						<a href="mainLogin.do"> <img class="logo_img"
							src="img/logo.png" alt="logo_img">
						</a>
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

										<!-- 로그인 후 main에서 아이디 표시 -->
										<div class="details_list_container_content container_welcome">
											<input type="text" name="mbr_id" id="mbr_id"
												value="${user.mbr_id }" disabled="disabled"
												style="width: 90px; border: none; color: black;">
										</div>

										<!-- 로그인 후 main에서 마이페이지 이동 -->
										<div class="details_list_container_content container_MyPage">
											<a class="userUpdate_label-link" href="userUpdateForm.do"
												id="userUpdate">Your Page</a>
										</div>

										<!--로그인 후 main에서 예약화면 이동  -->
										<div class="details_list_container_content container_Books">
											<a class="Books_label-link"
												href="userBooksList.do?mbr_id=${user.mbr_id}" id="books">Your
												Books </a>
										</div>
										<div class="details_list_container_content container_Picks">
											<a class="Picks_label-link"
												href="userPicksList.do?mbr_id=${user.mbr_id}" id="picks">
												Your Picks</a>
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
				</c:if>
				<!-- /mbr_level = 1----------------------------------------------------------------------------------------------------------------------------- -->

				<!-- mbr_level = 2----------------------------------------------------------------------------------------------------------------------------- -->
				<c:if test="${user.mbr_level == 2 }">
					<div class="head_main_nav_img">
						<a href="mainLogin.do"> <img class="logo_img"
							src="img/logo.png" alt="logo_img">
						</a>
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
												href="memList.do?admin_mbr_id=${user.mbr_id}"
												id="userUpdate">Member</a>
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
				</c:if>
				<!-- /mbr_level = 2----------------------------------------------------------------------------------------------------------------------------- -->
			</nav>
		</header>
	</div>
	<main class="board_main">
	<h1 class="board_header">게시판</h1>
	<table class="board_list">
		<tr>
			<th class="col1">NO.</th>
			<th class="col2">작성자</th>
			<th class="col3">제목</th>
			<th class="col4">작성일자</th>
			<th class="col5">조회수</th>
		</tr>
		<tr>
		</tr>
		<c:if test="${totCnt > 0 }">
			<c:forEach var="board" items="${list }">
				<c:choose>
					<c:when test="${board.brd_id eq 'NOTICE' }">
						<tr class="col3_notice">
					</c:when>
					<c:otherwise>
						<tr>
					</c:otherwise>
				</c:choose>
				<td>${startNum }</td>
				<td>${board.mbr_id}</td>
				<td class="col3_title"><c:if test="${board.art_reflevel > 0 }">
						<span class="board_comment">comment</span>
					</c:if> <!--  답글 작성시 생기는 표시 --> <c:choose>
						<c:when test="${board.brd_id eq 'NOTICE' }">
							<span class="board_comment">공지사항</span>
							<a class="board_title"
								href='boardArticle.do?art_idx=${board.art_idx}&pageNum=${currentPage}'>
								${board.art_title} </a>
						</c:when>
						<c:otherwise>
							<a class="board_title"
								href='boardArticle.do?art_idx=${board.art_idx}&pageNum=${currentPage}'>
								${board.art_title} </a>
						</c:otherwise>
					</c:choose></td>
				<td>${board.art_date}</td>
				<td>${board.art_count}</td>
				</tr>
				<c:set var="startNum" value="${startNum - 1 }" />
			</c:forEach>
		</c:if>
		<c:if test="${totCnt == 0 }">
			<tr>
				<td colspan=7>등록된 게시글이 없습니다.</td>
			</tr>
		</c:if>
	</table>
	<div class="board_footer">
		<form action="boardList.do?pageNum=${pageNum }" name="art_search">
			<input type="hidden" name="pageNum" value="${pageNum }"> <input
				type="hidden" name="art_idx" value="${art_idx}"> <input
				type="hidden" name="art_ref" value="${art_ref}"> <input
				type="hidden" name="art_reflevel" value="${art_reflevel}"> <input
				type="hidden" name="art_refstep" value="${art_refstep}">
			<!-- 답글 작성시 새글 / 답글 구분을 위한 정보들 -->
			<div class="board_footer_search">
				<div class="board_footer_search_select">
					<select class="search_select" name="search_select">
						<option value="art_title" selected="selected">제목</option>
						<option value="art_content">내용</option>
						<option value="mbr_id">작성자</option>
					</select> 
					<span class="search_bar"> 
					<input type="text" name="art_search">
					</span>
				</div>
				<input class="search_btn btn_1" type="submit" value="검색"></input>

				<c:if test="${user.mbr_level > 0 }">
					<input class="search_btn btn_2" type="button" value="글쓰기"
						onclick="location.href='boardWriteForm.do'">

				</c:if>
			</div>
		</form>
	</div>
	<div class="page">
		<div class="page_container">
			<c:if test="${startPage > blockSize }">
				<div class="page_btn"><a
					href='boardList.do?pageNum=${startPage-blockSize}'>
						<div class="page_box page-btn-box"><</div>
				</a>
				</div>
			</c:if>
			<c:forEach var="i" begin="${startPage}" end="${endPage}">
				<div class="page_btn">
					<a href='boardList.do?pageNum=${i}&art_search=${art_search}&search_select=${search_select}'>
						<div class="page_box page-btn-box">
								${i}
						</div>
					</a>
				</div>
			</c:forEach>
			<c:if test="${endPage < pageCnt }">
				<div class="page_btn">
					<a href='boardList.do?pageNum=${startPage+blockSize}'>
						<div class="page_box page-btn-box">
							>
						</div>
					</a>
				</div>
			</c:if>
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
					src="https://img.icons8.com/material-sharp/24/000000/facebook-f.png" />
			</a>
			</span> <span class="footer__nav-icon nav-icon"> <a
				href="https://www.instagram.com/"> <img
					src="https://img.icons8.com/metro/26/000000/instagram-new.png" />
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