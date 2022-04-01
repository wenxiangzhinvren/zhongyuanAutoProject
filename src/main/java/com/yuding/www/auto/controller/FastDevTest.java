package com.yuding.www.auto.controller;

import com.yuding.www.auto.form.FastDevForm;
import com.yuding.www.auto.util.AutoCreateHelper;

public class FastDevTest {
	
	//项目模板路径
	private static String tempPath = "C:/Users/10434/git/zhongyuanAutoProject/temp/"; //需修改
	//下载到本地
	private static String dowlanloadLocal = "C:/zhongyuanAutoProject";	//需修改
	
	private static String classPath = dowlanloadLocal+"/com/mims/csms/crew/"; //需修改后缀 crew 或 ky/salary 看开发项目
//	private static String classPath = dowlanloadLocal+"/com/mims/csms/ky/salary/";
	
	//包如：bc,cdm,cer,cms 适用于/csms-crew-server
	//无包名 适用于/csms-salary-server
	private static String packageName = "yuding"; //需修改看是否有包名
//	private static String packageName = "";
	
	//com.mims.csms.crew
	//com.mims.csms.ky.salary
	//不用动 根据classPath后缀截取
	private static String packagePrefix = classPath.substring(classPath.indexOf("mims/csms")+"mims/csms".length()+1,classPath.length()-1);
	
	public static void main(String[] args) {
		try {
			FastDevForm form = new FastDevForm();
			form.setTableName("YD_WANGJIHONG");//表名
			AutoCreateHelper ac = new AutoCreateHelper();
			ac.createAll(tempPath,classPath,form,packagePrefix,packageName);
			System.out.println("生成成功");
		} catch (Exception e) {
			System.out.println("生成失败");
			e.printStackTrace();
		}
	}
}
