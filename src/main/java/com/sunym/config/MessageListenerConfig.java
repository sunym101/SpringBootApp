package com.sunym.config;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

import org.springframework.core.io.support.PropertiesLoaderUtils;

import com.sunym.utils.StringUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * 第四种方式：PropertiesLoaderUtils 加载Properties文件内容
 */
@Slf4j
public class MessageListenerConfig {
	public static Map<String, String> propertiesMap = new HashMap<>();

	private static void processProperties(Properties props) {
		propertiesMap = new LinkedHashMap<String, String>();
		for (Object key : props.keySet()) {
			String keyStr = key.toString();
			try {
				propertiesMap.put(keyStr, props.getProperty(keyStr));
			} catch (Exception e) {
				log.error("加载Properties配置发生异常！", e.fillInStackTrace());
			}
		}
		//log.debug("properties = [" + JSONObject.toJSONString(propertiesMap, true) + "]");
	}

	public static void loadAllProperties(String propertyFileName) {
		try {
			Properties properties = PropertiesLoaderUtils.loadAllProperties(propertyFileName);
			processProperties(properties);
		} catch (IOException e) {
			log.error("加载Properties文件发生异常！", e.fillInStackTrace());
		}
	}

	public static String getProperty(String msgCode) {
		String value = "";
		if (!StringUtil.isEmpty(msgCode)) {
			value = propertiesMap.get(msgCode);
			if (value == null) {
				value = "";
			}
		}
		return value;
	}

	/**
	 * 信息内容取得
	 * 
	 * @param msgCode
	 *            信息编号
	 * @param paramList
	 *            本编号所需要的参数列表
	 * @return 信息内容
	 */
	public String getProperty(String msgCode, String[] params) {
		String msgInfo = "";
		if (msgCode == null || "".equals(msgCode)) {
			return msgInfo;
		}
		if (params == null) {
			return msgInfo;
		}

		try {
			msgInfo = getProperty(msgCode);

			String[] msgList = StringUtil.splitString(msgInfo, "%");
			if (params.length + 1 == msgList.length) {
				int k = 0;
				msgInfo = msgList[0];
				for (int i = 1; i < msgList.length; i++) {
					k = i - 1;
					msgInfo += params[k] + msgList[i];
				}
			}
		} catch (Exception ex) {
			msgInfo = "";
		}
		return msgInfo;
	}

	public static Map<String, String> getAllProperty() {
		return propertiesMap;
	}
}