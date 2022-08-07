package com.nts.wl9322.web.security.sha256;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.stereotype.Service;

import com.nts.wl9322.web.security.PasswordEncrypt;

//SHA-256으로 password 암호화
@Service
public class SHA256PasswordEncrypt implements PasswordEncrypt{
	public String encrypt(String password) {
		String result = null;
		
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("SHA-256");
			md.update(password.getBytes());
			byte[] pwd = md.digest();
			StringBuffer sb = new StringBuffer();
			for(byte b : pwd) sb.append(String.format("%02x", b));
			result = sb.toString();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}
}
