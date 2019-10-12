package com.zhiyou100.service;

import com.zhiyou100.model.ResponseObject;

public interface AjaxService {

	String checkUser(String usernameJsonkey);

	ResponseObject checkUser2(String usernameJsonkey);

	ResponseObject findUserById(String id);

	ResponseObject findDoctorBySectionId(String sectionId);

}
