package com.yuding.www.auto.controller;

import com.yuding.www.auto.form.FastDevForm;
import com.yuding.www.auto.util.AutoCreateHelper;

public class FastDevTest {
	
	//项目模板路径
	private static String tempPath = "C:/Users/10434/git/zhongyuanAutoProject/temp/";
	//下载到
	private static String classPath = "C:/zhongyuanAutoProject/com/mims/csms/ky/salary/";
	public static void main(String[] args) {
		try {
			FastDevForm form = new FastDevForm();
			form.setTableName("SRY_SALARY_PLAN");
			AutoCreateHelper ac = new AutoCreateHelper();
			ac.createAll(tempPath,classPath,form);
			System.out.println("生成成功");
		} catch (Exception e) {
			System.out.println("生成失败");
			e.printStackTrace();
		}
	}
}
