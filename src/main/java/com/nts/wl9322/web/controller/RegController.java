package com.nts.wl9322.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")

public class RegController {
	@RequestMapping("reg")
	public String reg() {
		return "reg";
	}
	@RequestMapping("update")
	public String update() {
		return "reg";
	}
}
