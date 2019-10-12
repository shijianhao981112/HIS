package com.zhiyou100.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhiyou100.mapper.HospitalMapper;
import com.zhiyou100.model.HospitalInfor;
import com.zhiyou100.model.RegistrationInfor;

@Service
public class HospitalServiceImpl implements HospitalService{

	@Autowired
	private HospitalMapper mapper;
	@Override
	public List<HospitalInfor> findAll(Map<String, String> keywordMap) {
		return mapper.findAll(keywordMap);
	}
	@Override
	public HospitalInfor findEditHospitalById(String medical_record) {
		return mapper.findEditHospitalById(medical_record);
	}
	@Override
	public void ReditRegistration(RegistrationInfor registration) {
		mapper.ReditRegistration( registration);
	}
	@Override
	public void HeditRegistration(HospitalInfor hospitalInfor) {
		mapper.HeditRegistration( hospitalInfor);
		
	}
	@Override
	public void insertHospital(HospitalInfor hospitalInfor) {
		mapper.insertHospital( hospitalInfor);
	}
	@Override
	public void editStatusTY(String medical_record) {
		mapper.editStatusTY( medical_record);
	}
	@Override
	public void editStatusCY(String medical_record) {
		mapper.editStatusCY( medical_record);
	}
	@Override
	public void deleteStatus(String medical_record) {
		mapper.deleteStatus(medical_record);
	}

}
