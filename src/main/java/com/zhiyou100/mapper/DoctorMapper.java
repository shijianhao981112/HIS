package com.zhiyou100.mapper;

import java.util.List;
import java.util.Map;

import com.zhiyou100.model.Doctor;

public interface DoctorMapper {

	

	List<Doctor> findAll(Map<String, String> map);

	void insertDoctor(Doctor doctor);

	Doctor findDoctorById(int id);

	void editDoctor(Doctor doctor);

	void deleteDoctorById(int id);

}
