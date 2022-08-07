package com.nts.wl9322.web.service;

import com.nts.wl9322.web.entity.Post;

//게시글을 위해 필요한 서비스를 정의할 인터페이스
public interface PostService {
	public int insertPost(Post post);
}
