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
	
	// 좋아요 postId로만 삭제하기
	public void deleteLikeByPostId(int postId);
	
	// 좋아요 통째로 가져오는 구문
	public List<Like> selectLikeListByPostId(int postId);
	
	
	// 선생님 코드  -- 좋아요 확인 구문
//	public boolean existLike(
//			@Param("userId") int userId,
//			@Param("postId") int postId);
	
	// 선생님 코드 - 좋아요 반복문 없이 가져오게 해주는 구문
//	public int selectLikeCountByPostId(int postId);
	
	// 선생님 코드 - 비슷한 쿼리를 한번에 사용할 수 있게 해주는 구문
	public int selectLikeCountByPostIdOrUserId(
			@Param("userId") int userId,
			@Param("postId") Integer postId);
}
