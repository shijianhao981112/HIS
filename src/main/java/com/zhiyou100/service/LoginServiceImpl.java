package com.zhiyou100.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhiyou100.mapper.LoginMapper;
import com.zhiyou100.model.User;

@Service
public class LoginServiceImpl implements LoginService{

	@Autowired
	private LoginMapper mapper;
	
	@Override
	public User findUserByLogin(String user_name, String password) {
		Map<String,String> map = new HashMap<>();
		map.put("user_name", user_name);
		map.put("password", password);
		// 调用数据访问层查询
		User user = mapper.findUserByLogin(map);
		
		return user;
	}

}
