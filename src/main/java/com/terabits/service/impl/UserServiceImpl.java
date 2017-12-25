package com.terabits.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.terabits.dao.UserDAO;
import com.terabits.domain.Person;
import com.terabits.service.UserService;

import net.sf.json.JSONObject;

/** 
* @author 作者Vladimir E-mail: gyang.shines@gmail.com
* @version 创建时间：2017年12月14日 下午5:07:59 
* 类说明 
*/
@Transactional
@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDAO userDAO;
	
	public JSONObject insert(int id, String name, String tel, double balance, String timestamp, String tablename) {
		// TODO Auto-generated method stub
		JSONObject jsonObject = new JSONObject();
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("ID", id);
			map.put("Name", name);
			map.put("Tel", tel);
			map.put("Balance", balance);
			map.put("timestamp", timestamp);
			map.put("tablename", tablename);
			jsonObject.put("info", userDAO.insertItem(map));
			return jsonObject;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			jsonObject.put("info", 0);
			return jsonObject;
		}
	}

	public JSONObject select(int id, String name, String tel, double balance, String timestamp, int offset, int limit, String tablename) {
		// TODO Auto-generated method stub
		JSONObject jsonObject = new JSONObject(); 
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("ID", id);
			map.put("Name", name);
			map.put("Tel", tel);
			map.put("Balance", balance);
			map.put("timestamp", timestamp);
			map.put("offset", offset);
			map.put("limit", limit);
			map.put("tablename", tablename);
			jsonObject.put("info", userDAO.selectItem(map));
			return jsonObject;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}

	public JSONObject selectFuzzy(int id, String name, String tel, double balance, String timestamp, int offset,
			int limit, String tablename) {
		// TODO Auto-generated method stub
		JSONObject jsonObject = new JSONObject();
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("ID", id);
			map.put("Name", name);
			map.put("Tel", tel);
			map.put("Balance", balance);
			map.put("timestamp", timestamp);
			map.put("offset", offset);
			map.put("limit", limit);
			map.put("tablename", tablename);
			jsonObject.put("info", userDAO.selectItemFuzzy(map));
			return jsonObject;
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
		
	}

	public JSONObject update(int idchange, String namechange, String telchange, double balancechange, String timestampchange, int id, String name, String tel, double balance, String timestamp, String tablename) {
		// TODO Auto-generated method stub
		JSONObject jsonObject = new JSONObject();
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("IDchange", idchange);
			map.put("Namechange", namechange);
			map.put("Telchange", telchange);
			map.put("Balancechange", balancechange);
			map.put("timestampchange", timestampchange);
			map.put("ID", id);
			map.put("Name", name);
			map.put("Tel", tel);
			map.put("Balance", balance);
			map.put("timestamp", timestamp);
			map.put("tablename", tablename);
			jsonObject.put("info", userDAO.updateItem(map));
			return jsonObject;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			jsonObject.put("info", 0);
			return jsonObject;
		}
	}

	public JSONObject updateFuzzy(int idchange, String namechange, String telchange, double balancechange, String timestampchange, int id, String name, String tel, double balance, String timestamp, String tablename) {
		// TODO Auto-generated method stub
		JSONObject jsonObject = new JSONObject();
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("IDchange", idchange);
			map.put("Namechange", namechange);
			map.put("Telchange", telchange);
			map.put("Balancechange", balancechange);
			map.put("timestampchange", timestampchange);
			map.put("ID", id);
			map.put("Name", name);
			map.put("Tel", tel);
			map.put("Balance", balance);
			map.put("timestamp", timestamp);
			map.put("tablename", tablename);
			jsonObject.put("info", userDAO.updateItemFuzzy(map));
			return jsonObject;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			jsonObject.put("info", 0);
			return jsonObject;
		}
	}

	public JSONObject delect(int id, String name, String tel, double balance, String timestamp, String tablename) {
		// TODO Auto-generated method stub
		JSONObject jsonObject = new JSONObject();
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("ID", id);
			map.put("Name", name);
			map.put("Tel", tel);
			map.put("Balance", balance);
			map.put("timestamp", timestamp);
			map.put("tablename", tablename);
			jsonObject.put("info", userDAO.deleteItem(map));			
			return jsonObject;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			jsonObject.put("info", 0);
			return jsonObject;
		}
	}

	public JSONObject delectFuzzy(int id, String name, String tel, double balance, String timestamp, String tablename) {
		// TODO Auto-generated method stub
		JSONObject jsonObject = new JSONObject();
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("ID", id);
			map.put("Name", name);
			map.put("Tel", tel);
			map.put("Balance", balance);
			map.put("timestamp", timestamp);
			map.put("tablename", tablename);
			jsonObject.put("info", userDAO.deleteItemFuzzy(map));
			return jsonObject;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			jsonObject.put("info", 0);
			return jsonObject;
		}
	}

	public JSONObject selecttest(String id,	String tablename) {
		// TODO Auto-generated method stub
		List<Person> persons = new ArrayList<Person>();
		try {
			System.out.println("serviceimpl*******************");
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("ID", id);
			
			map.put("tablename", tablename);
			persons = userDAO.selecttest(map);
			return JSONObject.fromObject(persons);
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}


}
