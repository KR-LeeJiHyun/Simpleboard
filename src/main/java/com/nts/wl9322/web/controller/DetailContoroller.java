package com.nts.wl9322.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class DetailContoroller {
	@RequestMapping("detail")
	public String detail() {
		return "detail";
	}
}
