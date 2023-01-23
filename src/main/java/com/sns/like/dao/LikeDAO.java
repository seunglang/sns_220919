package com.sns.like.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.sns.like.model.Like;

@Repository
public interface LikeDAO {
	
	// 좋아요 insert 구문
	public int insertLike(
			@Param("userId") int userId,
			@Param("postId") int postId);
	
	// 중복 좋아요 확인 구문
	public int existLikeByUserIdPostId(
			@Param("userId") int userId,
			@Param("postId") int postId);
	
	// 좋아요 삭제 구문
	public void deleteLikeByUserIdPostId(
			@Param("userId") int userId,
			@Param("postId") int postId);
	
	// 좋아요 통째로 가져오는 구문
	public List<Like> selectLikeListByPostId(int postId);
}
