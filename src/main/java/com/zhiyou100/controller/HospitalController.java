package com.zhiyou100.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhiyou100.model.HospitalInfor;
import com.zhiyou100.model.RegistrationInfor;
import com.zhiyou100.model.ResponseObject;
import com.zhiyou100.service.HospitalService;
import com.zhiyou100.service.RegistrationInforService;

@Controller
public class HospitalController {

	@Autowired
	private HospitalService service;
	@Autowired
	private RegistrationInforService registrationService;

	@RequestMapping("/hospital/index")
	public String HospitalIndex(@RequestParam(defaultValue = "1") int pageSize, Model model,
			@RequestParam Map<String, String> keywordMap) {
		PageHelper.startPage(pageSize, 6);

		List<HospitalInfor> lists = service.findAll(keywordMap);

		PageInfo<HospitalInfor> pageInfo = new PageInfo<>(lists);
		System.out.println("登录成功查询的全部用户 : " + lists);
		model.addAttribute("lists", pageInfo.getList());
		model.addAttribute("keywordMap", keywordMap);
		model.addAttribute("page", pageInfo);
		return "/hospital/index";

	}

	// look 同registration的look页面
	// 挂号详细信息
	@RequestMapping("/hospital/look")
	public String lookRegistration(String medical_record, Model model) {

		List<RegistrationInfor> registration = registrationService.findRegistrationById(medical_record);
		System.out.println("hospital/look成功查询的全部用户 : " + registration);
		model.addAttribute("registration", registration);
		return "/hospital/look";
	}
	

	// 更改
	@RequestMapping(value = "hospital/edit", method = RequestMethod.GET)
	public String edithospital(String medical_record, Model model) {
		// 查询信息
		HospitalInfor hospital = service.findEditHospitalById(medical_record);

		model.addAttribute("h", hospital);
		return "/hospital/edit";

	}

	@RequestMapping(value = "/hospital/edit", method = RequestMethod.POST)
	public String editHospital(RegistrationInfor registration, HospitalInfor hospitalInfor) {
		System.out.println("222" + registration);
		System.out.println("222" + hospitalInfor);
		// 更改信息 2张表 2次
		service.ReditRegistration(registration);
		service.HeditRegistration(hospitalInfor);

		return "forward:/hospital/index";

	}

	// 添加信息
	@RequestMapping(value = "/hospital/add", method = RequestMethod.GET)
	public String addHospital() {
		return "/hospital/add";
	}

	@RequestMapping(value = "/hospital/add/look", method = RequestMethod.POST)
	public String addLookHospital(String medical_record, Model model) {
		// 回车查看信息
		List<RegistrationInfor> registration = registrationService.findRegistrationById(medical_record);
		System.out.println("look成功查询的全部用户 : " + registration);
		model.addAttribute("registration", registration);

		// 添加信息

		return "/hospital/add";
	}
// ================================================== Ajax	
//	@RequestMapping(value = "/hospital/add/look", method = RequestMethod.POST)
//	@ResponseBody
//	public ResponseObject addLookHospital(String medical_record, Model model) {
//		// 回车查看信息
//		RegistrationInfor registration = registrationService.findRegistrationById2(medical_record);
//		System.out.println("look成功查询的全部用户 : " + registration);
//		
//		ResponseObject object = new ResponseObject("200","成功",registration);
//		// 添加信息
//
//		return object;
//	}
	
	
//	@RequestMapping(value = "/hospital/add/look", method = RequestMethod.POST)
//	@ResponseBody
//	public ResponseObject addLookHospital(String medical_record, Model model) {
//		// 回车查看信息
//		System.out.println("Ajax"+medical_record);
//		ResponseObject obj   = registrationService.findRegistrationById2(medical_record);
//		System.out.println("Ajax obj :"+obj);
//		
//		// 添加信息
//		
//		return obj;
//	}


	@RequestMapping(value = "/hospital/add", method = RequestMethod.POST)
	public String addHospital(HospitalInfor hospitalInfor) {
		// 添加信息
		System.out.println("hospitalInfor" + hospitalInfor);
		service.insertHospital(hospitalInfor);

		return "forward:/hospital/index";
	}

	// 退院 出院
	@RequestMapping("/hospital/TY")
	public String TYHospital(String medical_record) {
		// 退院RegistrationInfor的状态
		service.editStatusTY(medical_record);
		return "forward:/hospital/index";
	}
	@RequestMapping("/hospital/CY")
	public String CYHospital(String medical_record) {
		// 退院RegistrationInfor的状态
		service.editStatusCY(medical_record);
		return "forward:/hospital/index";
	}

	// 删除
	@RequestMapping("/hospital/delete")
	public String deleteHospital(String medical_record) {
		
		service.deleteStatus(medical_record);
		return "forward:/hospital/index";
	}

}
