package com.sunym.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.sunym.entity.ResultEntity;
import com.sunym.utils.RedisUtil;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@SuppressWarnings({ "rawtypes", "unchecked" })
public class RedisController {

	@Autowired
	RedisUtil redisUtil;

	@RequestMapping(value = "/setRedis", method = RequestMethod.GET, produces = "text/json;charset=utf-8")
	public String setRedis(@RequestParam("key") String key, @RequestParam("value") String value, @RequestParam("redisdb") String redisdb) {
		int db = Integer.parseInt(redisdb);
		redisUtil.set(key, value, db);

		String obj = "Redis保存数据(set) = " + key + ":" + value;
		log.debug("Result Object = " + obj);
		ResultEntity result = new ResultEntity("0000", "OK", obj);
		return JSONObject.toJSONString(result, true);
	}

	@RequestMapping(value = "/getRedis", method = RequestMethod.GET, produces = "text/json;charset=utf-8")
	public String getRedis(@RequestParam("key") String key, @RequestParam("redisdb") String redisdb) {
		int db = Integer.parseInt(redisdb);
		String value = redisUtil.get(key, db);

		String obj = "Redis获取数据(get) = " + key + ":" + value;
		log.debug("Result Object = " + obj);
		ResultEntity result = new ResultEntity("0000", "OK", obj);
		return JSONObject.toJSONString(result, true);
	}

	@RequestMapping(value = "/hmsetRedis", method = RequestMethod.GET, produces = "text/json;charset=utf-8")
	public String hmsetRedis(@RequestParam("key") String key, @RequestParam("subkey") String subkey, @RequestParam("value") String value, @RequestParam("redisdb") String redisdb) {
		int db = Integer.parseInt(redisdb);

		HashMap hash = new HashMap();
		hash.put(subkey, value);
		String res = redisUtil.hmset(key, hash, db);

		String obj = "Redis保存数据(hmset) = " + key + ":" + value + ", result = [" + res + "]";
		log.debug("Result Object = " + obj + ", result = [" + res + "]");

		ResultEntity result = new ResultEntity("0000", "OK", obj);
		if (!"OK".equals(res)) {
			result = new ResultEntity("8001", "NG", obj);
		}

		return JSONObject.toJSONString(result, true);
	}
}
