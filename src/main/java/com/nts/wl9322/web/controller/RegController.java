package com.nts.wl9322.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.nts.wl9322.web.entity.Post;
import com.nts.wl9322.web.service.PostService;

@Controller
@RequestMapping("/")

public class RegController {
	
	@Autowired
	private PostService jdbcPostService;
	
	@RequestMapping(value = "reg", method = RequestMethod.POST)
	public String reg(String title, String writer, String content, String hashtag, String password) {
		System.out.println("post 요청");
		Post post = new Post(title, writer, content, hashtag, password);
		System.out.println(post.getTitle());
		jdbcPostService.insertPost(post);
		
		return "redirect:index";
	}
	
	@RequestMapping(value = "reg", method = RequestMethod.GET)
	public String regpage() {
		System.out.println("get 요청!!!");
		return "reg";
	}
	
	@RequestMapping("update")
	public String update() {
		return "update";
	}
}
