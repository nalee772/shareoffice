<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<title>Share Office | 회원정보</title>
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

	<main class="minfo_main">
	<div class="minfo_container">
		<div class="minfo_container_main-body">
			<div class="member_info_table1">
				<table id="member_info_table1">
					<tr>
						<th class="mem_theader">메일주소</th>
						<th>${mbr_email}</th>
						<th class="member_info_btn_box"><input type="submit"
							class="member_info_btn" value="정지"
							onclick="location.href='memSusForm.do?mbr_id=${mbr_id}&pageNum=${pageNum }&admin_mbr_id=${admin_mbr_id}'"></th>
						<!-- 경로 -->
					</tr>
					<tr>
						<td class="mem_theader">이름</td>
						<td>${mbr_name }</td>
						<td class="member_info_btn_box"><input type="submit"
							class="member_info_btn" value="추방"
							onclick="location.href='memBanForm.do?mbr_id=${mbr_id}&pageNum=${pageNum }&admin_mbr_id=${admin_mbr_id}'"></td>
						<!-- 경로 -->
					</tr>


					<tr>
						<td class="mem_theader">아이디</td>
						<td>${mbr_id}</td>
						<td class="member_info_btn_box"><input type="submit"
							class="member_info_btn" value="활성화"
							onclick="location.href='memActForm.do?mbr_id=${mbr_id}&pageNum=${pageNum }&admin_mbr_id=${admin_mbr_id}'"></td>
						<!-- 경로 -->
					</tr>
					<tr>
						<td class="mem_theader">전화번호</td>
						<td colspan="2">${mbr_tel }</td>
					</tr>
					<tr>
						<td class="mem_theader">이메일수신</td>
						<td class="radio-btn" colspan="2"><c:if
								test="${mbr_ag_email == 1}">
								<span> <input type="radio" id="yes" name="mail_yes"
									value="mail_yes" checked disabled> <label
									for="mail_yes"> 수신 </label>
								</span>
								<span><input type="radio" id="no" name="mail_no"
									value="mail_no" disabled> <label for="mail_no">거부</label></span>
							</c:if> <c:if test="${member.mbr_ag_email == 0}">
								<span><input type="radio" id="yes" name="mail_yes"
									value="mail_yes" disabled> <label for="mail_yes">수신</label></span>
								<span><input type="radio" id="no" name="mail_no"
									value="mail_no" checked disabled> <label for="mail_no">거부</label></span>
							</c:if></td>
					</tr>
					<tr>
						<td class="mem_theader">SMS수신</td>
						<td class="radio-btn" colspan="2"><c:if
								test="${member.mbr_ag_sms == 1}">
								<span><input type="radio" id="yes" name="sms_yes"
									value="sms_yes" checked disabled> <label for="sms_yes">수신</label></span>
								<span><input type="radio" id="no" name="sms_no"
									value="sms_no" disabled> <label for="sms_no">거부</label></span>
							</c:if> <c:if test="${member.mbr_ag_sms == 0}">
								<span><input type="radio" id="yes" name="sms_yes"
									value="sms_yes" disabled> <label for="sms_yes">수신</label></span>
								<span><input type="radio" id="no" name="sms_no"
									value="sms_no" checked disabled> <label for="sms_no">거부</label></span>
							</c:if></td>
					</tr>
				</table>
			</div>
			<div class="member_info_table2">
				<table id="member_info_table2">
					<tr>
						<th class="mi_col1">글번호</th>
						<th class="mi_col2">작성글</th>
						<th class="mi_col3">작성일</th>
					</tr>
					<tr>
						<c:if test="${MtotCnt > 0 }">
							<c:forEach var="memArt" items="${Mlist }">
								<tr>
									<td class="mi_col1">${memArt.art_idx}</td>
									<td class="mi_col2" id="memart_title">
										<!-- content.do? - 게시글 정보 페이지로 이동 --> <a
										href="boardArticle.do?mbr_id=${memArt.mbr_id}&pageNum=${McurrentPage}&art_idx=${memArt.art_idx}">${memArt.art_title}</a>
										<!-- 경로 -->
									</td>
									<td class="mi_col3">${memArt.art_date}</td>
								</tr>
							</c:forEach>
						</c:if>
						<c:if test="${MtotCnt == 0 }">
							<tr>
								<td colspan=3>작성한 게시글이 없습니다</td>
							</tr>
						</c:if>
					</tr>
				</table>
			</div>
		</div>
		<div class="page">
			<div class="page_container">
				<!-- 한 작성자의 게시글들만 목록으로 뽑아 페이지수 나타낼 수 있도록 ListAction참고 -->
				<div class="page_btn">
					<c:forEach var="i" begin="${MstartPage }" end="${MendPage }">
						<a class="blocksize"
							href='memInfo.do?MpageNum=${i }&mbr_id=${mbr_id}'><div
								class="page_box page-btn-box">${i}</div></a>
						<!-- 경로 -->
						<!-- 페이지에 따라 이동시키기 위해 -->
					</c:forEach>
				</div>
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