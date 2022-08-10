package com.nts.wl9322.web.controller;

import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.nts.wl9322.web.entity.Post;
import com.nts.wl9322.web.security.PasswordEncrypt;
import com.nts.wl9322.web.service.PostService;

@Controller
@RequestMapping("/")

public class RegController {
	
	//게시글을 위한 서비스 객체
	@Autowired
	private PostService jdbcPostService;
	//비밀번호 암호화를 위한 보안 객체
	@Autowired
	private PasswordEncrypt sha256PasswordEncrypt;
	
	//등록을 POST 요청으로 받았을 경우
	@RequestMapping(value = "reg", method = RequestMethod.POST)
	public String reg(String title, String writer, String content, String hashtag, String password) throws NoSuchAlgorithmException {
		//해시태그 값의 존재 여부 확인
		if(hashtag == null) hashtag = "";
		//비밀번호 암호화
		String encrypt_password = sha256PasswordEncrypt.encrypt(password);
		
		//게시글을 DB에 저장한 후 목록페이지로 리다이렉트
		Post post = new Post(title, writer, content, hashtag, encrypt_password);
		jdbcPostService.insertPost(post);
		
		return "redirect:index";
	}
	
	//글쓰기 버튼으로 GET 등록 요청이 들어왔을 경우
	@RequestMapping(value = "reg", method = RequestMethod.GET)
	public String regpage() {
		return "reg";
	}
	
	//수정 버튼으로 GET 수정 요청이 들어왔을 경우
	@RequestMapping(value = {"update"}, method = RequestMethod.GET)
	public String update(Model model, int id) {
		//해당 게시글을 얻은 후 페이지에 넘길 모델 설정
		Post post = jdbcPostService.getPost(id);
		//해시태그 분리
		String[] hashtags = post.getHashtag().split(",");
		model.addAttribute("post", post);
		model.addAttribute("hashtags", hashtags);
		return "update";
	}
	
	//수정을 POST 수정 요청이 들어왔을 경우
	@RequestMapping(value = {"update"}, method = RequestMethod.POST)
	public String update(int id, String title, String writer, String content, String hashtag, String password) {
		//비밀번호 암호화
		String encrypt_password = sha256PasswordEncrypt.encrypt(password);
		//성공 시 해당 게시글을 수정 후 원래 페이지로 리다이렉트
		if(jdbcPostService.checkPassword(id, encrypt_password)) jdbcPostService.updatePost(id, title, writer, content, hashtag);
		//실패 시 실패 페이지 요청
		else {
			System.out.println("실패!!!!");
			return "fail";
		}
		
		return "redirect:detail?id=" + id;
	}
	
	//삭제요청이 왔을 경우
	@RequestMapping("delete")
	public String delete(int id, String password) {
		//비밀번호 암호화
		String encrypt_password = sha256PasswordEncrypt.encrypt(password);
		//성공 시 해당 게시글을 DB에서 삭제 후 목록 페이지로 리다이렉트
		if(jdbcPostService.checkPassword(id, encrypt_password)) jdbcPostService.deletePost(id);
		//실패 시 실패 페이지 요청
		else {
			System.out.println("실패!!!!");
			return "fail";
		}
		
		return "redirect:index";
	}
}
