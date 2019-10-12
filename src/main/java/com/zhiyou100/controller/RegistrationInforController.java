package com.zhiyou100.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.Registration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhiyou100.model.RegistrationInfor;
import com.zhiyou100.service.RegistrationInforService;

@Controller
public class RegistrationInforController {

	
	@Autowired
	private RegistrationInforService service;
	
	@RequestMapping("/registration/index")
	public String doctorIndex(@RequestParam(defaultValue="1")int pageSize,@RequestParam(defaultValue="")String keyword,Model model,@RequestParam Map<String,String> keywordMap) {
//		Map<String,Object> map = new HashMap<>();
//		map.put("keyword", keyword);
		System.out.println("测试keywordMap :"+keywordMap);
//		int total = service.count(map);
		PageHelper.startPage(pageSize, 6);
		
		List<RegistrationInfor> lists = service.findAll(keywordMap);		
		
		PageInfo<RegistrationInfor> pageInfo = new PageInfo<>(lists);
		//System.out.println("登录成功查询的全部用户 : " + lists);
		model.addAttribute("lists", pageInfo.getList());
		model.addAttribute("keywordMap", keywordMap);
		model.addAttribute("page", pageInfo);
		
		return "/registration/index";
	}
		// 挂号详细信息
		@RequestMapping("/registration/look")
		public String lookRegistration(String medical_record, Model model) {
			List<RegistrationInfor> registration= service.findRegistrationById(medical_record);
			System.out.println("look成功查询的全部用户 : " + registration);
			model.addAttribute("registration", registration);
			return "/registration/look";
		}
		
		
		// 添加挂号信息
		@RequestMapping(value="/registration/add",method=RequestMethod.GET)
		public String addRegistration(Model model) {
			// 以毫秒值为挂号id
			long time = new Date().getTime(); 
			System.out.println(time);
			model.addAttribute("time",time);
			
			return "/registration/add";
		}
		@RequestMapping(value="/registration/add",method=RequestMethod.POST)
		public String addRegistration(RegistrationInfor registration, Model model) {
			
			System.out.println("挂号信息:"+registration);
			
			service.insertRegistration(registration);
			return "forward:/registration/index";
		}
		
		// 更改
		@RequestMapping(value="registration/edit",method=RequestMethod.GET)
		public String editRegistration(String medical_record, Model model){
			
			System.out.println("更改查询medical_record"+medical_record);
			RegistrationInfor registration=service.findEditRegistrationById(medical_record);
			System.out.println("更改查询registration"+registration);
			model.addAttribute("registration",registration);
			return "/registration/edit";
			
		}
		
		@RequestMapping(value="/registration/edit",method=RequestMethod.POST)
		public String editRegistration(RegistrationInfor registration,Model model){
			
			service.editRegistration(registration);
			
			return "forward:/registration/index";
			
		}
		
		
		// 删除
		@RequestMapping("/registration/delete")
		public String deleteRegistration(String medical_record) {
			
			service.deleteRegistrationById(medical_record);
			return "forward:/registration/index";
			
		}
		// 批量删除
		@RequestMapping("/registration/deleteAll")
		public String deleteAllRegistration(String[] check) {
			System.out.println("批量"+Arrays.toString(check));
			
			List<String> list=new ArrayList<>();
			for (String string : check) {
				list.add(string);
				System.out.println("string"+string);
			}
			System.out.println("批量list"+list);
			service.deleteAllPL(list);
			return "forward:/registration/index";
			
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	
}
