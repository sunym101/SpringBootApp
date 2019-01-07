package com.sunym.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.sunym.utils.StringUtil;

import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
@Slf4j
public class JedisConfig extends CachingConfigurerSupport {

	/**
	 * SpringSession 需要注意的就是redis需要2.8以上版本，然后开启事件通知，在redis配置文件里面加上
	 * notify-keyspace-events Ex Keyspace notifications功能默认是关闭的（默认地，Keyspace
	 * 时间通知功能是禁用的，因为它或多或少会使用一些CPU的资源）。<br>
	 * 或是使用如下命令： redis-cli config set notify-keyspace-events Egx
	 * 如果你的Redis不是你自己维护的，比如你是使用阿里云的Redis数据库，你不能够更改它的配置，那么可以使用如下方法：
	 * 在applicationContext.xml中配置<util:constant static-field=
	 * "org.springframework.session.data.redis.config.ConfigureRedisAction.NO_OP"/>
	 * 
	 * @return
	 */

	@Value("${spring.redis.host}")
	private String host;

	@Value("${spring.redis.port}")
	private int port;

	@Value("${spring.redis.password}")
	private String password;

	@Value("${spring.redis.timeout}")
	private int timeout;

	@Value("${spring.redis.pool.max-active}")
	private int maxActive;

	@Value("${spring.redis.pool.max-idle}")
	private int maxIdle;

	@Value("${spring.redis.pool.min-idle}")
	private int minIdle;

	@Value("${spring.redis.pool.max-wait}")
	private long maxWaitMillis;

	@Value("${spring.redis.block-when-exhausted}")
	private boolean blockWhenExhausted;

	@Value("${spring.redis.isJmxEnabled}")
	private boolean isJmxEnabled;

	@Bean
	public JedisPool redisPoolFactory() {
		JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
		// 最大空闲数
		jedisPoolConfig.setMaxIdle(maxIdle);
		// 最大建立连接等待时间
		jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);
		// 连接池的最大数据库连接数
		jedisPoolConfig.setMaxTotal(maxActive);
		// 最小空闲数
		jedisPoolConfig.setMinIdle(minIdle);
		// 连接耗尽时是否阻塞, false报异常,ture阻塞直到超时, 默认true
		jedisPoolConfig.setBlockWhenExhausted(blockWhenExhausted);
		// 是否启用pool的jmx管理功能
		jedisPoolConfig.setJmxEnabled(isJmxEnabled);

		// 密码为空处理，设成null
		if (StringUtil.isEmpty(password)) {
			password = null;
		}

		JedisPool jedisPool = new JedisPool(jedisPoolConfig, host, port, timeout, password);

		log.debug("JedisPool注入成功！");
		log.debug("redis地址：" + host + ":" + port);
		return jedisPool;
	}

}
