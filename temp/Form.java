package com.yd.ibuznet.modules.${md1}.${md2}.form;


${importPackage}
import java.io.Serializable;
import com.yd.ibuznet.core.annotation.FieldName;
import com.yd.ibuznet.core.base.form.PageForm;

/**
 * 
 * @author 王基鸿  by 王基鸿 auto create
 * @version 1.0
 */
public class ${className}  extends PageForm implements Serializable {

	/** serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
${fkColunm}
${fields}
	/**istatus*/
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
