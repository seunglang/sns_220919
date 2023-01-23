package com.sns.comment;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sns.comment.bo.CommentBO;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/comment")
@RestController
public class CommentRestController {

	@Autowired
	private CommentBO commentBO;

	// @GetMapping("/create")
	@PostMapping("/create")
	public Map<String, Object> createComment(@RequestParam("postId") int postId,
			@RequestParam("content") String content, HttpSession session) {

		Map<String, Object> result = new HashMap<>();

		Integer userId = (Integer) session.getAttribute("userId");
		if (userId == null) {
			result.put("code", 500); // 비로그인
			result.put("result", "error");
			result.put("errorMessage", "로그인을 다시 해주세요.");
			return result;
		}

		commentBO.createComment(userId, postId, content);
		result.put("code", 1); // 성공
		result.put("result", "success");

		return result;
	}

	// timeline/timeline_view 화면에서 댓글 가져오기 api
//		@GetMapping("/get_comment_list")
//		public Map<String, Object> getCommentList(
//				@RequestParam("postId") int postId,
//				HttpSession session, Model model) {
//			
//			Integer userId = (Integer) session.getAttribute("userId");
//			
//			Map<String, Object> result = new HashMap<>();
//			
//			//timelineBO
//			List<Comment> comment = commentBO.getCommentByUserIdPostId(postId);
//			
//			if (comment != null) {
//				result.put("code", 200);
//				result.put("result", "댓글을 성공적으로 가져왔습니다.");
//				model.addAttribute("comment", comment);
//				// List<Map> 형식으로 List에 다 넣어보자
//			} else {
//				result.put("code", 404);
//				result.put("result", "실패하였습니다.");
//			}
//			
//			
//			return result;
//		}

	// 내 코드 - 선생님꺼랑 비교분석
//	@GetMapping("/create")
//	public Map<String, Object> create(
//			@RequestParam("addComment") String content,
//			@RequestParam("userId") int userId) {
//		
//		Map<String, Object> result = new HashMap<>();
//		// db에 댓글 인서트
//		//commentBO.addComment(content, userId);
//		
//		int isCommentAdded = commentBO.addComment(content, userId);
//		
//		if (isCommentAdded > 0) {
//			result.put("code", 1);
//			result.put("result", "댓글이 성공적으로 저장되었습니다.");
//		} else {
//			result.put("code", 500);
//			result.put("errorMessage", "댓글 저장에 실패했습니다.");
//		}
//		
//		
//		return result;
//	}
}
