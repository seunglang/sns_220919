package com.sns.timeline.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.sns.comment.model.Comment;
import com.sns.post.model.Post;
import com.sns.timeline.model.CardView;

@Repository
public interface TimelineDAO {
	
	
	// post 글 목록 가져오기
	public List<CardView> generateCardList();
	
	
	// 글쓴 데이터 db에 insert 구문
		public int insertPost(
				@Param("userId") int userId,
				@Param("content") String content,
				@Param("imagePath") String imagePath);
		
	// 글 목록 호출
		public List<Post> getTimelinePostList();
		
	// 게시글에 저장된 댓글 가져오기
		public List<Comment> getCommentByUserIdPostId(int postId); // 여기서 맵퍼로 postId, userId 비교해서 가져오고 난 후 forEach로 추출
		
}
