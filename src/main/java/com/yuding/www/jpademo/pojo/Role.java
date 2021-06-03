/**
 * 
 */
package com.yuding.www.jpademo.pojo;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @description <角色表>
 * @author wangjihong
 * @version [version 1.0, 2019年3月1日]
 */

@Getter
@Setter@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="test_role")
public class Role implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GenericGenerator(name="system-uuid", strategy="uuid")
    @GeneratedValue(generator="system-uuid")
	@Column(name="ID")
	private String id;
	
	@Column(name="ROLENAME")
	private String rolename;
	
	//一对多  ‘一个角色有多个用户’
	@OneToMany(mappedBy="role",cascade=CascadeType.PERSIST)
	private Set<User> user;
	
	//多对多  ‘一角色有多个菜单 一个菜单可以被多个角色所拥有’
	@ManyToMany(cascade=CascadeType.PERSIST, fetch = FetchType.EAGER)
	@JoinTable(name="test_role_menu",joinColumns=@JoinColumn(name="ROLE_ID"),inverseJoinColumns=@JoinColumn(name="MENU_ID"))
	private Set<Menu> menus;
	
}
