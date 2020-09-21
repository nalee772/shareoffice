let currentScrollTop = 0;

window.onload = function() {
	scrollController();
	$(window).on("scroll", function() {
		scrollController();
	});
};

function scrollController() {
	currentScrollTop = $(window).scrollTop();
	if (currentScrollTop > 50) {
		$(".head_main_nav").addClass("fixed");
	} else {
		$(".head_main_nav").removeClass("fixed");
	}
}

// 화면에서 50px만큼 상단에서 내려오면 메뉴바가 고정 되기위한 function

function removeCheck() {
	if (confirm("정말 삭제하시겠습니까?") == true) {
		// 확인
		document.remove.submit();
	} else {
		// 취소
		return false;
	}
}

// 게시글 삭제를 위한 btn function

function officeRemoveCheck() {
	if (confirm("정말 삭제하시겠습니까?") == true) {
		// 확인
		document.remove.submit();
	} else {
		// 취소
		return false;
	}
}

//오피스 삭제를 위한 btn function

function writeCheck() {
	if (confirm("등록하시겠습니까?") == true) {
		// 확인
		document.write.submit();
	} else {
		// 취소
		return false;
	}
}

//게시글 수정을 위한 btn function

function updateCheck() {
	if (confirm("수정하시겠습니까?") == true) {
		// 확인
		document.update.submit();
	} else {
		// 취소
		return false;
	}
}

// 검색창 날짜 선택을 위한  제이쿼리

$(function() {
	// 모든 datepicker에 대한 공통 옵션 설정
	$.datepicker.setDefaults({
		dateFormat : "yy-mm-dd", // Input Display Format 변경
		showOtherMonths : true, // 빈 공간에 현재월의 앞뒤월의 날짜를 표시
		// ,showMonthAfterYear:true //년도 먼저 나오고, 뒤에 월 표시
		// ,changeYear: true //콤보박스에서 년 선택 가능
		// ,changeMonth: true //콤보박스에서 월 선택 가능
		// ,showOn: "both" //button:버튼을 표시하고,버튼을 눌러야만 달력 표시 ^ both:버튼을 표시하고,버튼을
		// 누르거나 input을 클릭하면 달력 표시
		// buttonImage:
		// "http://jqueryui.com/resources/demos/datepicker/images/calendar.gif",
		// //버튼 이미지 경로
		buttonImageOnly : true, // 기본 버튼의 회색 부분을 없애고, 이미지만 보이게 함
		// ,buttonText: "선택" //버튼에 마우스 갖다 댔을 때 표시되는 텍스트
		yearSuffix : "년", // 달력의 년도 부분 뒤에 붙는 텍스트
		monthNamesShort : [ "1", "2", "3", "4", "5", "6", "7", "8", "9", "10",
				"11", "12", ], // 달력의 월 부분 텍스트
		monthNames : [ "1월", "2월", "3월", "4월", "5월", "6월", "7월", "8월", "9월",
				"10월", "11월", "12월", ], // 달력의 월 부분 Tooltip 텍스트
		dayNamesMin : [ "일", "월", "화", "수", "목", "금", "토" ], // 달력의 요일 부분 텍스트
		dayNames : [ "일요일", "월요일", "화요일", "수요일", "목요일", "금요일", "토요일", ], // 달력의
																			// 요일
																			// 부분
																			// Tooltip
																			// 텍스트
		minDate : "sysdate", // 최소 선택일자(-1D:하루전, -1M:한달전, -1Y:일년전)
		maxDate : "+1M", // 최대 선택일자(+1D:하루후, -1M:한달후, -1Y:일년후)
	});

	// input을 datepicker로 선언
	$("#datepicker").datepicker();
	$("#datepicker2").datepicker();

	// From의 초기값을 오늘 날짜로 설정
	// $('#datepicker').datepicker('setDate', 'today'); //(-1D:하루전, -1M:한달전,
	// -1Y:일년전), (+1D:하루후, -1M:한달후, -1Y:일년후)
	// To의 초기값을 내일로 설정
	// $('#datepicker2').datepicker('setDate', '+1D'); //(-1D:하루전, -1M:한달전,
	// -1Y:일년전), (+1D:하루후, -1M:한달후, -1Y:일년후)
});

// 슬라이드
//officeSelectForm에서의 슬라이드를 위한 function

let sliderImageIndex = 0;

function rotateSlider() {
	let images = window.document
			.querySelectorAll('[rel="js-select-slider"] > img');
	setTimeout(function() {
		for (let i = 0; i < images.length; i++) {
			images[i].style.transform = "translateX(-" + (sliderImageIndex + 1)
					* 100 + "%)";
		}
		if (sliderImageIndex + 2 >= images.length) {
			sliderImageIndex = -1;
		} else {
			sliderImageIndex++;
		}
		rotateSlider();
	}, 2000);
}

rotateSlider();

//메인 화면에서 첫번째슬라이드를 위한 function

let sliderImageIndexMain = 0;

function rotateSliderMain() {
	let images = window.document
			.querySelectorAll('[rel="js-main-slider"] > img');
	setTimeout(function() {
		for (let i = 0; i < images.length; i++) {
			images[i].style.transform = "translateX(-"
					+ (sliderImageIndexMain + 1) * 100 + "%)";
		}
		if (sliderImageIndexMain + 2 >= images.length) {
			sliderImageIndexMain = -1;
		} else {
			sliderImageIndexMain++;
		}
		rotateSliderMain();
	}, 2000);
}

rotateSliderMain();

//메인 화면에서 두번째슬라이드를 위한 function

let sliderImageIndexSub = 0;

function rotateSliderSub() {
	let images = window.document
			.querySelectorAll('[rel="js-sub-slider"] > img');
	setTimeout(function() {
		for (let i = 0; i < images.length; i++) {
			images[i].style.transform = "translateX(-"
					+ (sliderImageIndexSub + 1) * 100 + "%)";
		}
		if (sliderImageIndexSub + 2 >= images.length) {
			sliderImageIndexSub = -1;
		} else {
			sliderImageIndexSub++;
		}
		rotateSliderSub();
	}, 2000);
}

rotateSliderSub();

// 아이디 중복확인-->confirmId.jsp
$(function() {
	$('#join_ck_btn').click(function() {
		if (!mem_frm.mbr_id.value) {
			alert("id를 입력하고 사용하세요");
			mem_frm.mbr_id.focus();
			return false;
		}
		var mbr_id = $('#mbr_id').val();
		var sendData = 'mbr_id=' + mbr_id;
		$.post('confirmId.do', sendData, function(join_msg) {
			$('#join_msg').html(join_msg);
		});
	});
});

// 비밀번호 일치 확인--
function checkPwd() {
	var mem_frm = document.getElementById('mem_frm');
	var mbr_pw = mem_frm.elements["mbr_pw"].value;
	var mbr_pw2 = mem_frm.elements["mbr_pw2"].value;
	if (mbr_pw != mbr_pw2) {
		document.getElementById('checkPwd').style.color = "#FA5858";
		document.getElementById('checkPwd').style.fontSize = "12px";
		document.getElementById('checkPwd').innerHTML = "비밀번호가 일치하지 않습니다";
	} else {
		document.getElementById('checkPwd').style.color = "#5882FA";
		document.getElementById('checkPwd').style.fontSize = "12px";
		document.getElementById('checkPwd').innerHTML = "비밀번호가 일치합니다";
	}
}

function chk() {
	if (mem_frm.mbr_pw.value != mem_frm.mbr_pw2.value) {
		alert("비밀번호를 다시 입력해주세요");
		mem_frm.mbr_pw.focus();
		return false;
	}
	return true;
}
