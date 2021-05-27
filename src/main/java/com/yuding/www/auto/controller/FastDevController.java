package com.yuding.www.auto.controller;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yuding.www.auto.form.FastDevForm;
import com.yuding.www.auto.form.Response;
import com.yuding.www.auto.util.AutoCreateHelper;
import com.yuding.www.auto.util.FileExUtils;

import freemarker.template.TemplateException;

/**
 * <p>
 * 描述: 
 * </p>
 * <p>
 * 版本1.0: 2017年2月23日 新建
 * </p>
 * 
 * @author 王基鸿
 * @version 1.0
 */
@Controller
@RequestMapping("fastdev")
public class FastDevController {
	
	private String basePath = "C:/Users/10434/git/ibuznet/";
	private String pagePath = basePath+"src/main/resources/templates/";
	private String tempPath = basePath+"temp/";
	private String classPath =basePath+ "src/main/java/com/yd/ibuznet/modules/";
	
	@RequestMapping()
	public String index(Model model){
		model.addAttribute("basePath", basePath);
		return "fastdev/index";
	}
	@RequestMapping("add")
	public String add(){
		return "fastdev/create";
	}
	@RequestMapping("edit")
	public String edit(){
		return "fastdev/update";
	}
	
	@ResponseBody
	@RequestMapping("list")
	public Response list(){
		List<Map<String,String>> list = new ArrayList<Map<String,String>>();
		Response response = new Response();
		String path = pagePath;
		File file = new File(path);
		getFile(file,list);
		response.setResultData(list);
		return response;
	}
	
	private void getFile(File file,List<Map<String,String>> list){
		String[] cps = file.list();
		for(String cp : cps){
			File f = new File(file.getPath()+File.separator+cp);
			if(f.list()!=null&&f.list().length>0){
				getFile(f,list);
			}
			if("add.html".equals(cp)||"edit.html".equals(cp)){
				Map<String,String> map = new HashMap<String,String>();
				String path = (file.getPath()+File.separator+cp);
				path = path.split("templates")[1].substring(1);
				map.put("path", path);
				list.add(map);
			}
		}
	}
	@ResponseBody
	@RequestMapping("setBasePath")
	public Response setBasePath(String basePath){
		this.basePath = basePath;
		pagePath = this.basePath+"src/main/resources/templates/";
		tempPath = this.basePath+"temp/";
		classPath =this.basePath+ "src/main/java/com/yd/ibuznet/modules/";
		return new Response();
	}
	
	@ResponseBody
	@RequestMapping("temps")
	public String getTemps(String path) throws IOException, TemplateException{
		path = pagePath+path;
		File file = new File(path);
		Reader reader = new FileReader(file);
		int ch=reader.read();
        StringBuffer buffer=new StringBuffer();
        while(ch!=-1){ //读取成功
            buffer.append((char)ch);
            ch=reader.read();
        }
		String tempStr = buffer.toString();
		reader.close();
		return tempStr;
	}
	@ResponseBody
	@RequestMapping("save")
	public String save(FastDevForm form){
		if(StringUtils.isEmpty(form.getMd1())){
			form.setMd1(form.getTableName().substring(1, form.getTableName().indexOf("_")));
		}
		AutoCreateHelper ac = new AutoCreateHelper();
		ac.createAll(basePath,pagePath,tempPath,classPath,form);
		return "0";
	}
	@ResponseBody
	@RequestMapping("update")
	public String update(String path,String temp) throws IOException, TemplateException{
		path = pagePath+path;
		path = FileExUtils.path(path);
		File file = new File(path);
		Writer writer = new FileWriter(file);
		writer.write(temp);
		writer.flush();
		writer.close();
		return "0";
	}
	
}
