/**
 * 
 */
package com.yuding.www.jpademo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.yuding.www.jpademo.pojo.User;

/**
 * @description <功能详细描述、说明>
 * @author wangjihong
 * @version [version 1.0, 2019年2月28日]
 */

public interface UserDao extends JpaRepository<User, String>,JpaSpecificationExecutor<User>{
	/**
	 *功能描述:<命名规则查询>
	 */
	List<User> findByName(String string);

	/**
	 *功能描述:<JPQL语句>
	 */
	@Query(value="from User where name = :name")
	List<User> findByNameJPQL(@Param("name")String name);
	/**
	 *功能描述:<JPQL语句>
	 */
	@Query(value="from User where name = ?1")
	List<User> findByNameJPQLTwo(String name);

	/**
	 *功能描述:<原生sql>
	 */
	@Query(value="select * from test_user where name = :name",nativeQuery=true)
	List<User> findByNameSQL(@Param("name")String name);
	
	/**
	 *功能描述:<原生sql>
	 */
	@Query(value="select * from test_user where name = ?1",nativeQuery=true)
	List<User> findByNameSQLTwo(String name);
}
