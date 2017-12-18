package com.terabits.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.terabits.dao.UserDAO;
import com.terabits.service.UserService;

import net.sf.json.JSONArray;
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
			map.put("timeistamp", timestamp);
			map.put("tablename", tablename);
			userDAO.insertItem(map);
			
			jsonObject.put("info", 1);
			return jsonObject;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			jsonObject.put("info", 0);
			return jsonObject;
		}
	}

	public JSONArray select(int id, String name, String tel, double balance, String timestamp, int offset, int limit, String tablename) {
		// TODO Auto-generated method stub
		
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("ID", id);
			map.put("Name", name);
			map.put("Tel", tel);
			map.put("Balance", balance);
			map.put("timeistamp", timestamp);
			map.put("offset", offset);
			map.put("limit", limit);
			map.put("tablename", tablename);
			JSONArray jsonArray = JSONArray.fromObject(userDAO.selectItem(map));
			return jsonArray;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}

	public JSONArray selectFuzzy(int id, String name, String tel, double balance, String timestamp, int offset,
			int limit, String tablename) {
		// TODO Auto-generated method stub
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("ID", id);
			map.put("Name", name);
			map.put("Tel", tel);
			map.put("Balance", balance);
			map.put("timeistamp", timestamp);
			map.put("offset", offset);
			map.put("limit", limit);
			map.put("tablename", tablename);
			JSONArray jsonArray = JSONArray.fromObject(userDAO.selectItem(map));
			return jsonArray;
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
		
	}

	public JSONArray update(int id, String name, String tel, double balance, String timestamp, String tablename) {
		// TODO Auto-generated method stub
		JSONArray jsonArray = new JSONArray();
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("ID", id);
			map.put("Name", name);
			map.put("Tel", tel);
			map.put("Balance", balance);
			map.put("timeistamp", timestamp);
			map.put("tablename", tablename);
			userDAO.updateItem(map);
			jsonArray.add(0, 1);
			return jsonArray;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			jsonArray.add(0, 0);
			return jsonArray;
		}
	}

	public JSONArray updateFuzzy(int id, String name, String tel, double balance, String timestamp, String tablename) {
		// TODO Auto-generated method stub
		JSONArray jsonArray = new JSONArray();
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("ID", id);
			map.put("Name", name);
			map.put("Tel", tel);
			map.put("Balance", balance);
			map.put("timeistamp", timestamp);
			map.put("tablename", tablename);
			userDAO.updateItemFuzzy(map);
			jsonArray.add(0, 1);
			return jsonArray;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			jsonArray.add(0, 0);
			return jsonArray;
		}
	}

	public JSONArray delect(int id, String name, String tel, double balance, String timestamp, String tablename) {
		// TODO Auto-generated method stub
		JSONArray jsonArray = new JSONArray();
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("ID", id);
			map.put("Name", name);
			map.put("Tel", tel);
			map.put("Balance", balance);
			map.put("timeistamp", timestamp);
			map.put("tablename", tablename);
			userDAO.deleteItem(map);
			jsonArray.add(0, 1);
			return jsonArray;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			jsonArray.add(0, 0);
			return jsonArray;
		}
	}

	public JSONArray delectFuzzy(int id, String name, String tel, double balance, String timestamp, String tablename) {
		// TODO Auto-generated method stub
		JSONArray jsonArray = new JSONArray();
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("ID", id);
			map.put("Name", name);
			map.put("Tel", tel);
			map.put("Balance", balance);
			map.put("timeistamp", timestamp);
			map.put("tablename", tablename);
			userDAO.deleteItemFuzzy(map);
			jsonArray.add(0, 1);
			return jsonArray;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			jsonArray.add(0, 0);
			return jsonArray;
		}
	}


}
