package com.sns.comment.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.sns.comment.model.Comment;

@Repository
public interface CommentDAO {

	public void insertComment(
			@Param("userId") int userId, 
			@Param("postId") int postId, 
			@Param("content") String content);
	
	public List<Comment> selectCommentListByPostId(int postId);
	
	// 댓글들 삭제
	public void deleteCommentsByPostId(@Param("postId") int postId);
	
//	public void insertComment(@Param("userId") int userId, @Param("postId") int postId,
//			@Param("content") String content);
//
//	public List<Comment> selectCommentListByPostId(int postId);
//
//	// 게시글에 저장된 댓글 가져오기
//	public List<Comment> getCommentByUserIdPostId(int postId); // 여기서 맵퍼로 postId, userId 비교해서 가져오고 난 후 forEach로 추출

	// 내 코드 - 선생님 코드와 비교분석
//	public int insertComment(
//			@Param("content") String content,
//			@Param("userId") int userId);
}
