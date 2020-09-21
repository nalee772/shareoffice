<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Share Office | 비밀번호 찾기</title>

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
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css" />
<script src="javascript/javascript.js"></script>
<link rel="icon" href="img/favicon.png">
<link rel='stylesheet' href='css/style.css?v=<%=Math.random()%>'>

</head>
<body>
<div class="header">
        <header class="head_main">
            <nav class="head_main_nav">
                <div class="head_main_nav_img">						<!-- 경로 -->
                    <a href="main.do"><img class="logo_img" src="img/logo.png" alt="logo_img"></a>
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
     <main class="findPw_main">
       <!-- 여기에 작성 -->
       
       	<div class="findPw_container">
		
		</div>
		<form action="findPwPro.do" method="post" >
			<div class="findPw_container_main">
					<!-- ID 입력창 -->		
				<div class="findPw_container_main-body">
					 <label for="findPw_Id_txt">ID </label>
					 <input type="text" name="mbr_id" id="findPw_em_txt" class="form-control input-block" required="required"> 
					 <!-- /ID 입력창 -->	
					 		
					 <!-- Email 입력창 -->			 
					 <label for="findPw_em_txt"> E-mail</label> 
				 	 <input type="text" name="mbr_email" id="findPw_em_txt" class="form-control input-block" required="required"> 
				 	 <!-- /Email 입력창 -->	
				 	 
				 	  <!-- 이름 입력창 -->			 
					 <label for="findPw_name_txt"> Name</label> 
				 	 <input type="text" name="mbr_name" id="findPw_name_txt" class="form-control input-block"required="required"> 
				 	 <!-- /이름 입력창 -->
				 	 
				 	 <!-- FindPw_btn 비밀번호 찾기 버튼 -->
					 <input type="submit" value="비밀번호 찾기" id="FindPw_btn" class="btn btn-primary btn-block" >
					 <!-- FindPw_btn 비밀번호 찾기 버튼 -->								
				</div>
			</div>
		</form>
					<!-- 회원가입 -->
		<div class="findPw_container_main_footer" >
				<a class="label-link" href="joinForm.do" id="join_link">Create an account</a>
									<!-- 회원가입창으로 이동  -->				
					<!--/회원가입 -->
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
            <p class="nav-address">
                (주) SHARE OFFICE | 서울시 석촌동 246-19 5층 | 사업자
                등록번호 : 676-87-00055 | 대표 이준호
            </p>
            <p class="nav-address">
                통신판매업 신고번호: 종로 제 2015-서울종로-0499 호 |
                개인정보관리책임자 : 허지예
            </p>
            <p class="nav-address">
                © SHARE OFFICE. +82 010 4830 9376. molly.daleo3o@gmail.com
            </p>
        </div>
    </footer>
</body>
</html>