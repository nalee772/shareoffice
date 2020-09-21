<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Share Office | 회원가입</title>

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
					<!-- 경로 -->
					<a href="main.do"><img class="logo_img" src="img/logo.png"
						alt="logo_img"></a>
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
			</nav>
		</header>
	</div>
	<!-- --------------------------------------------------------------------------------------------------------------------------------------------------------------- -->
	<main class="join_main"> <!-- 여기에 작성 -->

	<div class="join_main_container">
		<div class="join_main_container_header">
			<h1>Create your account</h1>
		</div>

		<form action="joinPro.do" method="post" id="mem_frm" name="mem_frm"
			onsubmit="return chk()">
			<div class="join_main_container_main">
				<!-- ID  -->
				<div>
					<label for="mbr_id"> ID </label>
				</div>
				<div class="join_menu_div join_menu_div1">
					<input type="text" name="mbr_id" id="mbr_id"
						class="form-control input-block" required="required"> <input
						type="button" class="btn btn-primary btn-block" id="join_ck_btn"
						value="중복체크" onclick="confirmId.do">
				</div>
				<div id="join_msg">
					<p>
				</div>
				<!-- 아이디 중복확인 텍스트 -->

				<!-- 이메일  -->
				<div class="join_menu_div join_menu_div2">
					<label for="mbr_email"> E-mail </label> <input type="email"
						name="mbr_email" id="mbr_email" class="join_input form-control input-block"
						required="required" placeholder="example@example.com">
				</div>

				<!-- 이름  -->
				<div class="join_menu_div join_menu_div3">
					<label for="mbr_name"> Name </label> <input type="text"
						name="mbr_name" id="mbr_name" class="join_input form-control input-block"
						required="required">
				</div>


				<!-- Password  -->
				<div class="join_menu_div join_menu_div4">
					<label for="mbr_pw">Password</label> <input type="password"
						name="mbr_pw" id="mbr_pw" class="join_input form-control input-block"
						required="required">
				</div>

				<div class="join_menu_div join_menu_div5">
					<!-- ----------- -->
					<label for="mbr_pw">Password Check</label> <input type="password"
						name="mbr_pw2" id="mbr_pw2" class="form-control input-block"
						required="required" placeholder="비밀번호 확인" onkeyup="checkPwd()">
					<div id="checkPwd"></div>
					<!-- 비밀번호 일치확인 텍스트창 -->

				</div>
				<!-- ------------------------------ -->



				<!-- 전화번호 -->
				<div class="join_menu_div join_menu_div6">
					<label for="mbr_tel"> Tel </label> <input type="text"
						name="mbr_tel" id="mbr_tel" class="join_input form-control input-block"
						required="required" placeholder="'-'을 제외한 숫자만 입력해주세요">
				</div>

				<!-- 이메일 수신동의 -->
				<div class="join_menu_div7 check_box">
					<p>Email preferences</p> <input type="checkbox"
						name="mbr_ag_email" id="mbr_ag_email" value="1"> 이벤트 등
					프로모션 알림 수신(선택)
				</div>


				<div class="join_menu_div8 check_box">
					<p>SMS preferences</p> <input type="checkbox" name="mbr_ag_sms"
						id="mbr_ag_sms" value="1"> 이벤트 등 프로모션 알림 SMS 수신(선택)
				</div>
				<input type="submit" value="Create account" id="join_btn"
					class="btn btn-primary btn-block">
			</div>
		</form>
	</div>

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