/**
 * 
 */
package com.yuding.www.jpademo.pojo;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @description <菜单表>
 * @author wangjihong
 * @version [version 1.0, 2019年3月1日]
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name="test_menu")
public class Menu implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GenericGenerator(name="system-uuid", strategy="uuid")
    @GeneratedValue(generator="system-uuid")
	@Column(name="ID")
	private String id;
	
	@Column(name="MENUNAME")
	private String menuname;
	
	@Column(name="URL")
	private String url;
	
	@ManyToMany(cascade=CascadeType.PERSIST)
	@JoinTable(name="test_role_menu",joinColumns=@JoinColumn(name="MENU_ID"),inverseJoinColumns=@JoinColumn(name="ROLE_ID"))
	private Set<Role> role;
	
}
