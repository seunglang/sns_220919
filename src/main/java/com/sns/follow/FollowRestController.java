package com.sns.follow;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.sns.follow.bo.FollowBO;

import jakarta.servlet.http.HttpSession;

@RestController
public class FollowRestController {
	
	@Autowired
	private FollowBO followBO;
	
	@GetMapping("/follow/{userId}")
	public Map<String, Object> follow(
			@PathVariable int postId,
			HttpSession session,
			Model model) {
		// 로그인 된 사람이 어느 유저에 팔로우를 눌렀는지 체크하기
		// 팔로우 누르기에 대한 액션 만들기
		int userId = (int) session.getAttribute("userId");
		Map<String, Object> result = new HashMap<>();
		
		int followRow = followBO.insertfollow(userId);
		if (followRow > 0) {
			result.put("code", 200);
			result.put("result", "팔로우 성공");
		} else {
			result.put("code", 500);
			result.put("errorMessage", "팔로우 실패");
		}
		
		return result;
	}
}
