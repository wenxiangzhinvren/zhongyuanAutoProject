package com.yd.ibuznet.modules.${md1}.${md2}.form;


${importPackage}
import java.io.Serializable;
import com.yd.ibuznet.core.annotation.FieldName;

import com.yd.ibuznet.core.base.form.PageForm;

/**
 * 
 * @author 臧其乐 by 臧其乐 auto create
 * @version 1.0
 */
public class ${className} extends PageForm implements Serializable {

	/** serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** id. */
	private String id;
${fields}
	/**istatus*/
	private Integer istatus=0;
	
	
	public  void setId(String id){
		this.id = id;
	}
	
	public String getId(){
		return this.id;
	}
	
	${methods}
	
	public  void setIstatus(Integer istatus){
		this.istatus = istatus;
	}
	
	public Integer getIstatus(){
		return this.istatus;
	}
	

}
