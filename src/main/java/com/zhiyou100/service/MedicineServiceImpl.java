package com.zhiyou100.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhiyou100.mapper.MedicineMapper;
import com.zhiyou100.model.Drug;
@Service
public class MedicineServiceImpl implements MedicineService{

	@Autowired
	private MedicineMapper mapper;
	
	@Override
	public List<Drug> findAll(Map<String, String> keywordMap) {
		return mapper.findAll(keywordMap);
	}

	@Override
	public void insertDrug(Drug drug) {
		mapper.insertDrug(drug);	
	}

	@Override
	public Drug findMedicineById(String drug_num) {
		return mapper.findMedicineById( drug_num);
	}

	@Override
	public void editMedicine(Drug drug) {
		mapper.editMedicine(drug);
	}

	@Override
	public void deleteMedicine(String drug_num) {
		mapper.deleteMedicine(drug_num);		
	}

}
