package com.zhiyou100.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhiyou100.mapper.HospitalChargeMapper;
import com.zhiyou100.model.ChargeManager;
import com.zhiyou100.model.PayItems;
@Service
public class HospitalChargeServiceImpl implements HospitalChargeService{

	@Autowired
	private HospitalChargeMapper mapper;
	
	@Override
	public List<ChargeManager> findAll(Map<String, String> keywordMap) {
		return mapper.findAll( keywordMap);
	}

	@Override
	public PayItems findchargeNewlook(int id) {
		return mapper.findchargeNewlook( id);
	}

	@Override
	public void insertchargeNewlook(ChargeManager chargeManager) {
		mapper.insertchargeNewlook(chargeManager);		
	}

}
