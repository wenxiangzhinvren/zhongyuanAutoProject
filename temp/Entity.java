package com.yd.ibuznet.modules.${md1}.${md2}.domain;


${importPackage}
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.yd.ibuznet.core.annotation.FieldName;
import com.yd.ibuznet.core.base.domain.BaseDO;

/**
 * Model class of ${tableName}.
 * 
 * @author 王基鸿 by 王基鸿 auto create
 * @version 1.0
 */
@Entity
@Table(name="${tableName}")
public class ${className} extends BaseDO implements Serializable {

	/** serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** id. */
	@Id
	@GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
${fkColunm}
${fields}
	/**istatus*/
	@FieldName("istatus")
	private Integer istatus=0;
	
${methodsId}
${methods}
	
	public  void setIstatus(Integer istatus){
		this.istatus = istatus;
	}
	
	public Integer getIstatus(){
		return this.istatus;
	}
	

}
