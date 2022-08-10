package com.nts.wl9322.web.security.sha256;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.nts.wl9322.web.security.PasswordEncrypt;

//SHA-256으로 암호화
@Component
public class SHA256PasswordEncrypt implements PasswordEncrypt{
	public String encrypt(String password) {
		String result = null;
		
		MessageDigest md;
		try {
			//SHA-256으로 암호화 방식 설정
			md = MessageDigest.getInstance("SHA-256");
			md.update(password.getBytes());
			byte[] pwd = md.digest();
			StringBuffer sb = new StringBuffer();
			//암호화된 암호문을 16진수로 변환
			for(byte b : pwd) sb.append(String.format("%02x", b));
			result = sb.toString();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}
}
