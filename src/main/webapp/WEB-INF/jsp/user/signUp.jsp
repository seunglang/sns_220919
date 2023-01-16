<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="d-flex justify-content-center">
	<div class="sign-up-box">
		<h1 class="mb-4">회원가입</h1>
		<form id="signUpForm" method="post" action="/user/sign_up">
			<table class="sign-up-table table table-bordered">
				<tr>
					<th>* 아이디(4자 이상)<br></th>
					<td>
						<%-- 인풋박스 옆에 중복확인을 붙이기 위해 div를 하나 더 만들고 d-flex --%>
						<div class="d-flex">
							<input type="text" id="loginId" name="loginId"
								class="form-control" placeholder="아이디를 입력하세요.">
							<button type="button" id="loginIdCheckBtn"
								class="btn btn-success">중복확인</button>
							<br>
						</div> <%-- 아이디 체크 결과 --%> <%-- d-none 클래스: display none (보이지 않게) --%>
						<div id="idCheckLength" class="small text-danger d-none">ID를
							4자 이상 입력해주세요.</div>
						<div id="idCheckDuplicated" class="small text-danger d-none">이미
							사용중인 ID입니다.</div>
						<div id="idCheckOk" class="small text-success d-none">사용 가능한
							ID 입니다.</div>
					</td>
				</tr>
				<tr>
					<th>* 비밀번호</th>
					<td><input type="password" id="password" name="password"
						class="form-control" placeholder="비밀번호를 입력하세요."></td>
				</tr>
				<tr>
					<th>* 비밀번호 확인</th>
					<td><input type="password" id="confirmPassword"
						class="form-control" placeholder="비밀번호를 입력하세요."></td>
				</tr>
				<tr>
					<th>* 이름</th>
					<td><input type="text" id="name" name="name"
						class="form-control" placeholder="이름을 입력하세요."></td>
				</tr>
				<tr>
					<th>* 이메일</th>
					<td><input type="text" id="email" name="email"
						class="form-control" placeholder="이메일 주소를 입력하세요."></td>
				</tr>
			</table>
			<br>

			<button type="submit" id="signUpBtn"
				class="btn btn-primary float-right">회원가입</button>
		</form>
	</div>
</div>

<script>
	$(document).ready(function() {
		$('#loginIdCheckBtn').on('click', function() {
			$('#idCheckLength').addClass('d-none');
			$('#idCheckDuplicated').addClass('d-none');
			$('#idCheckOk').addClass('d-none');
			//alert("테스트용");
			let loginId = $('input[name=loginId]').val().trim();
			//alert(loginId);
			
			if (loginId.length < 4) {
				$('#idCheckLength').removeClass('d-none'); // 경고문구 노출
				return;
			}
			
			$.ajax({
				// request
				type:"GET"
				, url:"/user/is_duplicated_id"
				, data:{"loginId":loginId}
				
				// response
				, success:function(data){
					if(data.code == 1) {
						
						if (data.result) {
							$('#idCheckDuplicated').removeClass('d-none');
						} else {
							$('#idCheckOk').removeClass('d-none');
						}
					} else {
						alert(data.errorMessage);
					}
				}
				, error:function(e) {
					alert("중복 확인을 실패하였습니다." + e);
				}
			});
		});
		
		// 회원가입
		$('#signUpForm').on('submit', function(e) {
			e.preventDefault(); // 서브밋 중단 구문 - 눌러도 submit 되지 않음
			
			// 로그인 ,비밀번호, 등등 비어있지 않은지 validation 추가
			/* loginId
			password
			confirmPassword
			name
			email */
			let loginId = $('#loginId').val().trim();
			let password = $('#password').val().trim();
			let confirmPassword = $('#confirmPassword').val().trim();
			let name = $('#name').val().trim()
			let email = $('#email').val().trim();
			
			if (loginId == "") {
				alert("아이디를 입력해주세요");
				return false;
			}
			if(password == "") {
				alert("비밀번호를 입력해주세요");
				return false;
			}
			if(confirmPassword == "") {
				alert("비밀번호를 입력해주세요");
				return false;
			}
			if (password != confirmPassword) {
				alert("비밀번호가 일치하지 않습니다.");
				return false;
			}
			if(name == "") {
				alert("이름을 입력해주세요");
				return false;
			}
			if(email == "") {
				alert("이메일을 입력해주세요");
				return false;
			}
			
			// 아이디 중복확인 완료 됐는지 확인 -> idCheckOk d-none을 가지고 있으면 확인하라는 alert 띄워야 함
			if($('#idCheckOk').hasClass('d-none')) {
				alert("아이디 중복확인을 다시 해주세요");
				return false;
			}
			
			// 서버로 보내는 첫 번째 방법
			// 1) submit을 동작시킨다 
			// $(this)[0].submit(); // 첫번째 폼태그를 작동시킨다는 뜻이다. - sumit이기 때문에 화면이 넘어간다. view 페이지 이동
			
			// 2) ajax
			let url = $(this).attr('action');
			let params = $(this).serialize(); // name으로 파라미터 구성
			
			//alert(params);
			console.log(params);
			
			$.post(url, params)
			.done(function(data) {
				if (data.code == 1) {
					alert("가입을 환영합니다 ! 로그인 해주세요");
					location.href = "/user/sign_in_view";
				} else {
					alert(data.errorMessage);
				}
				
			});
		});
	});
</script>