package com.zhiyou100.controller;

import java.util.HashMap;
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
import com.zhiyou100.model.Role;
import com.zhiyou100.service.RoleService;

@Controller
public class RoleController {

	@Autowired
	private RoleService service;
	
	@RequestMapping("/Role/index")
	public String indexRole(@RequestParam(defaultValue = "1") int pageSize, Model model,
			@RequestParam Map<String, String> keywordMap) {
		
		PageHelper.startPage(pageSize, 6);
		List<Role> lists = service.findAllRole(keywordMap);

		PageInfo<Role> pageInfo = new PageInfo<>(lists);
		model.addAttribute("lists", pageInfo.getList());
		model.addAttribute("keywordMap", keywordMap);
		model.addAttribute("page", pageInfo);
		
		return "/Role/index";
	}
	
	// insert add
	@RequestMapping(value="/Role/addRole",method=RequestMethod.GET)
	public String insertRole() {
		
		return "Role/addRole";
	}
	
	@RequestMapping(value="/Role/addRole",method=RequestMethod.POST)
	public String insertRole(int[] group,Role role) {
		System.out.println("role: "+role);
		service.insertRole(role);
		int id = role.getId();
		
		System.out.println("id: "+id);
		for (int i : group) {
			System.out.println("group: "+i);
			Map<String,Integer> map = new HashMap<>();
			map.put("id", id);
			map.put("i", i);
			service.insertRole_permission(map);
			
			System.out.println("group2: "+i);
		}
		
		
		return "forward:/Role/index";
	}
	
	// 更新
	@RequestMapping(value="/Role/editRole",method=RequestMethod.GET)
	public String editRole(Model model,int id) {
		
		Role role =service.findRoleById(id);
		System.out.println("role"+role);
		model.addAttribute("roles", role);
		return "Role/editRole";
	}
	// 更新
	@RequestMapping(value="/Role/editRole",method=RequestMethod.POST)
	public String editRole(int[] group,Role role) {
		
		
		return "forward:/Role/index";
		
	}
}
