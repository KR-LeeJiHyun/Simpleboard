package com.nts.wl9322.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nts.wl9322.web.entity.Post;
import com.nts.wl9322.web.service.PostService;

@Controller
@RequestMapping("/")
public class DetailContoroller {
	
	@Autowired
	private PostService jdbcPostService;
	
	@RequestMapping("detail")
	public String detail(Model model, int id) {
		Post post = jdbcPostService.getPost(id);
		String[] hashtags = post.getHashtag().split(",");
		model.addAttribute("post", post);
		model.addAttribute("hashtags", hashtags);
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
}
