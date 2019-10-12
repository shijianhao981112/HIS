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
import com.zhiyou100.model.Doctor;
import com.zhiyou100.service.DoctorService;

@Controller
public class DoctorController {

	@Autowired
	private DoctorService service;

	@RequestMapping("/doctor/index")
	public String doctorIndex(@RequestParam(defaultValue = "1") int pageSize, Model model,
			@RequestParam Map<String, String> keywordMap) {

		PageHelper.startPage(pageSize, 2);

		List<Doctor> lists = service.findAll(keywordMap);
		System.out.println(lists);
		PageInfo<Doctor> pageInfo = new PageInfo<>(lists);
		// System.out.println("登录成功查询的全部用户 : " + lists);
		model.addAttribute("lists", pageInfo.getList());
		model.addAttribute("keywordMap", keywordMap);
		model.addAttribute("page", pageInfo);

		System.out.println("pageInfo" + pageInfo);
		return "/doctor/index";
	}

	// add
	@RequestMapping(value = "/doctor/add", method = RequestMethod.GET)
	public String addDoctor() {
		return "/doctor/add";
	}

	@RequestMapping(value = "/doctor/add", method = RequestMethod.POST)
	public String addDoctor(Doctor doctor, Model model) {
		System.out.println("医生信息:" + doctor);
		service.insertDoctor(doctor);

		return "forward:/doctor/index";
	}

	// look
	@RequestMapping("/doctor/look")
	public String lookDoctor(int id, Model model) {
		Doctor doctor = service.findDoctorById(id);
		model.addAttribute("doctor", doctor);
		return "/doctor/look";
	}

	// edit
	@RequestMapping(value = "/doctor/edit", method = RequestMethod.GET)
	public String editDoctor(int id, Model model) {
		Doctor doctor = service.findDoctorById(id);
		model.addAttribute("doctor", doctor);
		return "/doctor/edit";
	}

	// edit
	@RequestMapping(value = "/doctor/edit", method = RequestMethod.POST)
	public String editDoctor(Doctor doctor) {
		service.editDoctor(doctor);
		return "forward:/doctor/index";
	}

	// 删除
	@RequestMapping("/doctor/delete")
	public String deleteDoctor(int id) {
		service.deleteDoctorById(id);
		return "forward:/doctor/index";

	}

}
