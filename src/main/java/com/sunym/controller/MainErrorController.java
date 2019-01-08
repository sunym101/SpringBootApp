package com.sunym.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sunym.entity.ResultEntity;

@RestController
public class MainErrorController implements ErrorController {

	private static final String ERROR_PATH = "/error";

	@RequestMapping(value = ERROR_PATH)
	public String handleError(HttpServletRequest request) {
		// 获取statusCode:401,404,500
		Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");

		ResultEntity result = null;
		switch (statusCode) {
		case 401:

		case 404:

		case 403:

		case 500:

		default:
			result = new ResultEntity(""+statusCode, "Unexpected exceptoin. Error code = " + statusCode);
		}
		return result.toJson();
	}

	@Override
	public String getErrorPath() {
		return ERROR_PATH;
	}
}
