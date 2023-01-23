package com.sns.user.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.user.dao.UserDAO;
import com.sns.user.model.User;

@Service
public class UserBO {

	@Autowired
	private UserDAO userDAO;

	// id 중복 확인 구문
//	public boolean existLoginId(String loginId) {
//		return userDAO.existLoginId(loginId);
//	}
//
//	// 회원가입 정보 db에 insert
//	public void addUser(String loginId, String password, String name, String email) {
//		userDAO.insertUser(loginId, password, name, email);
//	}
//	
//	// 로그인 정보 db에 있는지 확인 구문
	
//	public User getUserByLoginIdPassword(String loginId, String password) {
//		return userDAO.getUserByLoginIdPassword(loginId, password);
//	}

	public int existLoginId(String loginId) {
		return userDAO.existLoginId(loginId);
	}

	public int insertUser(String loginId, String password, String name, String email) {
		return userDAO.insertUser(loginId, password, name, email);
	}
	
	public User getUserByLoginIdPassword(String loginId, String password) {
		return userDAO.selectUserByLoginIdPassword(loginId, password);
	}
	
	public User getUserById(int userId) {
		return userDAO.selectUserById(userId);
	}
}
