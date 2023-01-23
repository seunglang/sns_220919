<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="d-flex justify-content-center">
	<div class="login-box">
		<h1 class="mb-4">로그인</h1>
		
		<%-- 키보드 Enter키로 로그인이 될 수 있도록 form 태그를 만들어준다.(submit 타입의 버튼이 동작됨) --%>
		<form id="loginForm" action="/user/sign_in" method="post">
			<div class="input-group mb-3">
				<%-- input-group-prepend: input box 앞에 ID 부분을 회색으로 붙인다. --%>
				<div class="input-group-prepend">
					<span class="input-group-text">ID</span>
				</div>
				<input type="text" class="form-control" id="loginId" name="loginId">
			</div>
	
			<div class="input-group mb-3">
				<div class="input-group-prepend">
					<span class="input-group-text">PW</span>
				</div>
				<input type="password" class="form-control" id="password" name="password">
			</div>
			
			<%-- btn-block: 로그인 박스 영역에 버튼을 가득 채운다. --%>
			<input type="submit" class="btn btn-block btn-primary" value="로그인">
			<a class="btn btn-block btn-dark" href="/user/sign_up_view">회원가입</a>
		</form>
	</div>
</div>

<script>
$(document).ready(function() {
	// 로그인 서브밋
	$('#loginForm').submit(function(e) {
		e.preventDefault(); // submit 자동 수행 중단
		
		var loginId = $('input[name=loginId]').val().trim();
		if (loginId == '') {
			alert("아이디를 입력해주세요.");
			return;
		}
		
		var password = $('input[name=password]').val();
		if (password == '') {
			alert("비밀번호를 입력해주세요.");
			return;
		}
		
		// AJAX로 서브밋
		var url = $(this).attr("action");
		var params = $(this).serialize(); // form의 name 속성으로 data를 구성한다.
		console.log(params);
		
		$.post(url, params)
		.done(function(data) {
			if (data.code == 1) {
				location.href="/timeline/timeline_view"; 
			} else {
				alert("로그인에 실패했습니다. 다시 시도해주세요.");
			}
		}); 
	});
});
</script>

<!-- <script>
	$(document).ready(function() {
		$('#loginForm').on('submit', function(e) {
			// submit 안되게 해야 한다.
			e.preventDefault();
			let loginId = $('input[name=loginId]').val().trim();
			let password = $('#password').val();
			//console.log(loginId);
			//console.log(password);
			
			if (loginId == "") {
				alert("아이디를 입력해주세요.");
				return false;
			}
			if (password == "") {
				alert("비밀번호를 입력해주세요.");
				return false;
			}
			
			let url = $(this).attr('action'); // 내가 만든 폼 태그의 action 값이 url 변수에 들어간다.
			let params = $(this).serialize(); // 키와 벨류, 즉 쿼리스트링의 request 함수처럼 구성이 된다.
			//console.log(url);
			//console.log(params);
			
			// ajax 시작
			
			$.post(url, params) // request
			.done(function(data) { // response
				if (data.code == 1) {
					document.location.href = "/timeline/timeline_view"; // 글 목록으로 이동하는 페이지 주소
				} else {
					alert(data.errorMessage);
				}
			});
		});
	});
</script> -->