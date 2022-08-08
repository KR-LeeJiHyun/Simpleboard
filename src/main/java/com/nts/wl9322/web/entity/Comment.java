package com.nts.wl9322.web.entity;

import java.util.Date;

//댓글 객체
public class Comment {
	private int id;
	private int post_id;
	private int depth;
	private int group_id;
	private int order;
	private String content;
	private String writer;
	private String password;
	private Date regdate;
	
	public Comment() {}
	
	//생성자 정의
	public Comment(int id, int post_id, int depth, int group_id, int order, String content, String writer,
			String password, Date regdate) {
		this.id = id;
		this.post_id = post_id;
		this.depth = depth;
		this.group_id = group_id;
		this.order = order;
		this.content = content;
		this.writer = writer;
		this.password = password;
		this.regdate = regdate;
	}
	
	//맴버 getter, setter
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

	public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}

	public int getGroup_id() {
		return group_id;
	}

	public void setGroup_id(int group_id) {
		this.group_id = group_id;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
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
