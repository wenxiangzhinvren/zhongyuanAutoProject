package com.yuding.www.auto.controller;

import org.apache.commons.lang3.StringUtils;

import com.yuding.www.auto.form.FastDevForm;
import com.yuding.www.auto.util.AutoCreateHelper;

public class FastDevTest {
	private static String basePath = "C:/projectwork/autoProject/";
	private static String pagePath = basePath+"src/main/resources/templates/";
	private static String tempPath = basePath+"temp/";
	private static String classPath = basePath+ "src/main/java/com/yuding/www/modules/";
	
	public static void main(String[] args) {
		FastDevForm form = new FastDevForm();
		form.setTableName("tsys_cascade_data");
		form.setMd1("hehe");
		form.setMd2("heihei");
		
		if(StringUtils.isEmpty(form.getMd1())){
			form.setMd1(form.getTableName().substring(1, form.getTableName().indexOf("_")));
		}
		AutoCreateHelper ac = new AutoCreateHelper();
		ac.createAll(basePath,pagePath,tempPath,classPath,form);
	}
}
