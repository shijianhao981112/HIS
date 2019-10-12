package com.zhiyou100.service;

import java.util.List;
import java.util.Map;

import com.zhiyou100.model.Doctor;

public interface DoctorService {

	

	List<Doctor> findAll(Map<String, String> map);

	void insertDoctor(Doctor doctor);

	Doctor findDoctorById(int id);

	void editDoctor(Doctor doctor);

	void deleteDoctorById(int id);

	

}
