package br.com.cadsys.security.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class EncoderUtil {
	
	public static String PasswordEncode(String password) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder.encode(password);
	}
	
//	public static void main(String args[]) {
//		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//		System.out.println(encoder.encode("123"));
//	}

}
