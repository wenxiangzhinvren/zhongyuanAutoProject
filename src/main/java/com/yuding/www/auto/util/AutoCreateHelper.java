/**
 * 煜鼎, Copyright 2017-2017, All rights reserved.
 * author      date         time      
 * ─────────────────────────────────────────────
 * 王基鸿     2017年3月31日      上午10:33:57
*/
package com.yuding.www.auto.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import com.yuding.www.auto.dto.TableEntity;
import com.yuding.www.auto.dto.TableEntity.Column;
import com.yuding.www.auto.form.FastDevForm;

import freemarker.core.ParseException;
import freemarker.template.Configuration;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;

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

public class AutoCreateHelper {
	private String tableName;
//	private String pagePath;
	private String classPath;
	private String actionPath;
	private String md1;
	private String md2;
	private String md3;
	private String clazz;
	private String className;
	private TableEntity tableEntity;
	@SuppressWarnings("deprecation")
	private Configuration cfg = new Configuration();
	
	public void createAll(String tempPath,String classPath,FastDevForm form){
		init(tempPath,classPath,form);
		createEntity();
//		createForm();
		createController();
		createService();
//		createServiceImpl();
		crateRepository();
		crateResource();
//		crateSpec();
	}
	
	public void createController() {
		String path = classPath + "web/capi/v1/";
		String autoName = "";
		if(StringUtils.isNotEmpty(md1)) {
			autoName += firstToUper(md1);
		}
		if(StringUtils.isNotEmpty(md2)) {
			autoName += firstToUper(md2);
		}
		if(StringUtils.isNotEmpty(md3)) {
			autoName += firstToUper(md3);
		}
		path += autoName+"Controller.java";
		Map<String, String> map = new HashMap<String, String>();
		map.put("md1", md1);
		map.put("md2", md2);
		map.put("className1", autoName);
		map.put("className2", firstToLower(autoName));
		map.put("clazz", clazz);
		map.put("actionPath", actionPath.substring(1, actionPath.length() - 1));
		map.put("clazzName", className);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd");
		SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm:ss");
		map.put("date", sdf.format(new Date()));
		map.put("time", sdf2.format(new Date()));
		String tempStr =  getTemp("Controller.java",map);
		writer(path,tempStr);
	}
	
	public void crateResource(){
		String path = classPath + "mapper/";
		String autoName = "";
		if(StringUtils.isNotEmpty(md1)) {
			autoName += firstToUper(md1);
		}
		if(StringUtils.isNotEmpty(md2)) {
			autoName += firstToUper(md2);
		}
		if(StringUtils.isNotEmpty(md3)) {
			autoName += firstToUper(md3);
		}
		path += firstToLower(autoName)+"/"+autoName+"Resource.java";
		Map<String, String> map = new HashMap<String, String>();
		map.put("md1", md1);
		map.put("md2", md2);
		map.put("className1", autoName);
		map.put("className2", firstToLower(autoName));
		map.put("clazz", clazz);
		map.put("clazzName", className);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd");
		SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm:ss");
		map.put("date", sdf.format(new Date()));
		map.put("time", sdf2.format(new Date()));
		String tempStr = getTemp("Resource.java",map);
		writer(path,tempStr);
	}
	
	public void createService(){
		String path = classPath + "service/";
		String autoName = "";
		if(StringUtils.isNotEmpty(md1)) {
			autoName += firstToUper(md1);
		}
		if(StringUtils.isNotEmpty(md2)) {
			autoName += firstToUper(md2);
		}
		if(StringUtils.isNotEmpty(md3)) {
			autoName += firstToUper(md3);
		}
		path += autoName+"Service.java";
		Map<String, String> map = new HashMap<String, String>();
		map.put("md1", md1);
		map.put("md2", md2);
		map.put("className1", autoName);
		map.put("className2", firstToLower(autoName));
		map.put("clazz", clazz);
		map.put("clazzName", className);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd");
		SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm:ss");
		map.put("date", sdf.format(new Date()));
		map.put("time", sdf2.format(new Date()));
		String tempStr = getTemp("Service.java",map);
		writer(path,tempStr);
	}
	
