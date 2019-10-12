package com.zhiyou100.service;

import java.util.List;
import java.util.Map;

import com.zhiyou100.model.Role;

public interface RoleService {

	List<Role> findAllRole(Map<String, String> keywordMap);

	void insertRole(Role role);

	void insertRole_permission(Map<String, Integer> map);

	Role findRoleById(int id);

	

}
