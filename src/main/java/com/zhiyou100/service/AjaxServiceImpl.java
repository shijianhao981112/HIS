package com.zhiyou100.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.zhiyou100.model.Doctor;
import com.zhiyou100.model.ResponseObject;
import com.zhiyou100.model.User;

@Service
public class AjaxServiceImpl implements AjaxService {

	// 注入mapper 查数据库
	@Override
	public String checkUser(String usernameJsonkey) {
		/*
		 * 只要用户名不是admin,都可注册
		 */
		String code = null; // 同HTML中的code相同
		if(usernameJsonkey == null || usernameJsonkey == "" || usernameJsonkey.equals("admin")) {
			// 已注册 : 返回404
			code ="{\"code\":\"404\"}";
		} else {
			// 可以注册 : 返回200
			code = "{\"code\":\"200\"}";
		}
		System.out.println("业务层输入返回的结果:  "+code);
		return code;
	}
	@Override
	public ResponseObject checkUser2(String usernameJsonkey) {
		ResponseObject obj = new ResponseObject();
		/*
		 * 只要用户名不是admin,都可注册
		 */
		String code = null; 
		if(usernameJsonkey == null || usernameJsonkey == "" || usernameJsonkey.equals("admin")) {
			obj.setCode("404");
			obj.setMsg("不允许注册");
		} else {
			obj.setCode("200");
			obj.setMsg("可以注册");
		}
		System.out.println("业务层输入返回的结果:  "+obj);
		return obj;
	}
	
	// 案例二
	@Override
	public ResponseObject findUserById(String id) {
		// 假设直接能查到,自己创建对象,返回
		User user = new User();
		user.setReal_name("张三");
		user.setPassword("121212");
		user.setEmail("110@119.com");
		System.out.println(user);
		// 创建响应对象
		ResponseObject obj = new ResponseObject("200","成功",user);
		System.out.println(obj);
		return obj;
	}
	// 案例三
	@Override
	public ResponseObject findDoctorBySectionId(String sectionId) {
		ArrayList<Doctor> doctors = new ArrayList<>();
		// 写假方法
		if(sectionId != null && sectionId != "") {
			// 假设科室1
			if(sectionId.equals("1")) {
				doctors.add(new Doctor("k1张三"));
				doctors.add(new Doctor("k1李四"));
				doctors.add(new Doctor("k1王五"));
			} else if(sectionId.equals("2")){
				doctors.add(new Doctor("k2张三"));
				doctors.add(new Doctor("k2李四"));
				doctors.add(new Doctor("k2王五"));
			} else if(sectionId.equals("3")){
				doctors.add(new Doctor("k3张三"));
				doctors.add(new Doctor("k3李四"));
				doctors.add(new Doctor("k3王五"));
			}
				
		} else {
			System.out.println("id不正确");
		}
		
		return new ResponseObject("200","成功",doctors);
	}
}
