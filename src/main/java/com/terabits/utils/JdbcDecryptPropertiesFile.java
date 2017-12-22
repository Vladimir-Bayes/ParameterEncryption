package com.terabits.utils;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Properties;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

/** 
 * @author 作者Vladimir E-mail: gyang.shines@gmail.com
 * @version 创建时间：2017年12月22日 下午1:24:44 
 * 类说明 
 */
public class JdbcDecryptPropertiesFile extends PropertyPlaceholderConfigurer {
	@Override
	protected void processProperties(ConfigurableListableBeanFactory beanFactory, Properties props) throws BeansException{
		//读取配置文件中的密文
		String driver = props.getProperty("jdbc.driver");
		String url = props.getProperty("jdbc.url");
		String username = props.getProperty("jdbc.username");
		String password = props.getProperty("jdbc.password");
		//避免包之间的依赖
		if(null != password && "" != password && "null" != password){
			//将密文转换为明文
			String decPassword="";
			String decDriver = "";
			String decUrl = "";
			String decUsername = "";
			try {
				decPassword = Test_DES.decryptBasedDes(password);
				decDriver = Test_DES.decryptBasedDes(driver);
				decUrl = Test_DES.decryptBasedDes(url);
				decUsername = Test_DES.decryptBasedDes(username);
			} catch (InvalidKeyException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvalidKeySpecException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchPaddingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (BadPaddingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalBlockSizeException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//将解密后的密码放入property对象中
			props.setProperty("jdbc.password", decPassword);
			props.setProperty("jdbc.driver", decDriver);
			props.setProperty("jdbc.url", decUrl);
			props.setProperty("jdbc.username", decUsername);
		}
		super.processProperties(beanFactory, props);
	}
}
