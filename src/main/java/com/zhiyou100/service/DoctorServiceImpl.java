package com.zhiyou100.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhiyou100.mapper.DoctorMapper;
import com.zhiyou100.model.Doctor;


@Service
public class DoctorServiceImpl implements DoctorService{

	@Autowired
	private DoctorMapper mapper;
	

	@Override
	public List<Doctor> findAll(Map<String, String> map) {
		return mapper.findAll(map);
	}

	@Override
	public void insertDoctor(Doctor doctor) {
		mapper.insertDoctor(doctor);
		
	}

	@Override
	public Doctor findDoctorById(int id) {
		return mapper.findDoctorById( id);
	}

	@Override
	public void editDoctor(Doctor doctor) {
		mapper.editDoctor(doctor);
		
	}

	@Override
	public void deleteDoctorById(int id) {
		mapper.deleteDoctorById(id);
	}

	

}
