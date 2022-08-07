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
	public int getPostCount();
}
