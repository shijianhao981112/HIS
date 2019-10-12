package com.zhiyou100.service;

import java.util.List;
import java.util.Map;

import com.zhiyou100.model.ChargeManager;
import com.zhiyou100.model.PayItems;

public interface HospitalChargeService {

	List<ChargeManager> findAll(Map<String, String> keywordMap);

	PayItems findchargeNewlook(int id);

	void insertchargeNewlook(ChargeManager chargeManager);

}
