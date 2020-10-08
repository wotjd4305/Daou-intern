package com.daoumarket.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.daoumarket.dto.User;

public class SHA256Util {
	// SHA-256으로 해싱하는 메소드
	public static User sha256(User user) throws NoSuchAlgorithmException {
	    MessageDigest md = MessageDigest.getInstance("SHA-256");
	    md.update(user.getPassword().getBytes());
	    
	    String encodePassword = bytesToHex(md.digest());
	    
	    user.setPassword(encodePassword);
	    
	    return user;
	}
 
	// 바이트를 헥스값으로 변환하는 메소드
    public static String bytesToHex(byte[] bytes) {
        StringBuilder builder = new StringBuilder();
        for (byte b: bytes) {
          builder.append(String.format("%02x", b));
        }
        return builder.toString();
    }
    
}
