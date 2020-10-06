package com.daoumarket.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHA256Util {
	public static void main(String[] args) throws Exception {
        System.out.println(sha256("needjarvis"));        
    }
 
	// SHA-256으로 해싱하는 메소드
	public static String sha256(String msg) throws NoSuchAlgorithmException {
	    MessageDigest md = MessageDigest.getInstance("SHA-256");
	    md.update(msg.getBytes());
	    
	    return bytesToHex(md.digest());
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
