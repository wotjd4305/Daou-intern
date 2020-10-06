package com.daoumarket.util;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import com.daoumarket.dto.User;

public class EncodePassword {

	public static User Encode(User user) {
		AES256Util aes256 = AES256Util.getInstance();
		
		try {
			String encodePassword = aes256.AES_Encode(user.getPassword());
			user.setPassword(encodePassword);
			
		} catch (InvalidKeyException | UnsupportedEncodingException | NoSuchAlgorithmException | NoSuchPaddingException
				| InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException e) {
			e.printStackTrace();
		}
		
		return user;
	}

}
