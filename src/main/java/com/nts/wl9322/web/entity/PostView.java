package com.nts.wl9322.web.entity;

import java.util.Date;

//게시글 목록에 표시할 게시글 뷰 객체
public class PostView extends Post {
	
	//게시글 객체를 상속받은 뒤 댓글 수만 추가
	private int cmt_count;
	
	public PostView() {}

	public PostView(int id, String title, String writer, Date regdate, int hit, int like, int cmt_count) {
		super(id, title, writer, "", regdate, hit, like, 0, "", "");
		this.cmt_count = cmt_count;
	}

	public int getCmt_count() {
		return cmt_count;
	}

	public void setCmt_count(int cmt_count) {
		this.cmt_count = cmt_count;
	}
	
}
