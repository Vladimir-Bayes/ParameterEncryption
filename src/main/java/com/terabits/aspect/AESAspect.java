package com.terabits.aspect;

import java.util.Iterator;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.terabits.dao.UserDAO;
import com.terabits.utils.AESUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/** 
 * @author 作者Vladimir E-mail: gyang.shines@gmail.com
 * @version 创建时间：2017年12月25日 下午4:38:51 
 * 类说明 
 */
@Component("AESAspect")
@Aspect
public class AESAspect {

	@Autowired
	@Qualifier("userDAO")
	private UserDAO userDAO;
	@Pointcut("execution(* com.terabits.service.UserService.selecttest(..))")
	public void messagePointcut() {}

	@Around("messagePointcut()")
	public JSONObject messagePointcut(ProceedingJoinPoint point) throws Throwable {
		System.out.println("进入AOP***************************");
		Object[] args = point.getArgs();
		String id = (String) args[0];
		//      通过userDAO获取该用户的密钥
		String key = userDAO.getAESKeyByUserId(id);

		System.out.println("对参数进行解密***************************");
		for (int i = 0; i < args.length; i++) {
			byte[] Message = AESUtil.hex2byte((String) args[i]);
			byte[] decodeMessage = AESUtil.AESJDKDecode(Message, key);
			args[i] =  new String(decodeMessage);//AES解密
			System.out.println(args[i]+"*******************");
		}

		//      byte[] Message = AESUtil.hex2byte((String) args[1]);
		//		byte[] decodeMessage = AESUtil.AESJDKDecode(Message, key);
		//		new String(decodeMessage);//AES解密
		//        
		//        AESCodec.decrypt((String) args[1], key);
		// 执行
		System.out.println("方法执行之前***********************");
		JSONObject jsonObject = new JSONObject();
		JSONArray jsonArray = JSONArray.fromObject( point.proceed(args) );
		JSONArray jsonArrayEnCrypt = new JSONArray();
		// 返回值加密
		System.out.println("方法执行之后***********************");
		System.out.println(jsonArray.size());
		try {
			Thread.sleep(2000); //millisecond
		} catch (Exception e) {
		}
		
		for (int i = 0; i < jsonArray.size(); i++) {
			JSONObject jsonObject_temp = jsonArray.getJSONObject(i);
			JSONObject jsonObject_encrypt = new JSONObject(); 
			@SuppressWarnings("unchecked")
			Iterator<String> sIterator = jsonObject_temp.keys();  
			while(sIterator.hasNext()){  
				//获得key  
				String attribute = sIterator.next();  
				// 根据key获得value, value也可以是JSONObject,JSONArray,使用对应的参数接收即可  
				String value = jsonObject_temp.getString(attribute);  
				System.out.println("key: "+attribute+",value: "+value);  
				//对每一个属性对应的属性值加密
				value = AESUtil.byte2hex( AESUtil.AESJDKEncode(value, key) );
				jsonObject_encrypt.put(attribute, value);
			}
			jsonArrayEnCrypt.add(jsonObject_encrypt);
		}
		jsonObject.put("info", jsonArrayEnCrypt);
		
		// 返回结果
		return jsonObject;
	}
}
