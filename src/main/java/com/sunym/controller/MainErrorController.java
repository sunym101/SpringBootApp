package com.sunym.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sunym.entity.ResultEntity;

@RestController
public class MainErrorController implements ErrorController {

	private static final String ERROR_PATH = "/error";

	/**
	 * 200：服务器响应正常。<br>
	 * 304：该资源在上次请求之后没有任何修改（这通常用于浏览器的缓存机制，使用GET请求时尤其需要注意）。<br>
	 * 400：无法找到请求的资源。<br>
	 * 401：访问资源的权限不够。<br>
	 * 403：没有权限访问资源。<br>
	 * 404：需要访问的资源不存在。<br>
	 * 405：需要访问的资源被禁止。<br>
	 * 407：访问的资源需要代理身份验证。 <br>
	 * 414：请求的URL太长。 <br>
	 * 500：服务器内部错误。 <br>
	 */
	@RequestMapping(value = ERROR_PATH)
	public String handleError(HttpServletRequest request) {
		// 获取statusCode:401,404,500
		Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");

		String errorMsg = null;
		switch (statusCode) {
		case 401:
			errorMsg = "访问资源的权限不够。";
			break;
		case 403:
			errorMsg = "没有权限访问资源。";
			break;
		case 404:
			errorMsg = "需要访问的资源不存在。";
			break;
		case 500:
			errorMsg = "服务器内部错误。";
			break;
		default:
			errorMsg = "其他错误。";
		}
		ResultEntity result = new ResultEntity("" + statusCode, errorMsg);
		return result.toJson();
	}

	@Override
	public String getErrorPath() {
		return ERROR_PATH;
	}
}
