package com.sns.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/user")
@Controller
public class UserController {
	
	// http://localhost:8080/user/sign_up_view
	@GetMapping("/sign_up_view")
	public String signUp(Model model) {
		model.addAttribute("viewName", "user/signUp");
		return "template/layout";
	}
	
	/**
	 * 로그인 화면
	 * http://localhost:8080/user/sign_in_view
	 */
	@GetMapping("/sign_in_view")
	public String signInView(Model model) {
		model.addAttribute("viewName", "user/signIn");
		return "template/layout";
	}
}