	public void crateRepository(){
		String path = classPath + "repository/";
		String autoName = "";
		if(StringUtils.isNotEmpty(md1)) {
			autoName += firstToUper(md1);
		}
		if(StringUtils.isNotEmpty(md2)) {
			autoName += firstToUper(md2);
		}
		if(StringUtils.isNotEmpty(md3)) {
			autoName += firstToUper(md3);
		}
		path += autoName+"Repository.java";
		Map<String, String> map = new HashMap<String, String>();
		map.put("md1", md1);
		map.put("md2", md2);
		map.put("className1", autoName);
		map.put("className2", firstToLower(autoName));
		map.put("clazz", clazz);
		map.put("clazzName", className);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd");
		SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm:ss");
		map.put("date", sdf.format(new Date()));
		map.put("time", sdf2.format(new Date()));
		String tempStr = getTemp("Repository.java",map);
		writer(path,tempStr);
}
	
	public void createEntity(){
		String className = firstToUper(md1)+firstToUper(md2)+(StringUtils.isNotEmpty(md3) ? firstToUper(md3) : "");
		String filePath = classPath+"domain/"+className+ ".java";
		Map<String, String> map = new HashMap<String, String>();
		map.put("md1", md1);
		map.put("md2", md2);
		map.put("importPackage", getImportPackage());
		map.put("tableName", tableName);
		map.put("className", className);
		map.put("fields", getFields());
//		map.put("fkColunm", getIdFields(tableEntity.getFkColumn()));
//		map.put("methodsId", getMethods(tableEntity.getFkColumn()));
//		map.put("methods", getMethods());
		
		String tempStr = getTemp("Entity.java",map);
		writer(filePath,tempStr);
	}
	
	public void createForm() {
		String className = firstToUper(md2) + md3 + "Form";
		String filePath = classPath+"form/"+className+ ".java";
		Map<String, String> map = new HashMap<String, String>();
		map.put("md1", md1);
		map.put("md2", md2);
		map.put("importPackage", getImportPackage());
		map.put("className", className);
		map.put("fields", getFields());
		map.put("methods", getMethods());
		map.put("fkColunm", getIdFields(tableEntity.getFkColumn()));
		map.put("methodsId", getMethods(tableEntity.getFkColumn()));
		String tempStr = getTemp("Form.java",map);
		writer(filePath,tempStr);
	}
	
	public void crateSpec(){
		String path = classPath + "specification/" + firstToUper(md2) + md3 + "Spec.java";
			Map<String, String> map = new HashMap<String, String>();
			map.put("md1", md1);
			map.put("md2", md2);
			map.put("className1", firstToUper(md2) + md3);
			map.put("clazz", clazz);
			map.put("clazzName", className);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd");
			SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm:ss");
			map.put("date", sdf.format(new Date()));
			map.put("time", sdf2.format(new Date()));
			String tempStr = getTemp("Spec.java",map);
			writer(path,tempStr);
	}
	
	public void createServiceImpl(){
			String path =  classPath + "service/impl/" + firstToUper(md2) + md3 + "ServiceImpl.java";
			Map<String, String> map = new HashMap<String, String>();
			map.put("md1", md1);
			map.put("md2", md2);
			map.put("className1", firstToUper(md2) + md3);
			map.put("className2", md2 + md3);
			map.put("clazz", clazz);
			map.put("clazzName", className);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd");
			SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm:ss");
			map.put("date", sdf.format(new Date()));
			map.put("time", sdf2.format(new Date()));
			String tempStr = getTemp("ServiceImpl.java",map);
			writer(path,tempStr);
	}
	
	
	private String getThs(){
		Map<String, String> map = new HashMap<String, String>();
		map.put("p1", "{");
		map.put("p2", md1);
		map.put("p3", md2 + md3);
		map.put("p4", "columnName");
		map.put("p5", "zhCn");
		map.put("p6", "}");
		String tempThStr = getTemp("list-th.html",map);
		StringBuffer ths = new StringBuffer();
		List<Column> list = tableEntity.getColumnList();
		for(Column c:list){
			if(c.isCreate()){
				ths.append(tempThStr.replaceAll("columnName", c.getName()).replaceAll("zhCn", c.getComment())).append("\n");
			}
		}
		return ths.toString();
	}
	
