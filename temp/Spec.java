/**
 * 煜鼎, Copyright 2017-2017, All rights reserved.
 * author      date         time      
 * ─────────────────────────────────────────────
 * 王基鸿     ${date}      ${time}
*/
package com.yd.ibuznet.modules.${md1}.${md2}.specification;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import ${clazz};

import com.yd.ibuznet.core.util.SpecUtil;

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

public class ${className1}Spec  implements Specification<${clazzName}> {

	private ${className1}Form form;

	public ${className1}Spec(${className1}Form form) {
		this.form = form;
	}

	@Override
	public Predicate toPredicate(Root<${clazzName}> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		List<Predicate> list = new ArrayList<Predicate>();
		list.add(cb.equal(root.get("istatus"), 0));
		Predicate[] predicates = new Predicate[list.size()];
		if (StringUtils.isNotEmpty(form.getPublicQueryParam())) {
			return cb.and(SpecUtil.getPredicate(root, ${clazzName}.class, cb, form.getPublicQueryParam()),query.where(list.toArray(predicates)).getRestriction());
		}
		return query.where(list.toArray(predicates)).getRestriction();
	}

}
