/**
 * 煜鼎, Copyright 2017-2017, All rights reserved.
 * author      date         time      
 * ─────────────────────────────────────────────
 * 王基鸿     2017年3月31日      上午10:06:37
*/
package com.yuding.www.auto.dto;

import java.util.List;

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
 * 版本1.0: 2017年3月31日 新建
 * </p> 
 * @author 王基鸿
 * @version 1.0
 */

public class TableEntity {
	private String tableName;
	private Column fkColumn;
	private List<Column> columnList;
	
	
	public String getTableName() {
		return tableName;
	}


	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	
	public Column getFkColumn() {
		return fkColumn;
	}

	public void setFkColumn(Column fkColumn) {
		this.fkColumn = fkColumn;
	}


	public List<Column> getColumnList() {
		return columnList;
	}


	public void setColumnList(List<Column> columnList) {
		this.columnList = columnList;
	}
	

	public class  Column{
		private String name;
		private String comment;
		private String type;
		private boolean create;
		
		public Column() {
			super();
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getComment() {
			return comment;
		}
		public void setComment(String comment) {
			this.comment = comment;
		}
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
		public boolean isCreate() {
			return create;
		}
		public void setCreate(boolean create) {
			this.create = create;
		}
		
		
	}
}
