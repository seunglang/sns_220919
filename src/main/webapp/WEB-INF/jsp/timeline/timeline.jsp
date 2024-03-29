<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="d-flex justify-content-center">
	<div class="contents-box">
		<%-- 글쓰기 영역: 로그인 된 상태에서만 보여짐 --%>
		<c:if test="${not empty userId}">
		<div class="write-box border rounded m-3">
			<textarea id="writeTextArea" placeholder="내용을 입력해주세요" class="w-100 border-0"></textarea>
				
			<%-- 이미지 업로드를 위한 아이콘과 업로드 버튼을 한 행에 멀리 떨어뜨리기 위한 div --%>
			<div class="d-flex justify-content-between">
				<div class="file-upload d-flex">
					<%-- file 태그는 숨겨두고 이미지를 클릭하면 file 태그를 클릭한 것처럼 이벤트를 줄 것이다. --%>
					<input type="file" id="file" class="d-none" accept=".gif, .jpg, .png, .jpeg">
					<%-- 이미지에 마우스 올리면 마우스커서가 링크 커서가 변하도록 a 태그 사용 --%>
					<a href="#" id="fileUploadBtn"><img width="35" src="https://cdn4.iconfinder.com/data/icons/ionicons/512/icon-image-512.png"></a>

					<%-- 업로드 된 임시 파일 이름 저장될 곳 --%>
					<div id="fileName" class="ml-2">
					</div>
				</div>
				<button id="writeBtn" class="btn btn-info">게시</button>
			</div>
		</div>
		</c:if>
		<%--// 글쓰기 영역 끝 --%>
		
		<%-- 타임라인 영역 --%>
		<div class="timeline-box my-5">
			<c:forEach items="${cardList}" var="card">
			<%-- 카드1 --%>
			<div class="card border rounded mt-3">
				<%-- 글쓴이, 더보기(삭제) --%>
				<div class="p-2 d-flex justify-content-between">
					<span class="font-weight-bold">
						${card.user.loginId}
						<!-- if문으로 팔로우를 수락했다면 그 즉시 이 버튼 대신 체크했다는 아이콘으로 바꿔주자 - 토글 -->
						<a href="#" class="follow-btn" data-user-id="${card.user.id}">
							<img src="https://cdn-icons-png.flaticon.com/512/3756/3756602.png" width="30px" alt="followPic">
						</a>
					</span>
					
					
					<%-- 더보기 - 내가 쓴 글일때만 노출 --%>
					<c:if test="${userId eq card.user.id}">
					<a href="#" class="more-btn" data-toggle="modal" data-target="#modal" data-post-id="${card.post.id}">
						<img src="https://www.iconninja.com/files/860/824/939/more-icon.png" width="30">
					</a>
					</c:if>
				</div>
				
				<%-- 카드 이미지 --%>
				<div class="card-img">
					<img src="${card.post.imagePath}" class="w-100" alt="본문 이미지">
				</div>
				
				<%-- 좋아요 --%>
				<div class="card-like m-3">
					
					<a href="" class="like-btn" data-post-id="${card.post.id}" data-user-id="${userId}">
						<c:choose>
							<c:when test="${card.filledLike eq true}">
								<img src="https://www.iconninja.com/files/379/449/110/heart-icon.png" id="filledHeart" class="" width="18" height="18" alt="empty heart">
							</c:when>
							<c:when test="${card.filledLike == false}">
								<img src="https://www.iconninja.com/files/214/518/441/heart-icon.png" id="unfilledHeart" class="" width="18" height="18" alt="empty heart">
							</c:when>
						</c:choose>
						좋아요 ${card.likeCount}개
					</a>
					
					<!-- 선생님 코드 -->
					<!-- 좋아요가 되어있을 때 -->
					<%-- <a href="" class="like-btn" data-post-id="${card.post.id}" data-user-id="${card.user.id}">
						<c:if test="${card.filledLike eq true}">
							<img src="https://www.iconninja.com/files/379/449/110/heart-icon.png" id="filledHeart" class="" width="18" height="18" alt="empty heart">
						</c:if>
					</a> --%>
					<!-- 좋아요가 해제되어 있을 때 -->
					<%-- <a href="" class="like-btn" data-post-id="${card.post.id}" data-user-id="${card.user.id}">
						<c:if test="${card.filledLike eq false}">
							<img src="https://www.iconninja.com/files/214/518/441/heart-icon.png" id="unfilledHeart" class="" width="18" height="18" alt="empty heart">
						</c:if>
					</a> --%>
					<%-- 좋아요 ${card.likeCount}개 --%>
				</div>
				
				<%-- 글 --%>
				<div class="card-post m-3">
					<span class="font-weight-bold">${card.user.loginId}</span>
					<span>${card.post.content}</span>
				</div>
				
				<%-- 댓글 --%>
				<div class="card-comment-desc border-bottom">
					<div class="ml-3 mb-1 font-weight-bold">댓글</div>
				</div>
				
				<%-- 댓글 목록 --%>
				<div class="card-comment-list m-2">
					
					<%-- 댓글 내용 --%>
					<c:forEach items="${card.commentList}" var="commentView">
					<div class="card-comment m-1">
						<span class="font-weight-bold">${commentView.user.loginId}:</span>
						<span>${commentView.comment.content}</span>
						
						<%-- 댓글 삭제 버튼 --%>
						<a href="#" class="commentDelBtn">
							<img src="https://www.iconninja.com/files/603/22/506/x-icon.png" width="10px" height="10px">
						</a>
					</div>
					</c:forEach>
					
					<%-- 댓글 쓰기 --%>
					<c:if test="${not empty userId}">
					<div class="comment-write d-flex border-top mt-2">
						<input type="text" class="form-control border-0 mr-2" placeholder="댓글 달기"/> 
						<button type="button" class="comment-btn btn btn-light" data-post-id="${card.post.id}">게시</button>
					</div>
					</c:if>
				</div>
				<%--// 댓글 목록 끝 --%>
			</div>
			<%--// 카드1 끝 --%>
			</c:forEach>
		</div>
		<%--// 타임라인 영역 끝  --%>
	</div>
