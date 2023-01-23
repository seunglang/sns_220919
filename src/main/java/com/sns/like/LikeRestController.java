package com.sns.like;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.sns.like.bo.LikeBO;

import jakarta.servlet.http.HttpSession;

@RestController
public class LikeRestController {
	
	@Autowired
	private LikeBO likeBO;
	
	// toggle 기능 - {postId} 와일드 카드 문법이다.
	// /like?postId=13           @RequestParam()
	// /like/13 위 아래 둘다 같다	 @PathVariable
	@GetMapping("/like/{postId}")
	public Map<String, Object> like(
			@PathVariable int postId,
			HttpSession session,
			Model model) {
		// 로그인 된 사람이 어느 글에 좋아요를 눌렀는지 체크하기
		// 좋아요 누르기에 대한 액션 만들기
		Integer userId = (Integer)session.getAttribute("userId");
		
		Map<String, Object> result = new HashMap<>();
		
		if (userId == null) {
			result.put("code", 500);
			result.put("result", "로그인이 필요합니다.");
			return result;
		}
		
		int existLikeRow = likeBO.existLikeByUserIdPostId(userId, postId);
		
		if (existLikeRow == 0) {
			likeBO.insertLike(userId, postId);
			result.put("code", true);
			//result.put("result", "좋아요가 성공적으로 저장되었습니다.");
		} else if (existLikeRow >= 1) {
			likeBO.deleteLikeByUserIdPostId(userId, postId);
			result.put("code", false);
			//result.put("result", "좋아요 삭제 했습니다.");
		}
		
		model.addAttribute("existLike", existLikeRow);
		return result;
	}
}
