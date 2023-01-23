package com.sns.timeline.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.comment.bo.CommentBO;
import com.sns.comment.model.CommentView;
import com.sns.like.bo.LikeBO;
import com.sns.like.model.LikeView;
import com.sns.post.bo.PostBO;
import com.sns.post.model.Post;
import com.sns.timeline.model.CardView;
import com.sns.user.bo.UserBO;
import com.sns.user.model.User;

@Service
public class TimelineBO {
	
	@Autowired
	private PostBO postBO;
	
	@Autowired
	private UserBO userBO;
	
	@Autowired
	private LikeBO likeBO;
	
	@Autowired
	private CommentBO commentBO;
	
	
	// 응답을 내리기 위한 가공 - 로그인이 되지 않은 사람도 카드 목록이 보여야한다.
	public List<CardView> generateCardList(Integer userId) {
		List<CardView> cardViewList = new ArrayList<>();
		
		// 글 목록 가져오기(post)
		List<Post> postList = postBO.getPostList();
		
		// postList에 대한 반복문 => CardView => cardViewList에 넣는다
		for (Post post : postList) {
			CardView cardView = new CardView();
			// cardView내에서 포스트와 댓글을 하나씩 넣어주자
			
			// 글 채우기
			cardView.setPost(post);
			
			// 글쓴이 채우기
			User user = userBO.getUserById(post.getUserId());
			cardView.setUser(user);
			
			// 댓글들 채우기 - 글 하나에 해당하는 댓글들
			List<CommentView> commentList = commentBO.generateCommentViewListByPostId(post.getId()); // post.getId로써 돌고있는 그 글의 id를 가져온 것
			cardView.setCommentList(commentList);
			

			// 좋아요들 채우기 - 글 하나에 해당하는 좋아요들
			// for문으로 like 갯수만큼 int count에 넣어서 갯수만 전달해보자
			
			List<LikeView> likeList = likeBO.generateLikeViewListByPostId(post.getId());
			
			int likeCount = 0;
			for (int i = 0; i < likeList.size(); i++) {
				likeCount++;
			}
			cardView.setLikeCount(likeCount);
			//cardView.setLikeList(likeList);
			
			// 내가 좋아요를 눌렀는지 filledLike를 채우도록 위와 비슷하게 해보자 (로그인 안된 사람도 에러가 나지 않도록 해보자)
			if(userId != null) {
				int filledLike = likeBO.existLikeByUserIdPostId(userId, post.getId()); // post에 담겨있는 userId를 주면 안됨
				if (filledLike >= 1) {
					cardView.setFilledLike(true); // 유저가 이미 좋아요를 누른 상태일 때
				} else if (filledLike == 0) {
					cardView.setFilledLike(false); // 유저가 좋아요 누르지 않은 상태일 때 false
				}
			} else {
				cardView.setFilledLike(false);
			}
			
			
			// 카드 리스트에 채우기
			cardViewList.add(cardView); // 1:1 맵핑으로 인해 carViewList에 하나씩 채워진다.
		}
		
		
		return cardViewList;
	}
	
	// jsp > controller > timelineBO > Postbo > postDAO - 상호참조 오류X(낮은 단계에 있는 post comment등의 BO가 timelineBO를 부르면 안된다.
										// commentBO => userBO
										// likeBO
										// userBO
	
	
	
	
	
	
	
	
	
	    //글쓰기 내용 db에 insert 구문
//		public int addPost(int userId, String userLoginId, String content, MultipartFile file) {
//			
//			// multipartFile을 그대로 dao에 보낼 수 없다.
//			// 때문에 파일 업로드를 먼저 bo에서 해야함
//			// 파일 업로드 => 경로
//			String imagePath = null;
//			if (file != null) { // 파일이 있으면 순차적으로 기능 수행됨 - 파일 있을때만 수행하고 이미지 경로를 얻어낸다.
//				imagePath = fileManagerService.saveFIle(userLoginId, file); 
//			}
//			
//			// dao insert
//			return timelineDAO.insertPost(userId, content, imagePath);
//		}
	
	
	
	
	// 글 목록 호출
//	public List<Post> getTimelinePostList() {
//		return timelineDAO.getTimelinePostList();
//	}
}
