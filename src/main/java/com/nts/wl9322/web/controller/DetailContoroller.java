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
	
	@Autowired
	private PostService jdbcPostService;
	@Autowired
	private CommentService jdbcCommentService;
	@Autowired
	private PasswordEncrypt sha256PasswordEncrypt;
	
	@RequestMapping("detail")
	public String detail(Model model, int id) {
		Post post = jdbcPostService.getPost(id);
		String[] hashtags = post.getHashtag().split(",");
		model.addAttribute("post", post);
		model.addAttribute("hashtags", hashtags);
		List<Comment> list = jdbcCommentService.getComment(id); 
		model.addAttribute("list", list);
	
		return "detail";
	}
	
	@RequestMapping("like")
	public String like(Model model, int id) {
		Post post = jdbcPostService.likePost(id);
		String[] hashtags = post.getHashtag().split(",");
		model.addAttribute("post", post);
		model.addAttribute("hashtags", hashtags);
		return "detail";
	}
	
	@RequestMapping("unlike")
	public String unlike(Model model, int id) {
		Post post = jdbcPostService.unlikePost(id);
		String[] hashtags = post.getHashtag().split(",");
		model.addAttribute("post", post);
		model.addAttribute("hashtags", hashtags);
		return "detail";
	}
	
	@RequestMapping("comment")
	public String comment(Model model, int post_id, int group_id, int depth, int order, String writer, String content, String password) {
		
		//비밀번호 암호화
		String encrypt_password = sha256PasswordEncrypt.encrypt(password);
		Comment comment = new Comment(0, post_id, depth, group_id, order, content, writer, encrypt_password, null);
		if(group_id == 0) jdbcCommentService.insertTopComment(comment);
		else jdbcCommentService.insertComment(comment);
		
		return "redirect:detail?id=" + post_id;
	}
}
