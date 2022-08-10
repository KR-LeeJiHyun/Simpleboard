package com.nts.wl9322.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nts.wl9322.web.entity.Comment;
import com.nts.wl9322.web.entity.Post;
import com.nts.wl9322.web.security.PasswordEncrypt;
import com.nts.wl9322.web.service.CommentService;
import com.nts.wl9322.web.service.PostService;

@Controller
@RequestMapping("/")
public class DetailContoroller {
	
	//게시글을 위한 서비스 객체
	@Autowired
	private PostService jdbcPostService;
	//댓글을 위한 서비스 객체
	@Autowired
	private CommentService jdbcCommentService;
	//비밀번호 암호화를 위한 보안 객체
	@Autowired
	private PasswordEncrypt sha256PasswordEncrypt;
	
	//게시글 상세
	@RequestMapping("detail")
	public String detail(Model model, int id, Integer page) {
		//게시글을 받은 후 페이지에 넘겨주기
		Post post = jdbcPostService.getPost(id);
		//디비에 ,로 구분되어 있는 해시태그를 분리하여 배열로 변환
		String[] hashtags = post.getHashtag().split(",");
		model.addAttribute("post", post);
		model.addAttribute("hashtags", hashtags);
		
		//댓글 리스트 받은 후 페이지에 넘겨주기
		if(page == null || page == 0) page = 1;
		List<Comment> list = jdbcCommentService.getComment(id, page); 
		model.addAttribute("list", list);
		model.addAttribute("page", page);
		
		//더보기 버튼을 활성화 여부를 판단할 댓글 페이지 수 계산
		int last_page = (int)Math.ceil((double)jdbcCommentService.getCommentCount(id) / 5);
		model.addAttribute("last_page", last_page);
		
		return "detail";
	}
	
	//좋아요
	@RequestMapping("like")
	public String like(Model model, int id) {
		//해당 게시글의 좋아요 수 업데이트
		Post post = jdbcPostService.likePost(id);
		//디비에 ,로 구분되어 있는 해시태그를 분리하여 배열로 변환
		String[] hashtags = post.getHashtag().split(",");
		model.addAttribute("post", post);
		model.addAttribute("hashtags", hashtags);
		return "detail";
	}
	
	//싫어요
	@RequestMapping("unlike")
	public String unlike(Model model, int id) {
		//해당 게시글의 싫어요 수 업데이트
		Post post = jdbcPostService.unlikePost(id);
		//디비에 ,로 구분되어 있는 해시태그를 분리하여 배열로 변환
		String[] hashtags = post.getHashtag().split(",");
		model.addAttribute("post", post);
		model.addAttribute("hashtags", hashtags);
		return "detail";
	}
	
	//댓글 등록
	@RequestMapping("comment")
	public String comment(Model model, int post_id, String writer, String content, String password) {
		
		//비밀번호 암호화
		String encrypt_password = sha256PasswordEncrypt.encrypt(password);
		//댓글등록
		Comment comment = new Comment(0, post_id, content, writer, encrypt_password, null);
		jdbcCommentService.insertComment(comment);
		
		//등록 후 원래 페이지로 리다이렉트
		return "redirect:detail?id=" + post_id;
	}
	
	//댓글 수정
	@RequestMapping("updateComment")
	public String updateComment(int id, int post_id, String writer, String content, String password) {
		
		//비밀번호 암호화
		String encrypt_password = sha256PasswordEncrypt.encrypt(password);
		
		//비밀번호 검증
		//성공 시 댓글을 업데이트 후 원래 페이지로 리다이렉트
		if(jdbcCommentService.checkPassword(id, encrypt_password)) {
			Comment comment = new Comment(id, post_id, content, writer, encrypt_password, null);
			jdbcCommentService.updateComment(comment);
		}
		//실패 시 실패 페이지를 요청
		else {
			System.out.println("실패!!!!");
			return "fail";
		}
		
		return "redirect:detail?id=" + post_id;
	}
	
	//댓글 삭제
	@RequestMapping("deleteComment")
	public String deleteComment(int id, int post_id, String writer, String content, String password) {
		
		//비밀번호 암호화
		String encrypt_password = sha256PasswordEncrypt.encrypt(password);
		
		//성공 시 댓글을 삭제 => 이 때 댓글을 db에서 지우는 것이 아닌 댓글 내용만 ""로 업데이트 후 원래 페이지로 리다이렉트
		if(jdbcCommentService.checkPassword(id, encrypt_password)) {
			Comment comment = new Comment(id, post_id, "", writer, encrypt_password, null);
			jdbcCommentService.updateComment(comment);
		}
		//실패 시 실패 페이지를 요청
		else {
			System.out.println("실패!!!!");
			return "fail";
		}
		
		return "redirect:detail?id=" + post_id;
	}
}
