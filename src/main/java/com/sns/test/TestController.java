package com.sns.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sns.post.dao.PostDAO;

@Controller
public class TestController {
	
	@Autowired
	private PostDAO postDAO;
	
	@ResponseBody
	@GetMapping("/test1")
	public String test1() {
		return "테스트1";
	}
	
	@ResponseBody
	@GetMapping("/test2")
	public Map<String, Object> test2() {
		Map<String, Object> map = new HashMap<>();
		map.put("test1", 111);
		map.put("test2", 222);
		map.put("test3", 333);
		return map;
	}
	
	@GetMapping("/test3")
	public String test3() {
		return "test/test";
	}
	
	@ResponseBody
	@GetMapping("/test4")
	public List<Map<String, Object>> test4() {
		return postDAO.selectPostListTest();
	}
}
