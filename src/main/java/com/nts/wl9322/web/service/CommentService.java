package com.nts.wl9322.web.service;

import java.util.List;

import com.nts.wl9322.web.entity.Comment;

//댓글을 위한 서비스 기능 정의
public interface CommentService {
	//댓글 등록
	public int insertComment(Comment comment);
	//댓글 개수
	public int getCommentCount(int post_id);
	//전체 댓글 개수
	public int getCommentTotalCount();
	//댓글 얻어오기
	public List<Comment> getComment(int post_id, int page);
	//댓글 삭제 및 업데이트
	public int updateComment(Comment comment);
	//비밀번호 확인
	public boolean checkPassword(int id, String password);
}
