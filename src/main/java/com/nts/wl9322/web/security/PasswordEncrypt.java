package com.nts.wl9322.web.security;

//비밀번호 암호화를 위한 보안 기능 정의
public interface PasswordEncrypt {
	String encrypt(String password);
}
