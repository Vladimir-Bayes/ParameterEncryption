package com.terabits.utils;

import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/** 
* @author 作者Vladimir E-mail: gyang.shines@gmail.com
* @version 创建时间：2017年12月12日 下午4:43:27 
* 类说明
* byte2hex和hex2byte要成套地用，不然老是出问题 
*/
public class AESUtil {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String content = "{\"sites\":{\"site\":[{\"id\":\"1\",\"name\":\"菜鸟教程\",\"url\":\"www.runoob.com\"}]}}";
		String password = "545253234";
		byte[] encodeMessage = AESJDKEncode(content, password);
		String string = byte2hex(encodeMessage);
		
		System.out.println("AES 加密后为："+string);
//		实际中不太可能拿到encodeMessage这个byte数组，更多情况下，解密端拿到的都是经过编码之后的字符串，因此解密端要先解码再解密
			
		byte[] Message = AESUtil.hex2byte(string);	
		byte[] decodeMessage = AESJDKDecode(Message, password);			
		System.out.println("AES 解密后为："+ new String(decodeMessage));

		// byte2hex和hex2byte要成套地用，不然老是出问题 
		
		String string2 = CHexConver.byte2HexStr(encodeMessage, encodeMessage.length);
		System.out.println("AES 加密后为："+string2);
		byte[] Message2 = CHexConver.hexStr2Bytes(string2);
		byte[] decodeMessage2 = AESJDKDecode(Message2, password);
		System.out.println("AES 解密后为："+ new String(decodeMessage2));
			
	}
	
	public static byte[] AESJDKEncode(String message, String password) {
		try {
			KeyGenerator kgen = KeyGenerator.getInstance("AES");
			kgen.init(128, new SecureRandom(password.getBytes()));
			SecretKey secretKey = kgen.generateKey();
			byte[] enCodeFormat = secretKey.getEncoded();
			SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
			Cipher cipher = Cipher.getInstance("AES");//创建密码器
			cipher.init(Cipher.ENCRYPT_MODE, key);//  初始化
			byte[] result = cipher.doFinal(message.getBytes()); //加密
			return result;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	
	public static byte[] AESJDKDecode(byte[] message, String password) {
		try {
			KeyGenerator kgen = KeyGenerator.getInstance("AES");
			kgen.init(128, new SecureRandom(password.getBytes()));
			SecretKey secretKey = kgen.generateKey();
			byte[] enCodeFormat = secretKey.getEncoded();
			SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
			Cipher cipher = Cipher.getInstance("AES");//创建密码器
			cipher.init(Cipher.DECRYPT_MODE, key);//  初始化
			byte[] result = cipher.doFinal(message);  //加密
			return result;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}

	 
    public static byte[] hex2byte(String strhex) {  
        if (strhex == null) {  
            return null;  
        }  
        int l = strhex.length();  
        if (l % 2 == 1) {  
            return null;  
        }  
        byte[] b = new byte[l / 2];  
        for (int i = 0; i != l / 2; i++) {  
            b[i] = (byte) Integer.parseInt(strhex.substring(i * 2, i * 2 + 2),  
                    16);  
        }  
        return b;  
    }  
  
    public static String byte2hex(byte[] b) {  
        String hs = "";  
        String stmp = "";  
        for (int n = 0; n < b.length; n++) {  
            stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));  
            if (stmp.length() == 1) {  
                hs = hs + "0" + stmp;  
            } else {  
                hs = hs + stmp;  
            }  
        }  
        return hs.toUpperCase();  
    }  
}
