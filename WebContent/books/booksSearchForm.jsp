<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>Share Office | 지점보기</title>
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
	<form action="booksSearchForm.do?pageNum=${currentPage}" method="post" name="frm">
	
		<main class="booksSearchForm__main"> <!-- 조건 검색 결과-->
		<section class="booksSearchForm__main-section1">
			<div class="booksSearchForm__main-section1-text">
				<!-- 검색한 지역 (없으면 전체) -->
				<c:if test="${office.ofc_region eq '' }">
					<span class="text_location"><td>전체</td></span>
				</c:if>
				<c:if test="${office.ofc_region ne '' }">
					<span class="text_location"><td>${office.ofc_region }</td></span>
				</c:if>
				<!-- 검색한 조건에 맞는 갯수, 날짜, 인원수 -->
				<span class="text_details">
					<td>${books.bks_fromdate }~${books.bks_todate } ·
						${books.bks_count }명</td>
				</span>
			</div>
			<!-- 조건 검색창 -->
			<div class="booksSearchForm__main-section1-head">
				<div class="search_main_item search_main_item_loc">
					<div class="search_main_item_title">위치</div>
					<select class="search_main_item_input search_main_loc"
						name="ofc_region">
						<option value=""
							<c:if test="${office.ofc_region eq ''}">selected</c:if>>전체</option>
						<option value="강남" 
							<c:if test="${office.ofc_region=='강남'}">selected</c:if>>강남</option>
						<option value="역삼"
							<c:if test="${office.ofc_region=='역삼'}">selected</c:if>>역삼</option>
						<option value="삼성"
							<c:if test="${office.ofc_region=='삼성'}">selected</c:if>>삼성</option>
						<option value="여의도"
							<c:if test="${office.ofc_region=='여의도'}">selected</c:if>>여의도</option>
						<option value="종로"
							<c:if test="${office.ofc_region=='종로'}">selected</c:if>>종로</option>
					</select>
				</div>
				<div class="search_main_item search_main_item_fromdate">
					<div class="search_main_item_title">예약 시작</div>
					<input id="datepicker"
						class="search_main_item_input search_main_fromdate" type="text"
						placeholder="시작 날짜를 선택하세요" name="bks_fromdate"
						value="${books.bks_fromdate }" />
				</div>
				<div class="search_main_item search_main_item_todate">
					<div class="search_main_item_title">예약 종료</div>
					<input id="datepicker2"
						class="search_main_item_input search_main_todate" type="text"
						placeholder="종료 날짜 선택하세요" name="bks_todate"
						value="${books.bks_todate }" />
				</div>
				<div class="search_main_item search_main_item_count">
					<div class="search_main_item_title">인원수</div>
					<input class="search_main_item_input search_main_count" type="text"
						placeholder="사용 인원은 몇명인가요?" name="bks_count"
						value="${books.bks_count }" />
				</div>
				<div class="search_main_item search_main_item_submit">
					<input class="search_main_item_input search_main_submit"
						type="image"
						src="https://img.icons8.com/ios-glyphs/30/000000/search.png" />
				</div>
			</div>
		</section>
		<c:forEach items="${officelist}" var="office">	
			<!-- SearchForm에서 선택한 오피스의 정보 -->
			<section class="booksSearchForm__main-section2">
				<div class="booksSearchForm__main-section2-list">
					<a href='booksSelectForm.do?ofc_code=${office.ofc_code}&pageNum=${currentPage}&ofctype_code=${office.ofctype_code}
					&picture1=${office.picture1}&bks_fromdate=${books.bks_fromdate }&bks_todate=${books.bks_todate }&bks_count=${books.bks_count }'>
						<!-- 검색 조건에 맞는 오피스 목록 리스트 -->
						<div
							class="booksSearchForm__main-section2-list-article booksSearchForm-list">
							<div class="booksSearchForm-list-image">
								<!-- 오피스 대표 섬네일 사진 -->
								<img src="${office.picture1}"
									alt="booksSearchForm-list-image" />
							</div>
							<div class="booksSearchForm-list-text">
								<!-- 오피스 이름 -->
								<h1 class="booksSearchForm-list-text-header">${office.ofc_name }</h1>
								<h2 class="booksSearchForm-list-text-content">
									<!-- 오피스 주소 -->
									<p>주소 : ${office.ofc_location }</p>
									<!-- 오피스 번호 -->
									<p>번호 : ${office.ofc_tel }</p>
								</h2>
								<div class="booksSearchForm-list-text-details">
									<div class="list-text-details list-text-details-left">
										<div class="details-option">
											<img
												src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABgAAAAYCAYAAADgdz34AAAABmJLR0QA/wD/AP+gvaeTAAABLklEQVRIie3TzysFURwF8A+miPx8SvIrbFgp/gU2ysJf8KykrFhQdrYW7JSlLC1srfgD8C8Q5dWzQJSFBRZznya9aV7Ns3unbt0z58453e+ZoYEMNKEPPf/k/xphE0WU62w+gOMokH0c1DlgA4VKwBoW6xwwitMIhxjHHWZxgcuMl3twgqUUfRlPOIxQEs//AWO4xU1GQD8+E+fGsILdwOfQjlJzhlGtuMdeNSGq9rBGjOA5RWvFUd6AR8ykaEVxyfKM6AsvYXViK8E/Kofy3KAZvWH/Lu6gwtvrETCI6xStS/wZ5+5gMuybxGN6C3wVU+TrIIlhrFcT8tygDfMJfpXg0/jOG9CN7RRtCOd/A3bEczyrMaCMhRTtt4MKCpgIq6MG8xbhR0pBV/BsIBs/iYYuFTGuKyMAAAAASUVORK5CYII=" />
											<p>책상(1200 * 600)</p>
										</div>
										<div class="details-option">
											<img
												src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAEAAAABACAYAAACqaXHeAAAABmJLR0QA/wD/AP+gvaeTAAACP0lEQVR4nO2bvU4bQRSFv90YLELET0GTFokqb+BUiV+AAom/LgUSSodQWvpIqZAQigiKAn4K6KCkc2koQVSkQpgCU8xsssHeeGdh5oL3ftJKg313ztmj2ZmRR0R0MwrUgWlgqMf3L5Fb4BQ4AK6ziiJgHfgNdAb0ugLW7LP+Qww0noHBUNd+EkLFBrAGLKQCOQUObWK+eA/UbPsYOPKoNQl8xLzWAIvACfANzDufHvbfgapHMwkbKc2NAHpVYCeleQW8jjHJjNuiFvAZaAcwFJo2sAqc2b8ngA8xMJMqOmAwHz6hjXnGhJkYeJP64DKsHxEuUu2xWMzGM6H0AVT6l/whBpaAtznrzzHr7Z2rqZC6LgEsAz8d6hN+FbgnmK7LK9ApYKLIPUF1XUbAvu34Xc76JmZ7/Vi86roEcAfsOdQ/FV51dRJ0MKKTYAETOgk69C+iq5OgQ61Ogugk6IxOgg79i+iWfhIs/e8BGoC0AWk0AGkD0vgIIALmgFl6HEJ6JMIc78276Losg3lZ4u8u7BPww4NGL1aALduuknP36GMEpHdhIx76z2Iqo/1ffIyABjCMOXHa9tB/Fl+BV5jhv5n3Jh8BdIBdD/3244YCp8y6CkgbkEYDkDYgjQYgbUAaDUDagDQagLQBaTQAaQPSaADSBqQpfQB6OuxgRE+HC5jQ02GH/kV09XTYV8cvBQ1A2oA0GoC0AWkergI14Esg7dqDtoRuVwB1e4VGSpcY82+yZaUVYUJYJP9Oa1BoAo17zY8Dh5bBC/QAAAAASUVORK5CYII=" />
											<p>개인 사물함</p>
										</div>
									</div>
									<div class="list-text-details list-text-details-right">
										<div class="details-option">
											<img
												src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABgAAAAYCAYAAADgdz34AAAABmJLR0QA/wD/AP+gvaeTAAABMElEQVRIie3UvUoDURCG4ScJBkRRvAQlnXegpY1WFuolpFM7tTGoIEIuRsFKWwsluYBYiYVWNgqilfhT7IibJYkbFATJB8PunjPfO7PnnF0G+vcq9JgrYQaLmMUUJmLuAdc4xzEu8Jq36DC2cIf3nHGHzfD21Apu+wBn4wZLncBFHOAtY2ihjjlUMBJRibF65KQ9b9gPZlvn6aQG5r973ZQWwpNmLGeTdvGCDV+bP44qjnCFp4grHMbceOQWwvsSrI6ajOsQapLT8t2632M7PGlGV42hmQOcjUZ421TMDuARJ6nnFtYwLdng0bhfx2Uq7zS8uVXDqt4fYzGKb/cDzqos6baJ54hmgMs/AX+qqvu6V3+jAMmvIwvf+S14pyK54aU+CpxLNvwMe321NtCf6gN3Y3hF0l8SfQAAAABJRU5ErkJggg==" />
											<p>Wi-Fi</p>
										</div>
										<div class="details-option">
											<img
												src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAEAAAABACAYAAACqaXHeAAAABmJLR0QA/wD/AP+gvaeTAAAC/0lEQVR4nO2aS2sUQRRGT8ZBHRQNURCyMcb4QBQxIkkMLgNRxODGSDTRhRhUcOfCtSCIf8Cf4EIX4iMRRTfGFxjxsYlKxIWCShSNGnQ046J66JuErq5JerrncQ80VDdf3f7qdk1VdfWAoiiKoihAOzAIjAO5Cj3GvTa2T298N5AtAYNxHVlgH0ANUAeMAkunZ6XC+QY0AvTiZ2YEWJWgqWLTCLzCb+/BFLBSCC4BbxMwFhejmDbmaUgBaXEhG6+fRPgjyulUYjZKBE1A0gaSRhOQtIGkqfoEpMMlrAW6KL9kTQJXMAufQFwSMIC3ZCxD+oEmm6DcnmrkuPSATmA/sKjIXqLmJ3AxTOSSgNfAmTnbKVGq/idQ9QnQadAhkE6DlYxOgw6BdBqsZFKYjwV5ynWwK4TVovw9DQyJCwcw3wpexmopPjYCPeL8fr4wQPJfa+I+bsjM1HoXkjYVZ+NrwXR3SSvQBqygMvkIPAAeJm1EURRFKQXmRRCjD9gKPMPMsUH3OQI0A09D/BwDNgDPQ+7bBawhZMOj2PThLy4OWXTHha7HojsqdL0W3R6hO+xudyZzeRusAU6J8yUWbYco2/YVFgfUselOM3NBFwsd+E/hF7DMon0vtJstuh1CN2LRZYDPQrvL2XWEXBMGLlh0DUL3A/smTAbzF5YcZlNzuUV7VsS96Wo6KpqAf8LAJou2W+juOsR+IvQ7Lbp6/GSFeQhktmPASVH3FvDCom0RZZeXEKlpCVTBB+CyOD/hEDsyPuFnfneIdkho9zrEljPLYIi2TWi/OsSOjKveTe9g70XzgQl8k/UOsdcJ/RfCR/jrnva2Q+zISGMWIWE/oW34jXnnGLsGGBP11jt4aQYWOMafwmzHgL+Y7fLJEF2rKLtuQuSAx+LcNg7kvQwDvx3jT6HY2+LS/KMC6kltWAJKmjf4XXl7AfU6Rb3hIviKhRSme+Yw3TNTQN06/HXGWPTWfKJ4GwwiByzEDFDnMesFVya8uluAc8C9yN0piqIoCv8BxbKOYn0vcL8AAAAASUVORK5CYII=" />
											<p>24시간 냉난방</p>
										</div>
									</div>
								</div>
							</div>
						</div>
					</a>
				</div>
			</section>
		</c:forEach>
	</form>
	<div class="page">
		<div class="page_container">
			<c:if test="${startPage > blockSize }">
				<div class="page_btn"><a href="booksSearchForm.do?pageNum=${startPage-blockSize}">
                            <div class="page_box page-btn-box"></div></a>
				</div>
			</c:if>
			<c:forEach var="i" begin="${startPage}" end="${endPage}">
				<div class="page_btn">  <a href='booksSearchForm.do?pageNum=${i}&ofc_region=${office.ofc_region}
				&bks_fromdate=${books.bks_fromdate}&bks_todate=${books.bks_todate}&bks_count=${books.bks_count}'>
                            <div class="page_box page-num-box">${i}</div>
                        </a></div>
			</c:forEach>
			<c:if test="${endPage < pageCnt }">
				<div class="page_btn"> <a href='booksSearchForm.do?pageNum=${startPage+blockSize}'>
                            <div class="page_box page-btn-box"></div>
				</a></div>
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