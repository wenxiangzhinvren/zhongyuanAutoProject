package com.yuding.www.jpademo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.yuding.www.jpademo.pojo.UserExtend;

/**
 * @description <功能详细描述、说明>
 * @author wangjihong
 * @version [version 1.0, 2019年3月1日]
 */

public interface UserExtendDao extends JpaRepository<UserExtend, String>,JpaSpecificationExecutor<UserExtend>{

}
