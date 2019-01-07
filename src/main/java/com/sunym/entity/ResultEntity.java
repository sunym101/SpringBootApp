package com.sunym.entity;

public class ResultEntity {

	private String result_code;

	private String result_message;

	private Object result_info;

	public ResultEntity() {
	}

	public ResultEntity(String result_code, String result_message) {
		this.result_code = result_code;
		this.result_message = result_message;
	}

	public ResultEntity(String result_code, String result_message, Object result_info) {
		this.result_code = result_code;
		this.result_message = result_message;
		this.result_info = result_info;
	}

	public String getResult_code() {
		return result_code;
	}

	public void setResult_code(String result_code) {
		this.result_code = result_code;
	}

	public String getResult_message() {
		return result_message;
	}

	public void setResult_message(String result_message) {
		this.result_message = result_message;
	}

	public Object getResult_info() {
		return result_info;
	}

	public void setResult_info(Object result_info) {
		this.result_info = result_info;
	}
}
