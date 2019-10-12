package com.zhiyou100.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhiyou100.model.User;
import com.zhiyou100.model.UserRole;
import com.zhiyou100.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	private UserService service;
	
	@RequestMapping("/User/index")
	public String findAllUser(@RequestParam(defaultValue = "1") int pageSize,
			@RequestParam(defaultValue = "") String keyword, Model model,
			@RequestParam Map<String, String> keywordMap) {
		
		PageHelper.startPage(pageSize, 6);
		List<User> lists = service.findAllUser(keywordMap);

		PageInfo<User> pageInfo = new PageInfo<>(lists);
		model.addAttribute("lists", pageInfo.getList());
		model.addAttribute("keywordMap", keywordMap);
		model.addAttribute("page", pageInfo);

		return "/User/index";
	}
	// 修改
	@RequestMapping(value="/User/editUser",method=RequestMethod.GET)
	public String editUser(int id,Model model) {
		
		User user =service.findUserById(id);
		model.addAttribute("user", user);
		return "/User/editUser";
		
	}
	@RequestMapping(value="/User/editUser",method=RequestMethod.POST)
	public String editUser(User user,UserRole role) {
		
		System.out.println(user);
		System.out.println(role);
		
		service.updateUses(user);
		service.updateRole(role);
		return "forward:/User/index";
		
	}
	
	// add
	@RequestMapping(value="/User/addUser",method=RequestMethod.GET)
	public String addUser() {
		
		
		return "/User/addUser";
		
	}
	// add
	@RequestMapping(value="/User/addUser",method=RequestMethod.POST)
	public String addUser(User user,UserRole userRole) {
		
		System.out.println(user);
		System.out.println(userRole);
		
		user.getUser_name();
		
		service.addUses(user);
		
		
		int id = user.getId(); //获取到的即为新插入记录的ID 
		
		System.out.println("id============================="+id);
		
		userRole.setId(id);
		System.out.println(userRole);
		service.addUserRole(userRole);
		return "forward:/User/index";
		
	}
	// 删除
	@RequestMapping("/User/delete")
	public String deleteUser(int id) {
		
		service.deleteUserById(id);
		return "forward:/User/index";
		
	}
	
}
