package com.nts.wl9322.web.entity;

import java.util.Date;

//댓글 객체
public class Comment {
	private int id;
	private int post_id;
	private String content;
	private String writer;
	private String password;
	private Date regdate;
	
	public Comment() {}
	
	//생성자 정의
	public Comment(int id, int post_id, String content, String writer, String password, Date regdate) {
		this.id = id;
		this.post_id = post_id;
		this.content = content;
		this.writer = writer;
		this.password = password;
		this.regdate = regdate;
	}
	
	//맴버 변수 getter, setter
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPost_id() {
		return post_id;
	}

	public void setPost_id(int post_id) {
		this.post_id = post_id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getRegdate() {
		return regdate;
	}

	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	
}
