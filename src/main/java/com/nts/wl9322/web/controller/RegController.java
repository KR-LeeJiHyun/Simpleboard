package com.nts.wl9322.web.controller;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.nts.wl9322.web.entity.Post;
import com.nts.wl9322.web.security.PasswordEncrypt;
import com.nts.wl9322.web.service.PostService;

@Controller
@RequestMapping("/")

public class RegController {
	
	@Autowired
	private PostService jdbcPostService;
	@Autowired
	private PasswordEncrypt sha256PasswordEncrypt;
	
	@RequestMapping(value = "reg", method = RequestMethod.POST)
	public String reg(String title, String writer, String content, String hashtag, String password) throws NoSuchAlgorithmException {
		//hashtag null확인
		if(hashtag == null) hashtag = "";
		//비밀번호 암호화
		String encrypt_password = sha256PasswordEncrypt.encrypt(password);
		
		Post post = new Post(title, writer, content, hashtag, encrypt_password);
		jdbcPostService.insertPost(post);
		
		return "redirect:index";
	}
	
	@RequestMapping(value = "reg", method = RequestMethod.GET)
	public String regpage() {
		return "reg";
	}
	
	@RequestMapping("update")
	public String update() {
		return "update";
	}
}
