package com.yuding.www.sys.aspect;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yuding.www.jpademo.dao.UserExtendDao;
import com.yuding.www.sys.aspect.annotation.Dict;
import com.yuding.www.sys.util.oConvertUtils;

/**
 * @Description: 字典aop类
 * @Author: dangzhenghui
 * @Date: 2019-3-17 21:50
 * @Version: 1.0
 */
@Aspect
@Component
public class DictAspect {

	@Autowired
	private UserExtendDao userExtendDao;
	//execution(* com.yuding.www.*.*.*Controller.*(..)) or 
	// 定义切点Pointcut
	@Pointcut("execution(* com.yuding.www.*.*.*Dao.*(..))")
	public void excudeService() {}
	
	@Around("excudeService()")
	public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		Object result = pjp.proceed();
		this.parseDictText(result, pjp);
		return result;
	}

	/**
	 * 本方法针对返回对象为Result 的IPage的分页列表数据进行动态字典注入 字典注入实现 通过对实体类添加注解@dict 来标识需要的字典内容,字典分为单字典code即可 ，table字典 code table
	 * text配合使用与原来jeecg的用法相同 示例为SysUser 字段为sex 添加了注解@Dict(dicCode = "sex") 会在字典服务立马查出来对应的text
	 * 然后在请求list的时候将这个字典text，已字段名称加_dictText形式返回到前端 例输入当前返回值的就会多出一个sex_dictText字段 { sex:1, sex_dictText:"男" }
	 * 前端直接取值sext_dictText在table里面无需再进行前端的字典转换了 customRender:function (text) { if(text==1){ return "男"; }else
	 * if(text==2){ return "女"; }else{ return text; } } 目前vue是这么进行字典渲染到table上的多了就很麻烦了 这个直接在服务端渲染完成前端可以直接用
	 * 
	 * @param result
	 */
	private void parseDictText(Object result, ProceedingJoinPoint pjp) {
		System.out.println("****************************************");
		System.out.println("result:"+result);
		if (result instanceof List) {
			List<JSONObject> items = new ArrayList<>();
			System.out.println((List<Object>)(result));
//			for (Object record : (List<Object>)(result)) {
//				JSONObject item = parseForObj(record);
//				items.add(item);
//			}
//			((List<Object>)(result)).add(items);
		}
	}

	/**
	 * 解析Obj
	 * 
	 * @param record
	 * @return
	 */
	public JSONObject parseForObj(Object record) {
		ObjectMapper mapper = new ObjectMapper();
		String json = "{}";
		try {
			// 解决@JsonFormat注解解析不了的问题详见SysAnnouncement类的@JsonFormat
			json = mapper.writeValueAsString(record);
		} catch (Exception e) {
			e.printStackTrace();
		}
		JSONObject item = JSONObject.parseObject(json);
		// update-begin--Author:scott -- Date:20190603 ----for：解决继承实体字段无法翻译问题------
		// for (Field field : record.getClass().getDeclaredFields()) {
		for (Field field : oConvertUtils.getAllFields(record)) {
			// update-end--Author:scott -- Date:20190603 ----for：解决继承实体字段无法翻译问题------
			if (field.getAnnotation(Dict.class) != null) {
				String code = field.getAnnotation(Dict.class).dicCode();
				String text = field.getAnnotation(Dict.class).dicText();
				String table = field.getAnnotation(Dict.class).dictTable();
				String key = String.valueOf(item.get(field.getName()));
				System.out.println("code:"+code+"text:"+text+"table:"+table+"key:"+key);
				// 翻译字典值对应的txt
				String textValue = translateDictValue(code, text, table, key);
				item.put(field.getName() + "_dict", textValue);
			}
			// date类型默认转换string格式化日期
			if (field.getType().getName().equals("java.util.Date") && field.getAnnotation(JsonFormat.class) == null
					&& item.get(field.getName()) != null) {
				SimpleDateFormat aDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				item.put(field.getName(), aDate.format(new Date((Long)item.get(field.getName()))));
			}
		}
		return item;
	}

	/**
	 * 翻译字典文本
	 * 
	 * @param code
	 * @param text
	 * @param table
	 * @param key
	 * @return
	 */
	public String translateDictValue(String code, String text, String table, String key) {
		if (oConvertUtils.isEmpty(key)) {
			return null;
		}
		StringBuffer textValue = new StringBuffer();
		String[] keys = key.split(",");
		for (String k : keys) {
			String tmpValue = null;
			if (k.trim().length() == 0) {
				continue; // 跳过循环
			}
			if (!StringUtils.isEmpty(table)) {
//				tmpValue = userExtendDao.queryTableDictTextByKey(table, text, code, k.trim());
			} else {
//				tmpValue = userExtendDao.queryDictTextByKey(code, k.trim());
			}

			if (tmpValue != null) {
				if (!"".equals(textValue.toString())) {
					textValue.append(",");
				}
				textValue.append(tmpValue);
			}

		}
		return textValue.toString();
	}

}
