package com.sns.post.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.sns.post.model.Post;

@Repository
public interface PostDAO {

	// 테스트
	public List<Map<String, Object>> selectPostListTest();

	// 글 추가하기
	public int insertPost(@Param("userId") int userId, @Param("content") String content,
			@Param("imagePath") String imagePath);

	// 글 목록 가져오기
	public List<Post> selectPostList();
	
	// 글 가져오기
	public Post selectPostByUserIdPostId(
			@Param("postId") int postId,
			@Param("userId") int userId);
	
	// 글 삭제하기
	public int deletePostByPostIdUserId(
			@Param("postId") int postId,
			@Param("userId") int userId);

//	public List<Map<String, Object>> selectListsTest();
//	public List<Post> selectPostList();
//	
//	// 글쓴 데이터 db에 insert 구문
//	public int insertPost(
//			@Param("userId") int userId,
//			@Param("subject") String subject,
//			@Param("content") String content,
//			@Param("imagePath") String imagePath);
}
