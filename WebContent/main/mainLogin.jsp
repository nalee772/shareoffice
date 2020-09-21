<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<title>Share Office | INDEX</title>
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
					<a href="mainLogin.do"><img class="logo_img" src="img/logo.png"
						alt="logo_img" /></a>
				</div>
				<div class="dummy"></div>



				<!-- mbr_level = 1----------------------------------------------------------------------------------------------------------------------------- -->
				<c:if test="${user.mbr_level == 1 }">
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
		<form action="booksSearchForm.do?pageNum=${currentPage}" method="post">
			<div class="header_main_img">
				<img class="main_back_img" src="img/1.jpg" alt="main_img" />
				<div class="header_main_img_container">
					<img class="main_logo_img" src="img/logo.png" alt="main_logo_img" />
					<div class="main_img_hr"></div>
					<div class="header_img_search">
						<div class="search_main_item search_main_item_loc">
							<div class="search_main_item_title">위치</div>
							<select class="search_main_item_input search_main_loc"
								name="ofc_region" required="required">
								<option value="">지역을 선택하세요</option>
								<option value="">전체</option>
								<option value="강남">강남</option>
								<option value="역삼">역삼</option>
								<option value="삼성">삼성</option>
								<option value="여의도">여의도</option>
								<option value="종로">종로</option>
							</select>
						</div>
						<div class="search_main_item search_main_item_fromdate">
							<div class="search_main_item_title">예약 시작</div>
							<input id="datepicker"
								class="search_main_item_input search_main_fromdate" type="text"
								placeholder="시작 날짜를 선택하세요" name="bks_fromdate"
								required="required" />
						</div>
						<div class="search_main_item search_main_item_todate">
							<div class="search_main_item_title">예약 종료</div>
							<input id="datepicker2"
								class="search_main_item_input search_main_todate" type="text"
								placeholder="종료 날짜 선택하세요" name="bks_todate" required="required" />
						</div>
						<div class="search_main_item search_main_item_count">
							<div class="search_main_item_title">인원수</div>
							<input class="search_main_item_input search_main_count"
								type="text" placeholder="사용 인원은 몇명인가요?" name="bks_count"
								required="required" />
						</div>
						<div class="search_main_item search_main_item_submit">
							<input class="search_main_item_input search_main_submit"
								type="image"
								src="https://img.icons8.com/ios-glyphs/30/000000/search.png"
								readonly />
						</div>
					</div>
				</div>
			</div>
		</form>
	</div>

	<main class="main">
	<section class="container_section1">
		<div class="container_section1_text">
			<div class="container_section1_text_main main_text_1">공유 오피스 ?
			</div>
			<div class="container_section1_text_main main_text_2">코워킹
				스페이스(Co-Working-Office) ?</div>
		</div>
		<div class="container_section1_text">
			<p class="container_section1_text_sub sub_text_1">
				<span class="text_bold1">건물 전체</span> 또는 <span class="text_bold1">일부</span>를
				<span class="text_bold1">사무공간</span>으로 꾸민 후
			</p>
			<p class="container_section1_text_sub sub_text_2">
				이를 <span class="text_bold1">월 사용료</span>를 받고 <span
					class="text_bold1">임대해</span> 주는 곳을 말하며
			</p>
			<p class="container_section1_text_sub sub_text_3">
				<span class="text_bold2">자산 유동성</span>과 <span class="text_bold2">비용
					절감</span>이 가능합니다.
			</p>
		</div>
	</section>
<section class="container_section2">
		<div class="container_section2_text">
			<h1 class="container_section2_text1">WORKING SPACE</h1>
			<p class="container_section2_text2">이용자들을 위한</p>
			<p class="container_section2_text3">
				<span class="seciton2_bold">다양한</span> 공간이 있습니다.
			</p>
		</div>
		<div class=" container_section2_img">
			<div rel="js-main-slider" class="container_section2_slider">
				<img src="img/slider/이용시설/이용1.JPG" alt="section2_slider" /> 
				<img src="img/slider/이용시설/이용2.JPG" alt="section2_slider"> 
				<img src="img/slider/이용시설/이용3.JPG" alt="section2_slider"> 
				<img src="img/slider/이용시설/이용4.JPG" alt="section2_slider"> 
				<img src="img/slider/이용시설/이용5.JPG" alt="section2_slider">
			</div>
		</div>
	</section>
	<section class="container_section3">
		<div class="container_section3_text">
			<h1 class="container_section3_text1">LIFE SPACE</h1>
			<p class="container_section3_text2">이용자들을 위한</p>
			<p class="container_section3_text3">
				<span class="seciton3_bold">편리한</span> 공간이 있습니다.
			</p>
		</div>
		<div class=" container_section3_img">
			<div rel="js-sub-slider" class="container_section3_slider">
				<img src="img/slider/복지/시설1.JPG" alt="section3_slider" /> 
				<img src="img/slider/복지/시설2.JPG" alt="section3_slider" /> 
				<img src="img/slider/복지/시설3.JPG" alt="section3_slider" /> 
				<img src="img/slider/복지/시설4.JPG" alt="section3_slider" /> 
				<img src="img/slider/복지/시설5.JPG" alt="section3_slider" />
			</div>
		</div>
	</section>
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
