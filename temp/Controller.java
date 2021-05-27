/**
 * 煜鼎, Copyright 2017-2017, All rights reserved.
 * author      date         time      
 * ─────────────────────────────────────────────
 * 臧其乐    ${date}      ${time}
*/
package com.yd.ibuznet.modules.${md1}.${md2}.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yd.ibuznet.core.framework.response.Response;
import ${clazz};
import com.yd.ibuznet.modules.${md1}.${md2}.form.${className1}Form;
import com.yd.ibuznet.modules.${md1}.${md2}.service.${className1}Service;

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
 * 版本1.0: ${date} 新建
 * </p> 
 * @author 臧其乐  by 臧其乐 auto create
 * @version 1.0
 */
@Controller("${md1}${className2}Controller")
@RequestMapping("${actionPath}")
public class ${className1}Controller {
	@Autowired
	private ${className1}Service ${className2}Service;
	/**
	 * 列表页面
	 */
	@RequestMapping(method=RequestMethod.GET)
	public String list(){
		return "${md1}/${className3}/list";
	}
	/**
	 * 新增页面
	 */
	@RequestMapping(value="add",method=RequestMethod.GET)
	public String add(){
		return "${md1}/${className3}/add";
	}
	/**
	 * 修改页面
	 */
	@RequestMapping(value="edit/{id}",method=RequestMethod.GET)
	public String edit(@PathVariable String id,Model model){
		${clazzName} md = ${className2}Service.get(id);
		model.addAttribute("md", md);
		return "${md1}/${className3}/edit";
	}

	/**
	 * 列表数据
	 */
	@ResponseBody
	@RequestMapping(value="list",method=RequestMethod.POST)
	public Response grid(${className1}Form form){
		return ${className2}Service.getList(form);
	}
	/**
	 * 新增
	 */
	@ResponseBody
	@RequestMapping(value="save",method=RequestMethod.POST)
	public Response save(${className1}Form form){
		return ${className2}Service.save(form);
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping(value="update",method=RequestMethod.POST)
	public Response update(${className1}Form form){
		return ${className2}Service.update(form);
	}
	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping(value="delete/{id}",method=RequestMethod.POST)
	public Response update(@PathVariable String id){
		return ${className2}Service.delete(id);
	}

}
