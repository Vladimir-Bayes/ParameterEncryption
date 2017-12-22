package com.terabits.utils;


import javax.crypto.*;
import javax.crypto.spec.DESKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

public class Test_DES {

	public static final String src = "com.mysql.jdbc.Driver";

	public static final String key = "545253234";

	public static void main(String[] args) throws NoSuchPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException, InvalidKeySpecException, NoSuchProviderException {

		String string ="545253234";
		
		String cryptData = encryptBasedDes(string);
		System.out.println(cryptData);

	
		String decryptedData = decryptBasedDes(cryptData);
		System.out.println(decryptedData);

	}

	public static String encryptBasedDes(String data) throws NoSuchAlgorithmException, InvalidKeyException, InvalidKeySpecException, NoSuchPaddingException, BadPaddingException, IllegalBlockSizeException {

		String encryptedData = null;  
		try {  
			// DES算法要求有一个可信任的随机数源  
			SecureRandom sr = new SecureRandom();  
			DESKeySpec deskey = new DESKeySpec(key.getBytes());  
			// 创建一个密匙工厂，然后用它把DESKeySpec转换成一个SecretKey对象  
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");  
			SecretKey key = keyFactory.generateSecret(deskey);  
			// 加密对象  
			Cipher cipher = Cipher.getInstance("DES");  
			cipher.init(Cipher.ENCRYPT_MODE, key, sr);  
			// 加密，并把字节数组编码成字符串  
			encryptedData = new sun.misc.BASE64Encoder().encode(cipher.doFinal(data.getBytes()));  
		} catch (Exception e) {  
			//             log.error("加密错误，错误信息：", e);  
			throw new RuntimeException("加密错误，错误信息：", e);  
		}  
		return encryptedData; 

	}

	public static String decryptBasedDes(String cryptData) throws NoSuchAlgorithmException, InvalidKeyException, InvalidKeySpecException, NoSuchPaddingException, BadPaddingException, IllegalBlockSizeException {

		String decryptedData = null;  
		try {  
			// DES算法要求有一个可信任的随机数源  
			SecureRandom sr = new SecureRandom();  
			DESKeySpec deskey = new DESKeySpec(key.getBytes());  
			// 创建一个密匙工厂，然后用它把DESKeySpec转换成一个SecretKey对象  
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");  
			SecretKey key = keyFactory.generateSecret(deskey);  
			// 解密对象  
			Cipher cipher = Cipher.getInstance("DES");  
			cipher.init(Cipher.DECRYPT_MODE, key, sr);  
			// 把字符串解码为字节数组，并解密  
			decryptedData = new String(cipher.doFinal(new sun.misc.BASE64Decoder().decodeBuffer(cryptData)));  
		} catch (Exception e) {  
			//            log.error("解密错误，错误信息：", e);  
			throw new RuntimeException("解密错误，错误信息：", e);  
		} 
		return decryptedData;        
	}


}
