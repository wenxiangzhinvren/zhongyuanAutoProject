package com.yuding.www.jpademo.pojo;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author WangJihong
 * @since 2018-08-06
 */
//@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="test1_user")
public class User implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GenericGenerator(name="system-uuid", strategy="uuid")
    @GeneratedValue(generator="system-uuid")
	@Column(name="ID")
    private String id;

	@Column(name="NAME")
    private String name;

	@Column(name="PHONE")
    private String phone;
	
	//CascadeType 级联操作
	//一对一  ‘一个用户只有一个扩展表’
    @OneToOne(mappedBy="user",cascade=CascadeType.PERSIST)
    private UserExtend userExtend;
    
    //多对一  ‘多个用户属于同一个角色’
    @ManyToOne(cascade=CascadeType.PERSIST)
    @JoinColumn(name="ROLE_ID")
    private Role role;
    
    
}
