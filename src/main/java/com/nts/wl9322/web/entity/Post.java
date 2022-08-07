package com.nts.wl9322.web.entity;

import java.util.Date;

//게시글 객체
public class Post {
	
	private int id;
	private String title;
	private String writer;
	private String content;
	private Date regdate;
	private int hit;
	private int like;
	private int unlike;
	private String hashtag;
	private String password;
	
	public Post() {}

	//생성자 정의
	public Post(int id, String title, String writer, String content, Date regdate, int hit, int like, int unlike,
			String hashtag, String password) {
		this.id = id;
		this.title = title;
		this.writer = writer;
		this.content = content;
		this.regdate = regdate;
		this.hit = hit;
		this.like = like;
		this.unlike = unlike;
		this.hashtag = hashtag;
		this.password = password;
	}
	
	public Post(String title, String writer, String content, String hashtag, String password) {
		this.title = title;
		this.writer = writer;
		this.content = content;
		this.hashtag = hashtag;
		this.password = password;
	}

	//멤버들에 대한 getter setter
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getRegdate() {
		return regdate;
	}

	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}

	public int getHit() {
		return hit;
	}

	public void setHit(int hit) {
		this.hit = hit;
	}

	public int getLike() {
		return like;
	}

	public void setLike(int like) {
		this.like = like;
	}

	public int getUnlike() {
		return unlike;
	}

	public void setUnlike(int unlike) {
		this.unlike = unlike;
	}

	public String getHashtag() {
		return hashtag;
	}

	public void setHashtag(String hashtag) {
		this.hashtag = hashtag;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
