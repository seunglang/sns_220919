package com.sns.user;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sns.common.EncryptUtils;
import com.sns.user.bo.UserBO;

@RequestMapping("/user")
@RestController
public class UserRestController {

	@Autowired
	private UserBO userBO;

	/**
	 * 
	 * @param loginId
	 * @return
	 */
	@RequestMapping("/is_duplicated_id")
	public Map<String, Object> isDuplicatedId(@RequestParam("loginId") String loginId) {

		boolean isDuplicated = userBO.existLoginId(loginId);

		Map<String, Object> result = new HashMap<>();
		if (isDuplicated) {
			result.put("code", 1);
			result.put("result", true);
		} else {
			result.put("code", 1);
			result.put("result", false);
		}

		return result;
	}

	@PostMapping("sign_up")
	public Map<String, Object> signUp(@RequestParam("loginId") String loginId,
			@RequestParam("password") String password, @RequestParam("name") String name,
			@RequestParam("email") String email) {

		// 비밀번호 해싱(hashing) 혹은 (암호화) - mb5
		// static이 붙어있으면 new를 붙여서 새로운 hip 공간에 굳이 저장을 하지 않아도 사용이 가능하다.
		String hashedPassword = EncryptUtils.md5(password);

		// db insert
		userBO.addUser(loginId, hashedPassword, name, email);

		Map<String, Object> result = new HashMap<>();
		result.put("code", 1);
		result.put("result", "데이터 입력 성공");
		
		return result;
	}
}
