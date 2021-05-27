/**
 * 
 */
package com.yuding.www.jpademo.pojo;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @description <用户扩展表>
 * @author wangjihong
 * @version [version 1.0, 2019年3月1日]
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name="test1_userextend")
public class UserExtend implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GenericGenerator(name="system-uuid", strategy="uuid")
    @GeneratedValue(generator="system-uuid")
	@Column(name="ID")
	private String id;
	
	@Column(name="CARD")
	private String card;//身份证号吗
	
	@Column(name="EMAIL")
	private String email;//电子邮箱
	
	@Column(name="QQ")
	private String qq;//QQ
	
	@OneToOne(cascade=CascadeType.PERSIST)
	@JoinColumn(name="USER_ID")
	private User user;
	
}
