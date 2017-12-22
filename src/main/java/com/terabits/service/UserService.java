package com.terabits.service;
/** 
* @author 作者Vladimir E-mail: gyang.shines@gmail.com
* @version 创建时间：2017年12月14日 下午5:07:36 
* 类说明 
* 一般service层要根据具体的需求设计函数，用来实现一个web程序的业务逻辑，即与业务逻辑相关的代码应全部放在这一部分，这样便于管理。对于数据的访问，写到DAO层就可以了，不需要再在service层中写接口。
* 这里，因为没有什么特定的业务需求，所以才把数据访问写在了这里
*/

import java.util.Map;

import com.terabits.domain.Person;

import net.sf.json.JSONObject;

public interface UserService {
	
	public JSONObject insert(int id, String name, String tel, double balance, String timestamp, String tablename);
	
	public JSONObject select(int id, String name, String tel, double balance, String timestamp, int offset, int limit, String tablename);
	
	public JSONObject selectFuzzy(int id, String name, String tel, double balance, String timestamp, int offset, int limit, String tablename);
	
	public JSONObject update(int idchange, String namechange, String telchange, double balancechange, String timestampchange, int id, String name, String tel, double balance, String timestamp, String tablename);
	
	public JSONObject updateFuzzy(int idchange, String namechange, String telchange, double balancechange, String timestampchange, int id, String name, String tel, double balance, String timestamp, String tablename);
	
	public JSONObject delect(int id, String name, String tel, double balance, String timestamp, String tablename);
	
	public JSONObject delectFuzzy(int id, String name, String tel, double balance, String timestamp, String tablename);
	
	public Map<String, Object> selecttest(int id, String name, String tel, double balance, String timestamp, int offset, int limit, String tablename);

	public Person selecttest1 (int id, String name, String tel, double balance, String timestamp, int offset, int limit, String tablename);
	
	public Person selecttest2 (int id, String name, String tel, double balance, String timestamp, int offset, int limit, String tablename);
}
