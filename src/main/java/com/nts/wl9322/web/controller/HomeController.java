package com.nts.wl9322.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nts.wl9322.web.entity.PostView;
import com.nts.wl9322.web.service.CommentService;
import com.nts.wl9322.web.service.PostService;


@Controller
@RequestMapping("/")

public class HomeController {
	
	//게시글을 위한 서비스 객체
	@Autowired
	private PostService jdbcPostService;
	//댓글을 위한 서비스 객체
	@Autowired
	private CommentService jdbcCommentService;
	
	@RequestMapping(value = {"index",""})
	public String index(Model model, Integer page, String field, String query) {
		//게시글 목록 얻어오기
		//page값 확인(목록 중 몇 페이지를 요청할지 결정)
		if(page == null || page == 0) page = 1;
		//field값 확인(검색할 분야를 확인)
		if(field == null || field.equals("")) field = "title";
		//query값 확인(검색어)
		if(query == null) query = "";
		List<PostView> list = jdbcPostService.getPostList(field, query, page);
		model.addAttribute("list", list);
		
		//해당되는 게시글 수 얻어오기
		int post_count = jdbcPostService.getPostCount(field, query);
		model.addAttribute("post_count", post_count);
		
		//전체 게시글 수 얻어오기
		int total_post_count = jdbcPostService.getPostCount("TITLE", "");
		model.addAttribute("total_post_count", total_post_count);
		
		//전체 댓글 수 얻어오기
		int total_comment_count = jdbcCommentService.getCommentTotalCount();
		model.addAttribute("total_comment_count", total_comment_count);
		
		return "index";
	}
}
