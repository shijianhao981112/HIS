package com.zhiyou100.service;

import java.util.List;
import java.util.Map;

import com.zhiyou100.model.HospitalInfor;
import com.zhiyou100.model.RegistrationInfor;

public interface HospitalService {

	List<HospitalInfor> findAll(Map<String, String> keywordMap);

	HospitalInfor findEditHospitalById(String medical_record);

	void ReditRegistration(RegistrationInfor registration);

	void HeditRegistration(HospitalInfor hospitalInfor);

	void insertHospital(HospitalInfor hospitalInfor);

	void editStatusTY(String medical_record);

	void editStatusCY(String medical_record);

	void deleteStatus(String medical_record);

}
