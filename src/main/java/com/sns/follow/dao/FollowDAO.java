package com.sns.follow.dao;

import org.springframework.stereotype.Repository;

@Repository
public interface FollowDAO {
	
	// 팔로우 인서트 구문
	public int insertfollow(int userId);
}
