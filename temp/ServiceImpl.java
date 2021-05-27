/**
 * 煜鼎, Copyright 2017-2017, All rights reserved.
 * author      date         time      
 * ─────────────────────────────────────────────
 * 王基鸿     ${date}      ${time}
*/
package com.yd.ibuznet.modules.${md1}.${md2}.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yd.ibuznet.core.base.vo.EasyGridData;
import com.yd.ibuznet.core.framework.response.Response;
import com.yd.ibuznet.core.validator.CommonValidator;
import ${clazz};
import com.yd.ibuznet.modules.${md1}.${md2}.form.${className1}Form;
import com.yd.ibuznet.modules.${md1}.${md2}.repository.${className1}Repository;
import com.yd.ibuznet.modules.${md1}.${md2}.service.${className1}Service;
import com.yd.ibuznet.modules.${md1}.${md2}.specification.${className1}Spec;

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
 * @author 王基鸿  by 王基鸿 auto create
 * @version 1.0
 */
@Service("${md1}${className2}Service")
@Transactional
public class ${className1}ServiceImpl implements ${className1}Service{
	@Autowired
	private ${className1}Repository ${className2}Repository;

	/**
	 * 列表数据
	 */
	@Override
	public Response getList(${className1}Form form) {
		EasyGridData<${clazzName}> easyGridData = new EasyGridData<${clazzName}>();
		Response response = new Response();
		Page<${clazzName}>  page = ${className2}Repository.findAll(new ${className1}Spec(form), form.getPageRequest());
		easyGridData.setRows(page.getContent());
		easyGridData.setTotal(page.getTotalElements());
		response.setResultData(easyGridData);
		return response;
	}
	/**
	 * 获取单个对象
	 */
	@Override
	public ${clazzName} get(String id) {
		return ${className2}Repository.findOne(id);
	}

	/**
	 * 新增
	 */
	@Override
	public Response save(${className1}Form form) {
		Response response = new Response();
		if (!CommonValidator.validate(form, response)) {
			return response;
		}
		${clazzName} model = new ${clazzName}();
		BeanUtils.copyProperties(form, model);
		${className2}Repository.save(model);
		response.setResultData(model);
		return response;
	}

	/**
	 * 修改
	 */
	@Override
	public Response update(${className1}Form form) {
		Response response = new Response();
		if (!CommonValidator.validate(form, response)) {
			return response;
		}
		${clazzName} model = new ${clazzName}();
		BeanUtils.copyProperties(form, model);
		${className2}Repository.save(model);
		return response;
	}

	/**
	 * 删除
	 */
	@Override
	public Response delete(String id) {
		Response response = new Response();
		${className2}Repository.updateIstatusDis(id);
		return response;
	}


}
