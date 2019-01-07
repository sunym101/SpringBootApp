package com.sunym.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.sunym.entity.ResultEntity;
import com.sunym.model.UserModel;
import com.sunym.service.UserService;

@RestController
public class UserController {

	@Autowired
	UserService userService;

	@RequestMapping(value = "/getUser", method = RequestMethod.GET, produces = "text/json;charset=utf-8")
	public String getUser(@RequestParam("username") String username) {
		UserModel user = userService.getUserByName(username);

		ResultEntity result = new ResultEntity("8001", "Data not found.");
		if (user != null) {
			result = new ResultEntity("0000", "OK", user);
		}
		return JSONObject.toJSONString(result, true);
	}
}
