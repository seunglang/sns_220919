package com.sns.timeline;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.sns.timeline.bo.TimelineBO;
import com.sns.timeline.model.CardView;

import jakarta.servlet.http.HttpSession;

@Controller
public class TimelineController {
	// layout으로 최종 화면 내리고 조각 페이지로 껴 넣으면 된다.

	@Autowired
	private TimelineBO timelineBO;

	@GetMapping("/timeline/timeline_view") 
	public String timelineView(Model model, HttpSession session) {
		// List<Post> postList = postBO.getPostList();
		// model.addAttribute("postList", postList);
		Integer userId = (Integer) session.getAttribute("userId");
		
		// 로그인 한 유저의 id값을 generate 함수에 전달인자로 넣어줘서 각 유저들 마다 다른 view 페이지를 구성해보자
		List<CardView> cardList = timelineBO.generateCardList(userId); 
		model.addAttribute("cardList", cardList);
		model.addAttribute("viewName", "timeline/timeline");

		return "template/layout";
	}

}
