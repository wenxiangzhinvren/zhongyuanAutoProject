/**
 * 煜鼎, Copyright 2017-2017, All rights reserved.
 * author      date         time      
 * ─────────────────────────────────────────────
 * 臧其乐     2017年5月3日      上午10:01:31
*/
/**
 * 
 */
package com.yuding.www.auto.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Map;


/**
 * <p>
 * 描述: 当前登录用户信息
 * </p>
 * <p>
 * 版权所有: 版权所有(C)2017-2017
 * </p>
 * <p>
 * 公 司: 煜鼎
 * </p>
 * <p>
 * 版本1.0: 2017年5月3日 新建
 * </p> 
 * @author 臧其乐
 * @version 1.0
 */
public class CurrentUser implements Serializable{
	private static final long serialVersionUID = 1L;
	private String id;
	private String username;//用户名
	private String personalName;//姓名
	private String nickName;//昵称
	private String personalEname;//英文名
	private String vpassword;//密码
	private List<String> roles;//角色
	private String dept;//部门
	private String deptName;
	private String company;//公司
	private String companyName;
	private String rank;//职务
	private String liccomcode;
	private Integer iusertype; 
	private String  vpersonalid;
	private String email;
	private Integer singaporeflag; //是否新加坡公司   0：否  1:是
	private Integer companyflag;// 公司标识（1：长航 2：西南）
	/**
	 * 多部门多岗位
	 */
	private List<Map<String,Object>> ranks;
	
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	
	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * @return the personalName
	 */
	public String getPersonalName() {
		return personalName;
	}
	/**
	 * @param personalName the personalName to set
	 */
	public void setPersonalName(String personalName) {
		this.personalName = personalName;
	}
	/**
	 * @return the nickName
	 */
	public String getNickName() {
		return nickName;
	}
	/**
	 * @param nickName the nickName to set
	 */
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	/**
	 * @return the personalEname
	 */
	public String getPersonalEname() {
		return personalEname;
	}
	/**
	 * @param personalEname the personalEname to set
	 */
	public void setPersonalEname(String personalEname) {
		this.personalEname = personalEname;
	}
	/**
	 * @return the roles
	 */
	public List<String> getRoles() {
		return roles;
	}
	/**
	 * @param roles the roles to set
	 */
	public void setRoles(List<String> roles) {
		this.roles = roles;
	}
	/**
	 * @return the dept
	 */
	public String getDept() {
		return dept;
	}
	/**
	 * @param dept the dept to set
	 */
	public void setDept(String dept) {
		this.dept = dept;
	}
	/**
	 * @return the vpassword
	 */
	public String getVpassword() {
		return vpassword;
	}
	/**
	 * @param vpassword the vpassword to set
	 */
	public void setVpassword(String vpassword) {
		this.vpassword = vpassword;
	}
	/**
	 * @return the company
	 */
	public String getCompany() {
		return company;
	}
	/**
	 * @param company the company to set
	 */
	public void setCompany(String company) {
		this.company = company;
	}
	/**
	 * @return the companyName
	 */
	public String getCompanyName() {
		return companyName;
	}
	/**
	 * @param companyName the companyName to set
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	/**
	 * @return the deptName
	 */
	public String getDeptName() {
		return deptName;
	}
	/**
	 * @param deptName the deptName to set
	 */
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	/**
	 * @return the rank
	 */
	public String getRank() {
		return rank;
	}
	/**
	 * @param rank the rank to set
	 */
	public void setRank(String rank) {
		this.rank = rank;
	}
	/**
	 * @return the liccomcode
	 */
	public String getLiccomcode() {
		return liccomcode;
	}
	/**
	 * @param liccomcode the liccomcode to set
	 */
	public void setLiccomcode(String liccomcode) {
		this.liccomcode = liccomcode;
	}
	public Integer getIusertype() {
		return iusertype;
	}
	public void setIusertype(Integer iusertype) {
		this.iusertype = iusertype;
	}
	public String getVpersonalid() {
		return vpersonalid;
	}
	public void setVpersonalid(String vpersonalid) {
		this.vpersonalid = vpersonalid;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public Integer getSingaporeflag() {
		return singaporeflag;
	}
	public void setSingaporeflag(Integer singaporeflag) {
		this.singaporeflag = singaporeflag;
	}
	public Integer getCompanyflag() {
		return companyflag;
	}
	public void setCompanyflag(Integer companyflag) {
		this.companyflag = companyflag;
	}
	public List<Map<String, Object>> getRanks() {
		return ranks;
	}
	public void setRanks(List<Map<String, Object>> ranks) {
		this.ranks = ranks;
	}
	
}