</div>


<!-- 모달 -->

<!-- Modal -->
<div class="modal fade" id="modal""> <!--  data-post-id="" -->
	<%-- modal-sm : 작은 모달 창 --%>
	<%-- modal-dialog-centered : 수직으로 가운데 정렬 --%>
  	<div class="modal-dialog modal-sm modal-dialog-centered"> <!-- modal-dialog-centered 모달창을 가운데로 뜨게 하는 코드 -->
    	<div class="modal-content text-center">
      		<div class="py-3 border-bottom">
      			<a href="#" id="deletePostBtn">삭제하기</a>
      		</div>
      		<div class="py-3">
      		<%-- data-dismiss="modal" 을 추가하면 모달창이 닫힘 --%>
      			<a href="#" data-dismiss="modal">취소하기</a> 
      		</div>
    	</div>
 	 </div>
</div>




<script>
	$(document).ready(function() {
		// 파일업로드 이미지 클릭 => 숨겨져있는 file을 동작시킴
		$('#fileUploadBtn').on('click', function(e) {
			e.preventDefault(); // a 태그의 올라가는 현상 방지
			$('#file').click(); // input file을 클릭한 것과 같은 효과
		});
		
		// 사용자가 이미지를 선택했을 때 유효성 확인 및 업로드 된 파일 이름 노출
		$('#file').on('change', function(e) {
			//alert("파일 선택");
			let fileName = e.target.files[0].name; // 07_30_01.png
			//alert(fileName);
			
			// 확장자 유효성 확인
			let ext = fileName.split(".").pop().toLowerCase();
			if (ext != 'jpg' && ext != 'jpeg' && ext != 'gif' && ext != 'png') {
				alert("이미지 파일만 업로드 할 수 있습니다.");
				$('#file').val(''); // 파일 태그에 실제 파일 제거
				$("#fileName").text(''); // 파일 이름 비우기
				return;
			}
			
			// 유효성 통과한 이미지는 상자에 업로드 된 파일 이름 노출
			$('#fileName').text(fileName);
		});
		
		// 글쓰기 게시
		$('#writeBtn').on('click', function() {
			// validation 
			let content = $('#writeTextArea').val();
			console.log(content);
			if (content.length < 1) {
				alert("글 내용을 입력해주세요");
				return;
			}
			
			let file = $('#file').val();
			if (file == '') {
				alert('파일을 업로드 해주세요');
				return;
			}
			
			// 파일이 업로드 된 경우 확장자 체크
			let ext = file.split('.').pop().toLowerCase(); // 파일 경로를 .으로 나누고 확장자가 있는 마지막 문자열을 가져온 후 모두 소문자로 변경
			if ($.inArray(ext, ['gif', 'png', 'jpg', 'jpeg']) == -1) {
				alert("gif, png, jpg, jpeg 파일만 업로드 할 수 있습니다.");
				$('#file').val(''); // 파일을 비운다.
				return;
			}
			
			// 폼태그를 자바스크립트에서 만든다.
			let formData = new FormData();
			formData.append("content", content);
			formData.append("file", $('#file')[0].files[0]); // $('#file')[0]은 첫번째 input file 태그를 의미, files[0]는 업로드된 첫번째 파일
			
			// AJAX form 데이터 전송
			$.ajax({
				type: "post"
				, url: "/post/create"
				, data: formData
				, enctype: "multipart/form-data"    // 파일 업로드를 위한 필수 설정
				, processData: false    // 파일 업로드를 위한 필수 설정
				, contentType: false    // 파일 업로드를 위한 필수 설정
				, success: function(data) {
					if (data.code == 1) {
						location.reload();
					} else if (data.code == 500) { // 비로그인 일 때
						location.href = "/user/sign_in_view";
					} else {
						alert(data.errorMessage);
					}
				}
				, error: function(e) {
					alert("글 저장에 실패했습니다. 관리자에게 문의해주세요.");
				}
			});  // --- ajax 끝
		}); //--- 글쓰기 버튼 끝
		
		
		// 댓글 쓰기
		$('.comment-btn').on('click', function() {
			// 글번호, 댓글 내용
			let postId = $(this).data('post-id');
			//alert(postId);
			// 지금 클릭된 게시버튼의 형제인 input 태그를 가져온다. siblings
			let comment = $(this).siblings('input').val().trim();
			
			if (comment == '') {
				alert("댓글 내용을 입력해주세요");
				return;
			}
			
			$.ajax({
				type:'POST'
				,url:'/comment/create'
				,data: {"postId":postId, "content":comment}
				,success: function(data) {
					if (data.code == 1) {
						location.reload(); // 새로고침
					} else if (data.code == 500) {
						alert("로그인을 해주세요.");
						location.href = "/user/sign_in_view";
					}
				}
				,error: function(jqXHR, textStatus, errorThrown) {
					var errorMsg = jqXHR.responseJSON.status;
					alert(errorMsg + ":" + textStatus);
				}
			});
		});
		
		// 좋아요 누르기
		$('.like-btn').on('click', function(e) {
			e.preventDefault();
			
			let postId = $(this).data('post-id');
			let userId = $(this).data('user-id');
			//alert(userId);
			
			// 로그인 확인 여부 체크하자
			if (userId == '') {
				alert("로그인을 해주세요");
				return;
			}
			
			$.ajax({
				
				type:'GET'
				, url:'/like/' + postId
				//, data:{"postId":postId}
			
				, success:function(data) {
					if (data.code) { // 좋아요가 insert 됐을 때
						document.location.reload(); // timetime 컨트롤러를 다시 들어와서 실행시키겠다는 뜻
					} else { // 좋아요가 delete되거나 누르지 않은 상태일 때
						if (data.code == 500) {
							alert(data.result);
						}
						document.location.reload();
					}
				}
				
				, error:function(e) {
					var errorMsg = jqXHR.responseJSON.status;
					alert(errorMsg + ":" + textStatus);
				}
			});
			
		});
		
		// 더보기 버튼(...) 클릭 (글 삭제를 위해)
		$('.more-btn').on('click', function(e) {
			e.preventDefault(); // a태그에 위로 올라가는 현상 방지하기 위한 e.preventDefault
			let postId = $(this).data("post-id"); // getting
			//alert(postId);
			
			$('#modal').data('post-id', postId) // setting 모달 태그에 data-post-id를 심어 넣어줌
			
		});
		
		// 모달 안에 있는 삭제하기 버튼 클릭
		$('#modal #deletePostBtn').on('click', function(e) {
			e.preventDefault();
			let postId = $('#modal').data('post-id'); // 동적으로 꺼내오는 작업
			//alert(postId);
			
			// ajax로 글 삭제하기 api 생성
			
			$.ajax({
				// request
				type:"DELETE"
				, url:"/post/delete"
				, data:{"postId":postId}
				
				// response
				, success:function(data) {
					if (data.code == 200) {
						alert(data.result);
						location.href = "/timeline/timeline_view";
					} else {
						alert(data.errorMessage);
					}
				}
				
				, error:function(e) {
					alert("게시글을 삭제하는데 실패했습니다.");
				}
				
			});
		});
		
		// 팔로우 기능
		/* $('.follow-btn').on('click', function() {
			let userId = $(this).data('user-id');
			//alert(userId);
			
			$.ajax({
				// request
				type:"GET"
				, url:"/follow/" + userId
				
				// response
				, success:function(data) {
					if (data.code == 200) {
						alert(data.result);
						location.reload();
					} else {
						alert(errorMessage);
					}
				}
				, error:function(e) {
					alert("팔로우 실패했습니다.");
				}
			});
		}); */
	});
</script>