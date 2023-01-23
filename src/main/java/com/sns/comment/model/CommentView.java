package com.sns.comment.model;

import com.sns.user.model.User;

// 댓글 한개와 맵핑
public class CommentView {
	// 이 객체가 필요로 하는 건 - 댓글 한개(댓글 한개에 대한 댓글쓴이 정보)
	
	// 댓글 한개
	private Comment comment;

	// 댓글쓴이
	private User user;
	
	

	public Comment getComment() {
		return comment;
	}

	public void setComment(Comment comment) {
		this.comment = comment;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
