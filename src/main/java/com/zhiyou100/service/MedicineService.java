package com.zhiyou100.service;

import java.util.List;
import java.util.Map;

import com.zhiyou100.model.Drug;

public interface MedicineService {

	List<Drug> findAll(Map<String, String> keywordMap);

	void insertDrug(Drug drug);

	Drug findMedicineById(String drug_num);

	void editMedicine(Drug drug);

	void deleteMedicine(String drug_num);

}
