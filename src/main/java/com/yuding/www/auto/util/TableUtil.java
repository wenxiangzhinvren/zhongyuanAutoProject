/**
 * 煜鼎, Copyright 2017-2017, All rights reserved.
 * author      date         time      
 * ─────────────────────────────────────────────
 * 王基鸿     2017年3月31日      上午9:53:17
*/
package com.yuding.www.auto.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.yuding.www.auto.dto.TableEntity;
import com.yuding.www.auto.dto.TableEntity.Column;

import cn.hutool.setting.dialect.Props;
import cn.hutool.setting.dialect.PropsUtil;

/**
 * <p>
 * 描述: 
 * </p>
 * <p>
 * 版权所有: 版权所有(C)2017-2017
 * </p>
 * <p>
 * 公 司: 煜鼎
 * </p>
 * <p>
 * 版本1.0: 2017年3月31日 新建
 * </p> 
 * @author 王基鸿
 * @version 1.0
 */

public class TableUtil {
	private static String[] notCreateColumn ={"id","istatus","tcreateddate","vcreatedby","tmodifieddate","vmodifiedby","vcreatedcomcode","vliccomcode","vlicdepartcode"};
	static{
		Arrays.sort(notCreateColumn);
	}
	public static TableEntity getColunms(String tableName){
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = getMySQLConnection();
			stmt = conn.createStatement();
			//tableName
//			rs = stmt.executeQuery("show full columns from " + tableName);
			rs = stmt.executeQuery(""
					+ "SELECT\r\n"
					+ "	'Field'     = a.name,\r\n"
					+ "	'Key'       =\r\n"
					+ "CASE\r\n"
					+ "	WHEN EXISTS (\r\n"
					+ "	SELECT\r\n"
					+ "		1 \r\n"
					+ "	FROM\r\n"
					+ "		sysobjects \r\n"
					+ "	WHERE\r\n"
					+ "		xtype = 'PK' \r\n"
					+ "		AND parent_obj = a.id \r\n"
					+ "		AND name IN ( SELECT name FROM sysindexes WHERE indid IN ( SELECT indid FROM sysindexkeys WHERE id = a.id AND colid = a.colid ) ) \r\n"
					+ "		) THEN\r\n"
					+ "		'PRI' ELSE '' \r\n"
					+ "	END,\r\n"
					+ "	'Type'       = b.name,\r\n"
					+ "	'Null'     =\r\n"
					+ "CASE\r\n"
					+ "		WHEN a.isnullable= 1 THEN\r\n"
					+ "		'YES' ELSE 'NO' \r\n"
					+ "	END,\r\n"
					+ "  'Comment'   = isnull(g.[value],'')\r\n"
					+ "FROM\r\n"
					+ "	syscolumns a\r\n"
					+ "	LEFT JOIN systypes b ON a.xusertype= b.xusertype\r\n"
					+ "	INNER JOIN sysobjects d ON a.id= d.id \r\n"
					+ "	AND d.xtype= 'U' \r\n"
					+ "	AND d.name<> 'dtproperties'\r\n"
					+ "	LEFT JOIN syscomments e ON a.cdefault= e.id\r\n"
					+ "	LEFT JOIN sys.extended_properties g ON a.id= g.major_id \r\n"
					+ "	AND a.colid= g.minor_id\r\n"
					+ "	LEFT JOIN sys.extended_properties f ON d.id= f.major_id \r\n"
					+ "	AND f.minor_id= 0 \r\n"
					+ "WHERE\r\n"
					+ "	d.name= '"+tableName+"'\r\n"
					+ "ORDER BY\r\n"
					+ "	a.id,\r\n"
					+ "	a.colorder");
			
			TableEntity te = new TableEntity();
			List<Column> list = new ArrayList<Column>();
			while (rs.next()) {
				Column column = te.new Column();
				/**
				    * 主键
				 */
//				if(rs.getString("Field").toLowerCase().contains("id")&&
//						rs.getString("Key").toLowerCase().equals("pri")&&
//						rs.getString("Null").toLowerCase().equals("no")
//						){
//					Column columnid = te.new Column();
//					columnid.setName(rs.getString("Field").toLowerCase());
//					columnid.setType(getJavaType(rs.getString("Type")));
//					te.setFkColumn(columnid);
//				}else {
					//属性 统一小写
					column.setName(rs.getString("Field").toLowerCase());
					//注释格式为   中文名:注释
					column.setComment(rs.getString("Comment").split(":")[0]);
					String type = getJavaType(rs.getString("Type"));
					column.setType(type);
					if(isCreate(column.getName())){
						column.setCreate(true);
					}else{
						column.setCreate(false);
					}
//				}
				list.add(column);
			}
			te.setColumnList(list);
			return te;
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, stmt, rs);
		}
		return null;
	}
	public static Connection getMySQLConnection() throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Props props = PropsUtil.get(System.getProperty("user.dir")+"/src/main/resources/application.properties");
		Connection conn = DriverManager.getConnection(props.getProperty("spring.datasource.url"),
				props.getProperty("spring.datasource.username"), props.getProperty("spring.datasource.password"));
		return conn;
	}
	public static boolean isCreate(String field){
		int index = Arrays.binarySearch(notCreateColumn, field);
		if(index>0){
			return false;
		}
		return true;
	}
	public static String getJavaType(String type){
		if(type.startsWith("varchar")||type.startsWith("char")){
			return "String";
		}
		if(type.startsWith("int")){
			return "Integer";
		}
		if(type.startsWith("double")){
			return "Double";
		}
		
		if(type.startsWith("datetime")){
			return "java.util.Date";
		}
		
		if(type.startsWith("decimal")||type.startsWith("numeric")){
			return "java.math.BigDecimal";
		}
		return type;
	}

	public static void close(Connection conn, Statement stmt, ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if (stmt != null) {
				stmt.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
