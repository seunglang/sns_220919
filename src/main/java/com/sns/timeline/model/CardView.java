package com.sns.timeline.model;

import java.util.List;

import com.sns.comment.model.CommentView;
import com.sns.like.model.LikeView;
import com.sns.post.model.Post;
import com.sns.user.model.User;

// View용 객체
public class CardView {
	// 글 1개
	private Post post;
	
	// 글쓴이 정보
	private User user; // 필드들을 다 가져오거나 객체 자체를 선언해주거나, 둘다 상관없다.
	
	// 댓글 N개
	private List<CommentView> commentList;
	
	// 내가(로그인 된 사람) 좋아요를 눌렀는지 (booelan)
	private boolean filledLike;
	
	// 좋아요 개수
	private List<LikeView> likeList;
	private int likeCount;
	
	

	public int getLikeCount() {
		return likeCount;
	}

	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<CommentView> getCommentList() {
		return commentList;
	}

	public void setCommentList(List<CommentView> commentList) {
		this.commentList = commentList;
	}

	public boolean isFilledLike() {
		return filledLike;
	}

	public void setFilledLike(boolean filledLike) {
		this.filledLike = filledLike;
	}
	public List<LikeView> getLikeList() {
		return likeList;
	}

	public void setLikeList(List<LikeView> likeList) {
		this.likeList = likeList;
	}
}
