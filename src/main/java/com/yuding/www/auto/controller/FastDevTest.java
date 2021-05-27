package com.yuding.www.auto.controller;

import org.apache.commons.lang3.StringUtils;

import com.yuding.www.auto.form.FastDevForm;
import com.yuding.www.auto.util.AutoCreateHelper;

public class FastDevTest {
	
//	private static String basePath = "C:/projectwork/autoProject/";
//	private static String pagePath = basePath+"src/main/resources/templates/";
//	private static String tempPath = basePath+"temp/";
//	private static String classPath = basePath+ "src/main/java/com/yuding/www/modules/";
	
	private static String basePath = "C:/Users/10434/git/ibuznet/";
	private static String pagePath = basePath+"src/main/resources/templates/";
	private static String tempPath = "C:/projectwork/autoProject/temp/";
	private static String classPath = basePath+ "src/main/java/com/yd/ibuznet/modules/";
	
	public static void main(String[] args) {
		try {
			FastDevForm form = new FastDevForm();
			form.setTableName("tsys_email_template");
			form.setMd1("sys");
			form.setMd2("emailtemplate");
			form.setMd3("");
			if(StringUtils.isEmpty(form.getMd1())){
				form.setMd1(form.getTableName().substring(1, form.getTableName().indexOf("_")));
			}
			AutoCreateHelper ac = new AutoCreateHelper();
			ac.createAll(basePath,pagePath,tempPath,classPath,form);
			System.out.println("生成成功");
		} catch (Exception e) {
			System.out.println("生成失败");
			e.printStackTrace();
		}
	}
}
