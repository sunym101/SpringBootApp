package com.sunym;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.sunym.config.MessageListener;

@SpringBootApplication
public class SpringBootApp {
	public static void main(String[] args) {
		SpringApplication application = new SpringApplication(SpringBootApp.class);
		// 注册监听器，添加properties配置
		application.addListeners(new MessageListener("config/message.properties"));
		application.run(args);
	}
}
