<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<title>Share Office | 오피스수정</title>
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
	<main class="officeUpdate_main">
	<form action="officeUpdatePro.do?ofc_code=${office.ofc_code}&pageNum=${pageNum}"
		method="post" enctype="multipart/form-data">
		<div class="container">
			<table class="table"
				style="text-align: center; border: 1px solid #dddddd">
				<tr>
					<td class="col1">오피스 이름</td>
					<td class="col2"><textarea rows="0" cols="80" name="ofc_name"
							required="required">${office.ofc_name }</textarea></td>
				</tr>
				<tr>
					<td class="col1">유형식별코드</td>
					<td class="col2"><textarea rows="0" cols="80" name="ofctype_code"
							required="required">${office.ofctype_code }</textarea></td>
				</tr>
				<tr>
					<td class="col1">주소</td>
					<td class="col2"><textarea rows="0" cols="80" name="ofc_location"
							required="required">${office.ofc_location }</textarea></td>
				</tr>
				<tr>
					<td class="col1">전화번호</td>
					<td class="col2"><textarea rows="0" cols="80" name="ofc_tel"
							required="required">${office.ofc_tel }</textarea></td>
				</tr>
				<tr>
					<td class="col1">유형식별이름</td>
					<td class="col2"><textarea rows="0" cols="80" name="ofctype_name"
							required="required">${office.ofctype_name }</textarea></td>
				</tr>
				<tr>
					<td class="col1">지역</td>
					<td class="col2"><textarea rows="0" cols="80" name="ofc_region"
							required="required">${office.ofc_region }</textarea></td>
				</tr>
			</table>
			<table class="table" style="text-align: center; border: 1px solid #dddddd">
				<tr>
					<td class="col1">파일명 1</td>
					<td class="col2">${office.picture1 }
						<input type="hidden" name="picture1" value="${office.picture1 }">
						<input type="file" name="picture1">
					</td>
				</tr>
				<tr>
					<td class="col1">파일명2</td>
					<td class="col2">${office.picture2 }
						<input type="hidden" name="picture2" value="${office.picture2 }">
						<input type="file" name="picture2"></td>
				</tr>
				<tr>
					<td class="col1">파일명3</td>
					<td class="col2">${office.picture3 }
						<input type="hidden" name="picture3" value="${office.picture3 }">
						<input type="file" name="picture3"></td>
				</tr>
				<tr>
					<td class="col1">파일명4</td>
					<td class="col2">${office.picture4 }
					<input type="hidden" name="picture4" value="${office.picture4 }">
					<input type="file" name="picture4"></td>
				</tr>
				<tr>
					<td class="col1">파일명5</td>
					<td class="col2">${office.picture5 }
					<input type="hidden" name="picture5" value="${office.picture5 }">
					<input type="file" name="picture5"></td>
				</tr>
				<tr>
					<td class="col1">파일명6</td>
					<td class="col2">${office.picture6 }
					<input type="hidden" name="picture6" value="${office.picture6 }">
					<input type="file" name="picture6"></td>
				</tr>
				<tr>
					<td class="col1">파일명7</td>
					<td class="col2">${office.picture7 }
					<input type="hidden" name="picture7" value="${office.picture7 }">
					<input type="file" name="picture7"></td>
				</tr>
				<tr>
					<td class="col1">파일명8</td>
					<td class="col2">${office.picture8 }
					<input type="hidden" name="picture8" value="${office.picture8 }">
					<input type="file" name="picture8"></td>
				</tr>
				<tr>
					<td class="col1">파일명9</td>
					<td class="col2">${office.picture9 }
					<input type="hidden" name="picture9" value="${office.picture9 }">
					<input type="file" name="picture9"></td>
				</tr>
				<tr>
					<td class="col1">파일명10</td>
					<td class="col2">${office.picture10 }
					<input type="hidden" name="picture10" value="${office.picture10 }">
					<input type="file" name="picture10"></td>
				</tr>
			</table>
				<input class="search_btn" type="submit" value="수정"> 
				<input class="search_btn" type="button" value="목록" onclick="location.href='officelist.do?ofc_code=${office.ofc_code}&pageNum=${pageNum}'">
		</div>
	</form>
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
</body>
</html>