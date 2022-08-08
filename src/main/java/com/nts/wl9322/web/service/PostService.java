package com.nts.wl9322.web.service;

import java.util.List;

import com.nts.wl9322.web.entity.Post;
import com.nts.wl9322.web.entity.PostView;

//게시글을 위한 서비스 기능 정의
public interface PostService {
	//게시글 등록
	public int insertPost(Post post);
	//게시글 목록 얻어오기
	public List<PostView> getPostList(String field, String query, int page);
	//전체 게시글 수 얻어오기
	public int getPostCount(String field, String query);
	//특정 게시글 얻어오기
	public Post getPost(int id);
	//좋아요
	public Post likePost(int id);
	//싫어요
	public Post unlikePost(int id);
	//비밀번호 확인
	public boolean checkPassword(int id, String password);
	//수정
	public int updatePost(int id, String title, String writer, String content, String hashtag);
	//삭제
	public int deletePost(int id);
}
