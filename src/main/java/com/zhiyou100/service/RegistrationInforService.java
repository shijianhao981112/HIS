package com.zhiyou100.service;

import java.util.List;
import java.util.Map;

import javax.servlet.Registration;

import com.zhiyou100.model.RegistrationInfor;
import com.zhiyou100.model.ResponseObject;

public interface RegistrationInforService {

	int count(Map<String, Object> map);


	List<RegistrationInfor> findRegistrationById(String medical_record);

	void insertRegistration(RegistrationInfor registration);

	RegistrationInfor findEditRegistrationById(String medical_record);

	void editRegistration(RegistrationInfor registration);

	void deleteRegistrationById(String medical_record);

	List<RegistrationInfor> findAll(Map<String, String> map);


	void deleteAllPL(List<String> list);


	//RegistrationInfor findRegistrationById2(String medical_record);


	//ResponseObject findRegistrationById2(String medical_record);

	

}
