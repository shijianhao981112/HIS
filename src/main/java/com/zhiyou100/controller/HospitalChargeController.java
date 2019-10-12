package com.zhiyou100.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
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
import com.zhiyou100.model.ChargeManager;
import com.zhiyou100.model.PayItems;
import com.zhiyou100.model.RegistrationInfor;
import com.zhiyou100.service.HospitalChargeService;
import com.zhiyou100.service.RegistrationInforService;

@Controller
public class HospitalChargeController {

	@Autowired
	private HospitalChargeService service;
	@Autowired
	private RegistrationInforService registrationService;
	
	@RequestMapping("/hospital/charge")
	public String HospitalIndex(@RequestParam(defaultValue = "5") int pageSize, Model model,
			@RequestParam Map<String, String> keywordMap) {
		PageHelper.startPage(pageSize, 10);

		List<ChargeManager> lists = service.findAll(keywordMap);

		PageInfo<ChargeManager> pageInfo = new PageInfo<>(lists);
		System.out.println("登录成功查询的全部用户 : " + lists);
		model.addAttribute("lists", pageInfo.getList());
		model.addAttribute("keywordMap", keywordMap);
		model.addAttribute("page", pageInfo);
		return "/hospital/charge";

	}
	// 查看收费项目收费
	@RequestMapping(value="/hospital/charge-new", method = RequestMethod.GET)
	public String chargeNewlook() {
		
		return "/hospital/charge-new";
		
		
	}
	
	@RequestMapping("/hospital/charge-new/look1")
	public String addLookHospital(String medical_record, Model model) {
		// 回车查看信息
		List<RegistrationInfor> registration = registrationService.findRegistrationById(medical_record);
		System.out.println("look成功查询的全部用户 : " + registration);
		model.addAttribute("registration", registration);

		return "/hospital/charge-new";
	}

	
	
	@RequestMapping("/hospital/charge-new/look2")
	public String chargeNewlook(int id,String medical_record,Model model) {
		// 回车查看信息
		List<RegistrationInfor> registration = registrationService.findRegistrationById(medical_record);
		System.out.println("look成功查询的全部用户 : " + registration);
		model.addAttribute("registration", registration);
		PayItems payItems=service.findchargeNewlook(id);
		
		model.addAttribute("p", payItems);
		
		return "/hospital/charge-new";
		
		
	}
	
	// 添加
	@RequestMapping(value="/hospital/charge-new", method = RequestMethod.POST)
	public String chargeNewlook(ChargeManager chargeManager) {
		String format = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss").format(new Date());
		
		chargeManager.setCharge_time(format);
		
		service.insertchargeNewlook(chargeManager);
		return "forward:/hospital/charge";
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
