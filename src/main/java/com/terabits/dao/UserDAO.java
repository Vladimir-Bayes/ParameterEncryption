package com.terabits.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.terabits.domain.Person;
import com.terabits.mapper.UserMapper;

/** 
 * @author 作者Vladimir E-mail: gyang.shines@gmail.com
 * @version 创建时间：2017年12月14日 下午5:08:17 
 * 类说明 
 */
@Repository("userDAO")
public class UserDAO {

	@Autowired
	private UserMapper userMapper;

	public int insertItem(Map<String, Object> map) throws Exception {
		try {
			userMapper.insertItem(map);
			return 1;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return 0;
		}
	}

	public List<Map<String, Object>> selectItem(Map<String, Object> map) throws Exception{
		return userMapper.selectItem(map);
	}

	public List<Map<String, Object>> selectItemFuzzy(Map<String, Object> map) throws Exception{
		return userMapper.selectItemFuzzy(map);
	}

	public int updateItem(Map<String, Object> map) throws Exception{
		try {
			userMapper.updateItem(map);
			return 1;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return 0;
		}
		
	}

	public int updateItemFuzzy(Map<String, Object> map) throws Exception{
		try {
			userMapper.updateItemFuzzy(map);
			return 1;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return 0;
		}
	}

	public int deleteItem(Map<String, Object> map) throws Exception{
		try {
			userMapper.deleteItem(map);
			return 1;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return 0;
		}
	}

	public int deleteItemFuzzy(Map<String, Object> map) throws Exception{
		try {
			userMapper.deleteItemFuzzy(map);
			return 1;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return 0;
		}
	}
	
	public List<Person> selecttest(Map<String, Object> map) throws Exception {
		return userMapper.selecttest(map);
	}
	
	public String getAESKeyByUserId(String id) throws Exception {
//		return userMapper.getPassword(id);
		return "545253234";
	}

}
