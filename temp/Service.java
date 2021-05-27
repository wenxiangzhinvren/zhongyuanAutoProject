/**
 * 煜鼎, Copyright 2017-2017, All rights reserved.
 * author      date         time      
 * ─────────────────────────────────────────────
 * 王基鸿    ${date}      ${time}
*/
package com.yd.ibuznet.modules.${md1}.${md2}.service;

import com.yd.ibuznet.core.framework.response.Response;
import ${clazz};
import com.yd.ibuznet.modules.${md1}.${md2}.form.${className1}Form;

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

public interface ${className1}Service {
	/**
	 * 列表数据
	 */
	Response getList(${className1}Form form);
	/**
	 * 获取单个对象
	 */
	${clazzName} get(String id);
	/**
	 * 新增
	 */
	Response save(${className1}Form form);
	/**
	 * 修改
	 */
	Response update(${className1}Form form);
	/**
	 * 删除
	 */
	Response delete(String id);

	

}