	public String getAddRow(){
		Map<String, String> map = new HashMap<String, String>();
		map.put("p1", "{");
		map.put("p2", md1);
		map.put("p3", md2 + md3);
		map.put("p4", "columnName");
		map.put("p5", "zhCn");
		map.put("p6", "}");
		String tempThStr = getTemp("add-cl.html",map);
		StringBuffer ths = new StringBuffer();
		ths.append("<div class=\"row\">\n");
		int i = 0;
		List<Column> list = tableEntity.getColumnList();
		for(Column c:list){
			if(c.isCreate()){
				if (i % 4 == 0 && i > 0) {
					ths.append("</div>\n");
					ths.append("<div class=\"row\">\n");
				}
				i++;
				ths.append(tempThStr.replaceAll("columnName", c.getName()).replaceAll("zhCn", c.getComment())).append("\n");
			}
		}
		ths.append("</div>\n");
		return ths.toString();
	}
	
	public String getEditRow(){
		Map<String, String> map = new HashMap<String, String>();
		map.put("p1", "{");
		map.put("p2", md1);
		map.put("p3", md2 + md3);
		map.put("p4", "columnName");
		map.put("p5", "zhCn");
		map.put("p6", "}");
		map.put("p7", "modelName");
		String tempThStr = getTemp("edit-cl.html",map);
		StringBuffer ths = new StringBuffer();
		ths.append("<div class=\"row\">\n");
		int i = 0;
		List<Column> list = tableEntity.getColumnList();
		for(Column c:list){
			if(c.isCreate()){
				if (i % 4 == 0 && i > 0) {
					ths.append("</div>\n");
					ths.append("<div class=\"row\">\n");
				}
				i++;
				ths.append(tempThStr.replaceAll("columnName", c.getName()).replaceAll("zhCn", c.getComment()).replaceAll("modelName",
						"\\$\\{md." + c.getName() + "\\}")).append("\n");
			}
		}
		ths.append("</div>\n");
		return ths.toString();
	}
	
	
	private String getImportPackage(){
		StringBuffer sb = new StringBuffer();
		for(Column c:tableEntity.getColumnList()){
			if(!c.isCreate()){
				continue;
			}
			String type = c.getType();
			if(type.contains(".")&&sb.indexOf(type)==-1){
				sb.append("import "+type+";\n");
			}
		}
		return sb.toString();
	}
	private String getFields(){
		StringBuffer fields = new StringBuffer();
		int count = 0;
		for(Column c:tableEntity.getColumnList()){
			if(!c.isCreate()){
				continue;
			}
			if(
					c.getName().toLowerCase().equals("id")||
					c.getName().toLowerCase().equals("office_code")||
					c.getName().toLowerCase().equals("create_by_name")||
					c.getName().toLowerCase().equals("update_by_name")||
					c.getName().toLowerCase().equals("create_by")||
					c.getName().toLowerCase().equals("create_time")||
					c.getName().toLowerCase().equals("update_by")||
					c.getName().toLowerCase().equals("update_time")||
					c.getName().toLowerCase().equals("is_void")||
					c.getName().toLowerCase().equals("loc_time_zone")||
					c.getName().toLowerCase().equals("version")
					) {
				continue;
			}
			if(count == 0) {
				fields.append("/**"+c.getComment()+"*/").append("\n");
				count++;
			}else {
				fields.append("\t/**"+c.getComment()+"*/").append("\n");
				count++;
			}
			
			fields.append("\t@Column(name=\""+c.getName().toUpperCase()+"\")").append("\n");
			String type = getType(c.getType());
			String[] fieldNames = c.getName().split("_");
			String fieldName = "";
			for(int i=0;i<fieldNames.length;i++) {
				if(i==0) {
					fieldName += fieldNames[i];
				}else {
					fieldName += this.firstToUper(fieldNames[i]);
				}
			}
			fields.append("\tprivate ").append(type).append(" ").append(fieldName).append(";").append("\n");
		}
		return fields.toString();
	}
	private String getIdFields(Column fkColunm) {
		StringBuffer fields = new StringBuffer();
		fields.append("\tprivate ").append(fkColunm.getType()).append(" ").append(fkColunm.getName()).append(";").append("\n");
		return fields.toString(); 
	}
	
