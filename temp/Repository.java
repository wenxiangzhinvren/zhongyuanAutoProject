/**
 * 煜鼎, Copyright 2017-2017, All rights reserved.
 * author      date         time      
 * ─────────────────────────────────────────────
 * 王基鸿     ${date}      ${time}
*/
package com.yd.ibuznet.modules.${md1}.${md2}.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.yd.ibuznet.core.base.repository.BaseRepository;
import ${clazz};

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
@Repository("${md1}${className2}Repository")
public interface ${className1}Repository extends BaseRepository<${clazzName}>{

	/**
	 * 逻辑删除
	 */
	@Modifying
	@Query("update ${clazzName} set istatus=-1 where id=:id")
	void updateIstatusDis(@Param("id")String id);

}
