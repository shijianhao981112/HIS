package com.zhiyou100.mapper;

import java.util.List;
import java.util.Map;

import com.zhiyou100.model.User;
import com.zhiyou100.model.UserRole;

public interface UserMapper {

	List<User> findAllUser(Map<String, String> keywordMap);

	User findUserById(int id);

	void updateUses(User user);

	void updateRole(UserRole role);

	void addUses(User user);

	void addUserRole(UserRole userRole);

	void deleteUserById(int id);

}
