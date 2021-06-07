/**
 * 
 */
package com.yuding.www.jpademo.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yuding.www.jpademo.dao.UserDao;
import com.yuding.www.jpademo.pojo.Menu;
import com.yuding.www.jpademo.pojo.Role;
import com.yuding.www.jpademo.pojo.User;
import com.yuding.www.jpademo.pojo.UserExtend;


@Controller
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserController {

	@Autowired
	private UserDao userDao;

	@RequestMapping("/")
	@ResponseBody
	public String index(Model model){
		Order orders = new Order(Direction.DESC, "id");
		Sort sort = Sort.by(orders);
		Pageable pageable = PageRequest.of(0, 2,sort);
		Specification<User> spec = new Specification<User>() {
			private static final long serialVersionUID = 1L;
			@Override
			public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				return cb.and(
						cb.equal(root.get("name"), "王基鸿"),
						cb.equal(root.get("phone"),"13775910654")
						);
			}
		};
		Page<User> listUsers = userDao.findAll(spec,pageable);
		System.out.println(listUsers.getContent());//具体信息
		System.out.println(listUsers.getTotalElements());//总条数
		System.out.println(listUsers.getTotalPages());//页数
		System.out.println("进来了");
		String name = "{\"name\":\"http://localhost:80/sxhur/uploads/picture/u=1577759731,3108671782&fm=26&gp=0.jpg\"}";
		model.addAttribute("name",name);
		model.addAttribute("userList", listUsers.getContent());
		return name;
	}

	@RequestMapping("/test")
	public String test(){
		return "redirect:http://www.baidu.com";
	}

	//王基鸿的用户
	//拥有扩展属性 
	//并且属于 超级管理员角色
	//这个角色下有两个菜单 
	@Test
	public void test1(){
		Set<Menu>menuList = new HashSet<Menu>();
		//用户
		User user = new User();
		user.setName("王基鸿");
		user.setPhone("13775910654");
		//用户宽展
		UserExtend userExtend = new UserExtend();
		userExtend.setCard("32032219940126565X");
		userExtend.setEmail("1043429669@qq.com");
		userExtend.setQq("1043429669");
		//角色
		Role role = new Role();
		role.setRolename("超级管理员");
		//			//菜单
		Menu menu1 = new Menu();
		menu1.setMenuname("首页管理");
		menu1.setUrl("http://www.taobao.com");
		Menu menu2 = new Menu();
		menu2.setMenuname("订单管理");
		menu2.setUrl("http://www.jd.com");
		menuList.add(menu1);
		menuList.add(menu2);
		//如果需要存储某个表中的主键  就set这个实体类获取
		//实体之间绑定
		userExtend.setUser(user);
		user.setUserExtend(userExtend);
		role.setMenus(menuList);//role-》中间表-》Menu
		user.setRole(role); //user关联-》role表 -》
		try {
			userDao.save(user);
		} catch (Exception e) {
			e.fillInStackTrace();
		}

	}

	/**
	 * 条件查询及分页和排序
	 *2019年3月1日 上午10:57:12
	 */
	@Test
	public void test2(){
		Order orders = new Order(Direction.DESC, "id");
		Sort sort = Sort.by(orders);
		Pageable pageable = PageRequest.of(0, 2,sort);
		Specification<User> spec = new Specification<User>() {

			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				return cb.and(
						cb.equal(root.get("name"), "王基鸿"),
						cb.equal(root.get("phone"),"13775910654")
						);
			}
		};
		Page<User> listUsers = userDao.findAll(spec,pageable);
		System.out.println(listUsers.getContent());//具体信息
		System.out.println(listUsers.getTotalElements());//总条数
		System.out.println(listUsers.getTotalPages());//页数
	}
	/**
	 * 取所有
	 */
	@RequestMapping("/test3")
	@ResponseBody
	@Test
	public void test3(){
		List<User> list = userDao.findAll();
		System.out.println("用户信息："+list);//用户信息
		System.out.println("用户扩展信息："+list.get(0).getUserExtend());//扩展信息
		for(User user:list){
			System.out.println("角色信息："+user.getRole());//角色信息
			for(Menu menu:user.getRole().getMenus()){
				System.out.println("角色下菜单信息："+menu);//角色信息
			}
		}
	}

}
