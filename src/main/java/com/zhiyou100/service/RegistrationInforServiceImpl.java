package com.zhiyou100.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.Registration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhiyou100.mapper.RegistrationInforMapper;
import com.zhiyou100.model.RegistrationInfor;
import com.zhiyou100.model.ResponseObject;
@Service
public class RegistrationInforServiceImpl implements RegistrationInforService{

	@Autowired
	private RegistrationInforMapper mapper;
	@Override
	public int count(Map<String, Object> map) {
		return mapper.count( map);
	}

	@Override
	public List<RegistrationInfor> findAll(Map<String, String> map) {
		return mapper.findAll( map);
	}

	@Override
	public List<RegistrationInfor> findRegistrationById(String medical_record) {
		return mapper.findRegistrationById( medical_record);
	}

	@Override
	public void insertRegistration(RegistrationInfor registration) {
		mapper.insertRegistration( registration);
		
	}

	@Override
	public RegistrationInfor findEditRegistrationById(String medical_record) {
		return mapper.findEditRegistrationById( medical_record);
	}

	@Override
	public void editRegistration(RegistrationInfor registration) {
		mapper.editRegistration(registration);	
	}

	@Override
	public void deleteRegistrationById(String medical_record) {
		mapper.deleteRegistrationById( medical_record);		
	}
	// 批量删除
	@Override
	public void deleteAllPL(List<String> list) {
		mapper.deleteAllPL(list);
		
	}

//	@Override
//	public RegistrationInfor findRegistrationById2(String medical_record) {
//		// TODO Auto-generated method stub
//		return mapper.findRegistrationById2( medical_record);
//	}

//	@Override
//	public ResponseObject findRegistrationById2(String medical_record) {
//		List<RegistrationInfor> registrationInfor = new ArrayList<>();
//		ResponseObject rObj = mapper.findRegistrationById2( medical_record);
//		
//		
//		registrationInfor.addAll(rObj);
//		return new ResponseObject("200","成功",registrationInfor);
//		//return 
//	}

}
