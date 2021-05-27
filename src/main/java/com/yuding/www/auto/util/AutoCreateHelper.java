/**
 * 煜鼎, Copyright 2017-2017, All rights reserved.
 * author      date         time      
 * ─────────────────────────────────────────────
 * 臧其乐     2017年3月31日      上午10:33:57
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
 * @author 臧其乐
 * @version 1.0
 */

public class AutoCreateHelper {
	private String tableName;
	private String pagePath;
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
	
	public void createAll(String basePath,String pagePath,String tempPath,String classPath,FastDevForm form){
		init(basePath,pagePath,tempPath,classPath,form);
		createEntity();
		createListPage();
		createAddPage();
		createEditPage();
		createForm();
		//createListForm();
		createController();
		createService();
		createServiceImpl();
		crateRepository();
		crateSpec();
	}
	public void createEntity(){
		String className = firstToUper(tableName.split("_")[0])+firstToUper(tableName.split("_")[1])+"DO";
		String filePath = classPath+"domain/"+className+ ".java";
		Map<String, String> map = new HashMap<String, String>();
		map.put("md1", md1);
		map.put("md2", md2);
		map.put("importPackage", getImportPackage());
		map.put("tableName", tableName);
		map.put("className", className);
//		map.put("fkColunm", tableEntity.getFkColumn());
		map.put("fields", getFields());
		map.put("methods", getMethods());
		String tempStr = getTemp("Entity.java",map);
		writer(filePath,tempStr);
	}
	public void createListPage() {
		String path = pagePath + "list.html";
		Map<String, String> map = new HashMap<String, String>();
		map.put("dgThs", getThs());
		String tempStr = getTemp("list.html",map);
		writer(path,tempStr);
	}
	
	public void createAddPage(){
		String path = pagePath + "add.html";
		Map<String, String> map = new HashMap<String, String>();
		map.put("rows", getAddRow());
		map.put("actionPath", actionPath);
		String tempStr = getTemp("add.html",map);
		writer(path,tempStr);
	}
	
	public void createEditPage(){
		String path = pagePath + "edit.html";
		Map<String, String> map = new HashMap<String, String>();
		map.put("rows", getEditRow());
		map.put("modelId", "${md.id}");
		map.put("actionPath", actionPath);
		String tempStr =getTemp("edit.html",map);
		writer(path,tempStr);
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
		String tempStr = getTemp("Form.java",map);
		writer(filePath,tempStr);
	}
	
	/*public void createListForm() {
		String className = firstToUper(md2) + md3 + "ListForm";
		String path = classPath +"form/"+className+ ".java";
		Map<String, String> map = new HashMap<String, String>();
		map.put("md1", md1);
		map.put("md2", md2);
		map.put("importPackage", getImportPackage());
		map.put("className", className);
		map.put("fields", getFields());
		map.put("methods", getMethods());
		String tempStr = getTemp("ListForm.java",map);
		writer(path,tempStr);
	}*/
	public void createController() {
		String path = classPath + "controller/" + firstToUper(md2) + md3 + "Controller.java";
		Map<String, String> map = new HashMap<String, String>();
		map.put("md1", md1);
		map.put("md2", md2);
		map.put("className1", firstToUper(md2) + md3);
		map.put("className2", md2 + md3);
		map.put("className3", md2 + (StringUtils.isNotEmpty(md3) ? ("/" + md3) : ""));
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
	
	public void createService(){
		String path = classPath + "service/" + firstToUper(md2) + md3 + "Service.java";
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
		String tempStr = getTemp("Service.java",map);
		writer(path,tempStr);
	}
	
	public void crateRepository(){
			String path = classPath + "repository/" + firstToUper(md2) + md3 + "Repository.java";
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
			String tempStr = getTemp("Repository.java",map);
			writer(path,tempStr);
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
		for(Column c:tableEntity.getColumnList()){
			if(!c.isCreate()){
				continue;
			}
			fields.append("\t/**"+c.getComment()+"*/").append("\n");
			fields.append("\t@FieldName(\""+c.getComment()+"\")").append("\n");
			String type = getType(c.getType());
			fields.append("\tprivate ").append(type).append(" ").append(c.getName()).append(";").append("\n");
		}
		return fields.toString();
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
	public void init(String basePath,String pagePath,String tempPath,String classPath,FastDevForm form){
		this.tableName=form.getTableName();
		this.md1 = form.getMd1();
		this.md2 = form.getMd2();
		this.md3 = form.getMd3();
		this.pagePath = pagePath+ md1 + "/" + md2 + "/" + (StringUtils.isNotEmpty(md3) ? (md3 + "/") : "");
		this.classPath = classPath+md1+"/"+md2+"/";
		this.actionPath = "/" + md1 + "/" + md2 + (StringUtils.isNotEmpty(md3) ? ("/" + md3 + "s/") : "s/");
		className = firstToUper(tableName.split("_")[0])+firstToUper(tableName.split("_")[1])+"DO";
		this.clazz = "com.yd.ibuznet.modules."+md1+"."+md2+".domain."+className;
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
