package com.terabits.mapper;
/** 
* @author 作者Vladimir E-mail: gyang.shines@gmail.com
* @version 创建时间：2017年12月14日 下午5:09:18 
* 类说明 
*/

import java.util.List;
import java.util.Map;


public interface UserMapper {
	
	public int insertItem(Map<String, Object> map) throws Exception;
	
	public List<Map<String, Object>> selectItem(Map<String, Object> map) throws Exception;
	
	public List<Map<String, Object>> selectItemFuzzy(Map<String, Object> map) throws Exception;
	
	public int updateItem(Map<String, Object> map) throws Exception;
	
	public int updateItemFuzzy(Map<String, Object> map) throws Exception;
	
	public int deleteItem(Map<String, Object> map) throws Exception;
	
	public int deleteItemFuzzy(Map<String, Object> map) throws Exception;

}
