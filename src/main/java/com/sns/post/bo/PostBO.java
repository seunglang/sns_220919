package com.sns.post.bo;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sns.comment.bo.CommentBO;
import com.sns.common.FileManagerService;
import com.sns.like.bo.LikeBO;
import com.sns.post.dao.PostDAO;
import com.sns.post.model.Post;

@Service
public class PostBO {

	//private Logger logger = LoggerFactory.getLogger(PostBO.class); // 원하는 클래스마다 복붙하자
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private PostDAO postDAO;
	
	@Autowired
	private CommentBO commentBO;
	
	@Autowired
	private LikeBO likeBO;
	
	@Autowired
	private FileManagerService fileManagerService;
	
	// 글 저장
	public int addPost(int userId, String userLoginId, String content, MultipartFile file) {
		String imagePath = null;
		if (file != null) {
			imagePath = fileManagerService.saveFile(userLoginId, file);
		}
		
		return postDAO.insertPost(userId, content, imagePath);
	}
	
	// 글 목록 가져오기
	public List<Post> getPostList() {
		return postDAO.selectPostList();
	}
	
	public Post getPostByUserIdPostId(int postId, int userId) {
		return postDAO.selectPostByUserIdPostId(postId, userId);
	}
	
	public int deletePostByPostIdUserId(int postId, int userId) {
		// 기존 글 가져오기
		Post post = getPostByUserIdPostId(postId, userId);
		if (post == null) {
			logger.warn("[글 삭제] post is null. postId:{}, userId:{}", postId, userId);
			return 0;
		}
		
		// 이미지 있으면 이미지 삭제
		if (post.getImagePath() != null) {
			fileManagerService.deleteFile(post.getImagePath()); // 이미지 패스만 주면 사진을 삭제 해준다.
		}
		// 글 삭제
		int rowCount = postDAO.deletePostByPostIdUserId(postId, userId);
		
		// 댓글들 삭제 - Post에서 불러올지 생각해보자
		commentBO.deleteCommentsByPostId(postId);
		
		// 좋아요들 삭제
		likeBO.deleteLikeByPostId(postId);
		
		return rowCount;
	}
}
