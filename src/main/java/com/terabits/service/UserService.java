package com.terabits.service;
/** 
* @author 作者Vladimir E-mail: gyang.shines@gmail.com
* @version 创建时间：2017年12月14日 下午5:07:36 
* 类说明 
*/

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public interface UserService {
	
	public JSONObject insert(int id, String name, String tel, double balance, String timestamp, String tablename);
	
	public JSONArray select(int id, String name, String tel, double balance, String timestamp, int offset, int limit, String tablename);
	
	public JSONArray selectFuzzy(int id, String name, String tel, double balance, String timestamp, int offset, int limit, String tablename);
	
	public JSONArray update(int id, String name, String tel, double balance, String timestamp, String tablename);
	
	public JSONArray updateFuzzy(int id, String name, String tel, double balance, String timestamp, String tablename);
	
	public JSONArray delect(int id, String name, String tel, double balance, String timestamp, String tablename);
	
	public JSONArray delectFuzzy(int id, String name, String tel, double balance, String timestamp, String tablename);

}
