package com.sns.like.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.like.dao.LikeDAO;
import com.sns.like.model.Like;
import com.sns.like.model.LikeView;
import com.sns.user.bo.UserBO;
import com.sns.user.model.User;

@Service
public class LikeBO {
	
	@Autowired
	private LikeDAO likeDAO;
	
	@Autowired
	private UserBO userBO;
	
	// 좋아요 insert 구문
	public int insertLike(int userId, int postId) {
		return likeDAO.insertLike(userId, postId);
	}
	
	// 해당 포스터에 같은 유저가 좋아요를 눌렀는지 확인하는 구문
	public int existLikeByUserIdPostId(int userId, int postId) {
		return likeDAO.existLikeByUserIdPostId(userId, postId);
	}
	
	// 좋아요 delete 구문
	public void deleteLikeByUserIdPostId(int userId, int postId) {
		likeDAO.deleteLikeByUserIdPostId(userId, postId);
	}
	
	// 좋아요들 리스트 가져오기
	public List<Like> getLikeListByPostId(int postId) {
		return likeDAO.selectLikeListByPostId(postId);
	}
	
	// input: 게시글번호
	// output: 해당 게시글에 눌린 좋아요들을 가져온다 (댓글쓴이 정보까지)
	public List<LikeView> generateLikeViewListByPostId(int postId) {
		// 결과물
		List<LikeView> likeViewList = new ArrayList<>();
		
		// 좋아요 목록
		List<Like> likeList = getLikeListByPostId(postId);
		
		// 반복문 => likeView => 결과물에 넣는다.
		for (Like like : likeList) {
			LikeView likeView = new LikeView();
			
			// 좋아요 한개
			likeView.setLike(like);
			
			// 좋아요 누른 유저
			User user = userBO.getUserById(like.getUserId());
			likeView.setUser(user);
			
			// 리스트에 담는다
			likeViewList.add(likeView);
		}
		
		// 결과물 리턴
		return likeViewList;
	}
}
