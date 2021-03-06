package com.terabits.aspect;

import java.util.Base64;
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
	public void messagePointcut() {
		System.out.println("******************************这个函数好像没什么用********************");
	}

	@Around("messagePointcut()")
	public JSONObject messagePointcut(ProceedingJoinPoint point) throws Throwable {
		System.out.println("进入AOP***************************");
		Object[] args = point.getArgs();
		
//		因为考虑到通过ID来获取每个用户的加密密钥，所以是要么把id字段放在第一个，且不能加密，这样能允许不同的人用不同的密码
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

		System.out.println("方法执行之前***********************");
		JSONObject jsonObject_temp = JSONObject.fromObject( point.proceed(args) );
		JSONObject jsonObject_encrypt = new JSONObject();
		// 返回值加密
		System.out.println("方法执行之后***********************");
		System.out.println(jsonObject_temp);
		@SuppressWarnings("unchecked")
		Iterator<String> sIterator = jsonObject_temp.keys();
		while(sIterator.hasNext()){  
			//获得key  
			String attribute = sIterator.next();  
			// 根据key获得value, value也可以是JSONObject,JSONArray,使用对应的参数接收即可  
			String value = jsonObject_temp.getString(attribute);  
			System.out.println("key: "+attribute);
			System.out.println("value: "+value);
			//对每一个属性对应的属性值加密
			
			byte[] encodeMessage = AESUtil.AESJDKEncode(value, key);
			String value_encrypt = Base64.getEncoder().encodeToString(encodeMessage);
			jsonObject_encrypt.put(attribute, value_encrypt);
		}
		
		// 返回结果
		return jsonObject_encrypt;
	}
}
