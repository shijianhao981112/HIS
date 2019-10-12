package com.zhiyou100.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhiyou100.mapper.UserMapper;
import com.zhiyou100.model.Role;
import com.zhiyou100.model.User;
import com.zhiyou100.model.UserRole;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserMapper mapper;
	@Override
	public List<User> findAllUser(Map<String, String> keywordMap) {
		return mapper.findAllUser(keywordMap);
	}
	@Override
	public User findUserById(int id) {
		return mapper.findUserById(id);
	}
	@Override
	public void updateUses(User user) {
		mapper.updateUses(user);
	}
	@Override
	public void updateRole(UserRole role) {
		mapper.updateRole(role);		
	}
	@Override
	public void addUses(User user) {
		mapper.addUses( user);
	}
	@Override
	public void addUserRole(UserRole userRole) {
		mapper.addUserRole(userRole);
	}
	@Override
	public void deleteUserById(int id) {
		mapper.deleteUserById(id);
	}

}
