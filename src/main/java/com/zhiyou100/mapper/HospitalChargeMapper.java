package com.zhiyou100.mapper;

import java.util.List;
import java.util.Map;

import com.zhiyou100.model.ChargeManager;
import com.zhiyou100.model.PayItems;

public interface HospitalChargeMapper {

	List<ChargeManager> findAll(Map<String, String> keywordMap);

	PayItems findchargeNewlook(int id);

	void insertchargeNewlook(ChargeManager chargeManager);

}
