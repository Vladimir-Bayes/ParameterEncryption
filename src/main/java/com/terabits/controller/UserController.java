package com.terabits.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.terabits.domain.Person;
import com.terabits.service.UserService;

import net.sf.json.JSONObject;

/** 
 * @author 作者Vladimir E-mail: gyang.shines@gmail.com
 * @version 创建时间：2017年12月14日 下午5:07:13 
 * 类说明 
 */

@Controller
public class UserController {

	@Autowired
	private UserService userService;


	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject insert(
			@RequestParam(value = "id") int id,
			@RequestParam(value = "name") String name,
			@RequestParam(value = "tel") String tel,
			@RequestParam(value = "balance") double balance,
			@RequestParam(value = "timestamp") String timestamp,
			@RequestParam(value = "tablename")String tablename) throws Exception {


		return userService.insert(id, name, tel, balance, timestamp,tablename);
	}

	@RequestMapping(value = "/select", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject select(
			@RequestParam(value = "id") int id,
			@RequestParam(value = "name") String name,
			@RequestParam(value = "tel") String tel,
			@RequestParam(value = "balance") double balance,
			@RequestParam(value = "timestamp") String timestamp,
			@RequestParam(value = "offset") int offset,
			@RequestParam(value = "limit") int limit,
			@RequestParam(value = "tablename")String tablename) throws Exception {
		return userService.select(id, name, tel, balance, timestamp, offset, limit,tablename);
	}

	@RequestMapping(value = "/select/fuzzy", method = RequestMethod.POST)
	//	@ResponseBody
	public JSONObject selectfuzzy(
			@RequestParam(value = "id") int id,
			@RequestParam(value = "name") String name,
			@RequestParam(value = "tel") String tel,
			@RequestParam(value = "balance") double balance,
			@RequestParam(value = "timestamp") String timestamp,
			@RequestParam(value = "offset") int offset,
			@RequestParam(value = "limit") int limit,
			@RequestParam(value = "tablename")String tablename) throws Exception {
		return userService.selectFuzzy(id, name, tel, balance, timestamp, offset, limit,tablename);

	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject update(
			@RequestParam(value = "idchange") int idchange,
			@RequestParam(value = "namechange") String namechange,
			@RequestParam(value = "telchange") String telchange,
			@RequestParam(value = "balancechange") double balancechange,
			@RequestParam(value = "timestampchange") String timestampchange,
			@RequestParam(value = "id") int id,
			@RequestParam(value = "name") String name,
			@RequestParam(value = "tel") String tel,
			@RequestParam(value = "balance") double balance,
			@RequestParam(value = "timestamp") String timestamp,
			@RequestParam(value = "tablename")String tablename) throws Exception {
		return userService.update(idchange, namechange, telchange, balancechange, timestampchange, id, name, tel, balance, timestamp, tablename);
	}

	@RequestMapping(value = "/update/fuzzy", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject updatefuzzy(
			@RequestParam(value = "idchange") int idchange,
			@RequestParam(value = "namechange") String namechange,
			@RequestParam(value = "telchange") String telchange,
			@RequestParam(value = "balancechange") double balancechange,
			@RequestParam(value = "timestampchange") String timestampchange,
			@RequestParam(value = "id") int id,
			@RequestParam(value = "name") String name,
			@RequestParam(value = "tel") String tel,
			@RequestParam(value = "balance") double balance,
			@RequestParam(value = "timestamp") String timestamp,
			@RequestParam(value = "tablename")String tablename) throws Exception {
		return userService.updateFuzzy(idchange, namechange, telchange, balancechange, timestampchange, id, name, tel, balance, timestamp, tablename);
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject delete(
			@RequestParam(value = "id") int id,
			@RequestParam(value = "name") String name,
			@RequestParam(value = "tel") String tel,
			@RequestParam(value = "balance") double balance,
			@RequestParam(value = "timestamp") String timestamp,
			@RequestParam(value = "tablename")String tablename) throws Exception {
		return userService.delect(id, name, tel, balance, timestamp,tablename);
	}

	@RequestMapping(value = "/delete/fuzzy", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject deletefuzzy(@RequestParam(value = "id") int id,
			@RequestParam(value = "name") String name,
			@RequestParam(value = "tel") String tel,
			@RequestParam(value = "balance") double balance,
			@RequestParam(value = "timestamp") String timestamp,
			@RequestParam(value = "tablename")String tablename) throws Exception {
		return userService.delectFuzzy(id, name, tel, balance, timestamp,tablename);
	}

	@RequestMapping(value = "/select/test", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject selecttest(
			@RequestParam(value = "id") int id,
			@RequestParam(value = "name") String name,
			@RequestParam(value = "tel") String tel,
			@RequestParam(value = "balance") double balance,
			@RequestParam(value = "timestamp") String timestamp,
			@RequestParam(value = "offset") int offset,
			@RequestParam(value = "limit") int limit,
			@RequestParam(value = "tablename")String tablename) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map = userService.selecttest(id, name, tel, balance, timestamp, offset, limit, tablename);
		System.out.println(map);
		System.out.println(map.toString());
		System.out.println(map.get("Name"));
		System.out.println(map.get("Age"));
		JSONObject jsonObject = JSONObject.fromObject(map);
		return jsonObject;
	}
	
	@RequestMapping(value = "/select/test1", method = RequestMethod.POST)
	@ResponseBody
	public Person selecttest1(
			@RequestParam(value = "id") int id,
			@RequestParam(value = "name") String name,
			@RequestParam(value = "tel") String tel,
			@RequestParam(value = "balance") double balance,
			@RequestParam(value = "timestamp") String timestamp,
			@RequestParam(value = "offset") int offset,
			@RequestParam(value = "limit") int limit,
			@RequestParam(value = "tablename")String tablename) throws Exception {
		Person person = new Person();
		person = userService.selecttest1(id, name, tel, balance, timestamp, offset, limit, tablename);
		System.out.println(person);
		System.out.println(person.toString());
		System.out.println(person.getAge());
		System.out.println(person.getName());
		return person;
	}
	
	@RequestMapping(value = "/select/test2", method = RequestMethod.POST)
	@ResponseBody
	public Person selecttest2(
			@RequestParam(value = "id") int id,
			@RequestParam(value = "name") String name,
			@RequestParam(value = "tel") String tel,
			@RequestParam(value = "balance") double balance,
			@RequestParam(value = "timestamp") String timestamp,
			@RequestParam(value = "offset") int offset,
			@RequestParam(value = "limit") int limit,
			@RequestParam(value = "tablename")String tablename) throws Exception {
		Person person = new Person();
		person = userService.selecttest2(id, name, tel, balance, timestamp, offset, limit, tablename);
		System.out.println(person);
		System.out.println(person.toString());
		System.out.println(person.getAge());
		System.out.println(person.getName());
		System.out.println(person.getMoney());
		System.out.println(person.getSex());
		return person;
	}
}
