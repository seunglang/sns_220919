package com.sns.post.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.sns.post.model.Post;

@Repository
public interface PostDAO {

	public List<Map<String, Object>> selectPostListTest();

	public int insertPost(@Param("userId") int userId, @Param("content") String content,
			@Param("imagePath") String imagePath);

	public List<Post> selectPostList();

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