	private String getMethods(Column fkColunm){
		StringBuffer methods = new StringBuffer();
		String field = fkColunm.getName();
		String type = getType(fkColunm.getType());
		methods.append("\tpublic  void set").append(firstToUper(field)).append("("+type+" "+field+"){").append("\n");
		methods.append("\t\tthis."+field+" = "+field+";").append("\n");
		methods.append("\t}").append("\n");
		methods.append("\tpublic "+type+" get"+firstToUper(field)+"(){").append("\n");
		methods.append("\t\treturn this."+field+";").append("\n");
		methods.append("\t}").append("\n");	
		return methods.toString();
		
	}
	
	public String getType(String type){
		if(type.contains(".")){
			return type.substring(type.lastIndexOf(".")+1,type.length());
		}
		return type;
	}
	private String getMethods(){
		StringBuffer methods = new StringBuffer();
		for(Column c:tableEntity.getColumnList()){
			if(!c.isCreate()){
				continue;
			}
			String field = c.getName();
			String type = getType(c.getType());
			methods.append("\tpublic  void set").append(firstToUper(field)).append("("+type+" "+field+"){").append("\n");
			methods.append("\t\tthis."+field+" = "+field+";").append("\n");
			methods.append("\t}").append("\n");
			methods.append("\tpublic "+type+" get"+firstToUper(field)+"(){").append("\n");
			methods.append("\t\treturn this."+field+";").append("\n");
			methods.append("\t}").append("\n");	
		}
		return methods.toString();
		
	}
	public void init(String tempPath,String classPath,FastDevForm form){
		this.tableName=form.getTableName().toLowerCase();
		String[]tableNames = tableName.split("_");
		if(tableNames.length>0) {
			this.md1 = tableNames[0];
		}
		if(tableNames.length>1) {
			this.md2 = tableNames[1];
		}
		if(tableNames.length>2) {
			this.md3 = tableNames[2];
		}
		this.classPath = classPath;
		this.actionPath = "/" + md1 + "-" + md2 + (StringUtils.isNotEmpty(md3) ? ("-" + md3 + "/") : "/");
		className = firstToUper(md1)+firstToUper(md2)+(StringUtils.isNotEmpty(md3) ? firstToUper(md3) : "");
		this.clazz = "com.mims.csms.ky.salary.domain."+className;
		try {
			cfg.setDirectoryForTemplateLoading(new File(tempPath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.tableEntity = TableUtil.getColunms(this.tableName);
	} 
	private String firstToUper(String str) {
		return str.substring(0, 1).toUpperCase() + str.substring(1);
	}
	private String firstToLower(String str) {
		return str.substring(0, 1).toLowerCase() + str.substring(1);
	}
	private String getTemp(String tempName,Map<String,String> map){
		try {
			Template temp = cfg.getTemplate(tempName);
			return FreeMarkerTemplateUtils.processTemplateIntoString(temp, map);
		} catch (TemplateNotFoundException e) {
			e.printStackTrace();
		} catch (MalformedTemplateNameException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TemplateException e) {
			e.printStackTrace();
		}
		return "";
	}
	public void writer(String path, String temp) {
		String dir = path.substring(0,path.lastIndexOf("/"));
		File dirFile = new File(dir);
		if(!dirFile.exists()){
			dirFile.mkdirs();
		}
		
		File file = new File(path);
		if (file.exists()) {
			return;
		}
		try {
			Writer writer = new FileWriter(file);
			writer.write(temp);
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
