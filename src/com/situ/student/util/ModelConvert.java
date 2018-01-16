package com.situ.student.util;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ModelConvert {
	/**
	 * 用于将rs查询结果封装为List<Map<String, Object>>对象
	 * Map<String, Object>对应结果集里面的一行记录
	 * 这一行数据包含多个<列名，值>
	 * @param resultSet
	 * @return
	 */
	public static List<Map<String, Object>> converList(ResultSet resultSet) {
		//新建一个map list用于存放多条查询记录
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		try {
			ResultSetMetaData metaData = resultSet.getMetaData();//结果集(ResultSet)结构信息，比如字段数，字段名
			int columnCount = metaData.getColumnCount();//返回字段的个数
			while (resultSet.next()) {//迭代ResultSet
				Map<String, Object> map = new HashMap<String, Object>();
				for (int i = 1; i <= columnCount; i++) {//循环所有查询出来字段
					map.put(metaData.getColumnLabel(i), resultSet.getObject(i));
					//metaData.getColumnLabel(i) 得到别名
					//metaData.getColumnName(i) 数据库字段名
				}
				list.add(map);//将封装好的一行记录放到list里面
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
}
