<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Share Office | 회원탈퇴</title>

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
				<div class="heade_main_log_nav head_main_log_nav_img">
					<a href="mainLogin.do?mbr_id=${user.mbr_id}"><img
						class="logo_img" src="img/logo.png" alt="logo_img"></a>
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
			</nav>
		</header>
	</div>

	<main class="userOut_main"> <!-- 여기에 작성 -->
	<div class="userOut_container"></div>
	<form action="userOutPro.do" method="post" name="userOut_frm">
		<div class="userOut_container_main">
			<div class="userOut_container_main_body">
				<input type="hidden" name="mbr_id" id="mbr_id"
					value="${user.mbr_id}" style="width: 90px; color: black;">
			</div>
			<!-- Password  -->
			<div class="userOut_menu_div1">
				<label for="mbr_pw">Password</label> <input type="password"
					name="mbr_pw" id="mbr_pw" class="form-control input-block"
					required="required">
			</div>

			<div>
				<!-- 회원탈퇴 버튼 -->
				<input type="submit" value="회원탈퇴" id="userOut_btn2"
					class="btn btn-primary btn-block" onclick="userOutPro.do">
				<!-- 회원탈퇴 버튼 -->
			</div>
		</div>
	</form>


	<!-- ----------------------------------------------------------------------------------------------------------------------------------------------------------------- -->

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