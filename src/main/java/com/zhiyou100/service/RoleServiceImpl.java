package com.zhiyou100.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhiyou100.mapper.RoleMapper;
import com.zhiyou100.model.Role;
@Service
public class RoleServiceImpl implements RoleService{

	@Autowired
	private RoleMapper mapper;
	
	@Override
	public List<Role> findAllRole(Map<String, String> keywordMap) {
		return mapper.findAllRole(keywordMap);
	}

	@Override
	public void insertRole(Role role) {
		mapper.insertRole(role);
	}

	@Override
	public void insertRole_permission(Map<String, Integer> map) {
		mapper.insertRole_permission(map);
	}

	@Override
	public Role findRoleById(int id) {
		return mapper.findRoleById(id);
	}

	

}
