package com.nts.wl9322.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nts.wl9322.web.entity.PostView;
import com.nts.wl9322.web.service.PostService;


@Controller
@RequestMapping("/")

public class HomeController {
	
	@Autowired
	private PostService jdbcPostService;
	
	@RequestMapping(value = {"index",""})
	public String index(Model model, Integer page, String field, String query) {
		//======게시글 목록 얻어오기======
		//page값 확인(목록 중 몇 페이지를 요청할지 결정)
		if(page == null || page == 0) page = 1;
		//field값 확인(검색할 분야를 확인)
		if(field == null || field.equals("")) field = "title";
		//query값 확인(검색어)
		if(query == null) query = "";
		List<PostView> list = jdbcPostService.getPostList(field, query, page);
		model.addAttribute("list", list);
		
		//======게시글 수 얻어오기======
		int post_count = jdbcPostService.getPostCount();
		model.addAttribute("post_count", post_count);
		
		return "index";
	}
}
