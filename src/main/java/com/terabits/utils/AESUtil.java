package com.terabits.utils;
import java.security.SecureRandom;
import java.util.Base64;

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
//		String content = "{\"sites\":{\"site\":[{\"id\":\"1\",\"name\":\"菜鸟教程\",\"url\":\"www.runoob.com\"}]}}";
//		String content = "[{\"key\":\"id\",\"value\":\"-1\",\"equals\":true,\"description\":\"\",\"enabled\":true},{\"key\":\"name\",\"value\":\"A\",\"equals\":true,\"description\":\"\",\"enabled\":true},{\"key\":\"tel\",\"value\":\"\",\"equals\":true,\"description\":\"\",\"enabled\":true},{\"key\":\"balance\",\"value\":\"-1\",\"equals\":true,\"description\":\"\",\"enabled\":true},{\"key\":\"timestamp\",\"value\":\"\",\"equals\":true,\"description\":\"\",\"enabled\":true},{\"key\":\"tablename\",\"value\":\"test1\",\"equals\":true,\"description\":\"\",\"enabled\":true},{\"key\":\"offset\",\"value\":\"0\",\"equals\":true,\"description\":\"\",\"enabled\":true},{\"key\":\"limit\",\"value\":\"10\",\"equals\":true,\"description\":\"\",\"enabled\":true}]";
//		String content = "{\"info\":[{\"Tel\":\"34567890123\",\"ID\":\"4\",\"Balance\":\"44.0\",\"Name\":\"Catd\",\"timestamp\":{\"date\":18,\"day\":1,\"hours\":14,\"minutes\":31,\"month\":11,\"nanos\":0,\"seconds\":52,\"time\":1513578712000,\"timezoneOffset\":-480,\"year\":117}},{\"Tel\":\"12345678901\",\"ID\":\"1\",\"Balance\":\"14\",\"Name\":\"Ana\",\"timestamp\":{\"date\":5,\"day\":2,\"hours\":9,\"minutes\":55,\"month\":11,\"nanos\":0,\"seconds\":0,\"time\":1512438900000,\"timezoneOffset\":-480,\"year\":117}}]}";
		String content = "[{\"ID\":\"1\",\"age\":15,\"money\":15.5,\"name\":\"Ana\",\"sex\":\"男\",\"tel\":\"12345678901\"},{\"ID\":\"1\",\"age\":16,\"money\":15.4,\"name\":\"Dog\",\"sex\":\"男\",\"tel\":\"45678901234\"}]";
		String password = "545253234";
		byte[] encodeMessage = AESJDKEncode(content, password);
		String string = Base64.getEncoder().encodeToString(encodeMessage);
		
		System.out.println("AES 加密后为："+string);
//		实际中不太可能拿到encodeMessage这个byte数组，更多情况下，解密端拿到的都是经过编码之后的字符串，因此解密端要先解码再解密
			
		byte[] Message = Base64.getDecoder().decode(string);
		byte[] decodeMessage = AESJDKDecode(Message, password);			
		System.out.println("AES 解密后为："+ new String(decodeMessage));
		
		
		String encrypt_dataString = "7IjjwdlC3iUyqvgbi2G2PQSUdHqossVhiSw6LicUGYDlpnx8Qp34qpubKkB+TIaA/jjz7u7u+BSH5m/TTT7AKsroTUdGK/+A7wxGYCIcTGFnhw4Eutr6MDN3iHO/68LQ+CJNeoGe2A0KtuUC2dhfIO8j3uhQvlR95qD3YmjOaJU79if89Ft2IxOEdPKDCx9CRb/y1xEfTbmsLOhw3FFUbQ==";
		System.out.println(encrypt_dataString.equals(string));
		
		byte[] Message2 = Base64.getDecoder().decode(encrypt_dataString);
		byte[] decodeMessage2 = AESJDKDecode(Message2, password);
		System.out.println("AES 解密后为：" + new String(decodeMessage2));
			
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
