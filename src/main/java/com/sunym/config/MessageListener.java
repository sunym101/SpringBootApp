package com.sunym.config;

import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;

public class MessageListener implements ApplicationListener<ApplicationStartedEvent> {
	private String propertyFileName;

	public MessageListener(String propertyFileName) {
		this.propertyFileName = propertyFileName;
	}

	@Override
	public void onApplicationEvent(ApplicationStartedEvent event) {
		MessageListenerConfig.loadAllProperties(propertyFileName);
	}
}
