package com.sunym.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sunym.config.MessageListenerConfig;
import com.sunym.entity.ResultEntity;
import com.sunym.utils.StringUtil;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class MainErrorController implements ErrorController {

	private static final String ERROR_PATH = "/error";

	/**
	 * 处理服务器异常 200：服务器响应正常。<br>
	 * 304：该资源在上次请求之后没有任何修改（这通常用于浏览器的缓存机制，使用GET请求时尤其需要注意）。<br>
	 * 400：无法找到请求的资源。<br>
	 * 401：访问资源的权限不够。<br>
	 * 403：没有权限访问资源。<br>
	 * 404：需要访问的资源不存在。<br>
	 * 405：需要访问的资源被禁止。<br>
	 * 407：访问的资源需要代理身份验证。 <br>
	 * 414：请求的URL太长。 <br>
	 * 500：服务器内部错误。 <br>
	 * 
	 * @param request
	 * @return 错误消息
	 */
	@RequestMapping(value = ERROR_PATH)
	public String handleError(HttpServletRequest request) {
		// 获取statusCode:401,404,500等
		Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
		// 获取错误消息
		String errorMsg = MessageListenerConfig.getProperty("server.error." + statusCode);
		if (StringUtil.isEmpty(errorMsg)) {
			errorMsg = MessageListenerConfig.getProperty("server.error.999");
		}

		ResultEntity result = new ResultEntity("" + statusCode, errorMsg);
		String message = result.toJson();
		log.error("Error:" + message);
		return message;
	}

	@Override
	public String getErrorPath() {
		return ERROR_PATH;
	}
}
