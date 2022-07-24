package com.spring.aes.util;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Hex;
import org.springframework.stereotype.Component;

@Component
public class Aes {
	
	private String secretKey = "1234567890"; // 비밀키
	
	// 암호화
	public String encryption(String text) {
		try {
			Cipher cipher = Cipher.getInstance("AES");
			
			byte[] key = new byte[16];
			int i = 0;
			
			for(byte b : secretKey.getBytes()) {
				key[i++%16] ^= b;
			}
			
			cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(key, "AES"));
			
			return new String(Hex.encodeHex(cipher.doFinal(text.getBytes("UTF-8")))).toUpperCase();
		} catch(Exception e) {
			return text;
		}
	}
	
	// 복호화
	public String decryption(String encryptedText) {
		try {
			Cipher cipher = Cipher.getInstance("AES");
			
			byte[] key = new byte[16];
			int i = 0;
			
			for(byte b : secretKey.getBytes()) {
				key[i++%16] ^= b;
			}
			
			cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(key, "AES"));
			
			return new String(cipher.doFinal(Hex.decodeHex(encryptedText.toCharArray())));
		} catch(Exception e) {
			return encryptedText;
		}
	}
}
