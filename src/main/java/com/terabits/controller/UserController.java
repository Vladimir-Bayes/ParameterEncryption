package com.terabits.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


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

	@RequestMapping(value = "/select/encrypt", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject selectencrypt(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String id = request.getParameter("id");
		String tablename = request.getParameter("tablename");

		System.out.println("controller**************");
		return userService.selecttest(id, tablename);

	}
}
