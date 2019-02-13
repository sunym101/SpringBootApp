package com.sunym.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sunym.utils.HttpRequestUtil;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class HelloWorldController {

	@RequestMapping("/helloWorld")
	public String helloWorld() {
		return "Hello World!";
	}

	@RequestMapping("/getToken")
	public String getToken() {
		// 请求URL
		String pathUrl = "http://10.168.1.35:10000/auth/oauth/token";
		log.info("URL=[" + pathUrl + "]");

		// 请求用的Json参数
		Map<String, String> head = new HashMap<String, String>();

		Map<String, String> body = new HashMap<String, String>();
		body.put("username", "admin");
		body.put("password", "123456");
		body.put("grant_type", "password");
		body.put("scope", "all");
		body.put("client_id", "client");
		body.put("client_secret", "secret");

		// 调用Http请求，并返回结果
		String result = HttpRequestUtil.doPost(pathUrl, head, body, "UTF-8");
		log.info("WebService返回结果：[" + result + "]");
		return result;
	}
}
