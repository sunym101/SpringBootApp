# SpringBootApp

1. Hello World
	URL: http://localhost:18080/SpringBootApi/helloWorld

2. 获取DB数据
 	获取用户信息： http://localhost:18080/SpringBootApi/getUser?username=admin
	使用Mybatis功能到MySql中获取用户数据信息。

3. 获取Redis数据
	注意: 配置没有密码时，JedisConfig中，password必须是null，是空字符串的话，会认为密码是""，而连不上的。
	实例：
	setRedis:
		http://localhost:18080/SpringBootApi/setRedis?key=key1&value=value1&redisdb=15
	
	getRedis:
		http://localhost:18080/SpringBootApi/getRedis?key=key1&redisdb=15
	
	hmsetRedis: 
		http://localhost:18080/SpringBootApi/hmsetRedis?key=key1&subkey=subkey1&value=value1&redisdb=15
		http://localhost:18080/SpringBootApi/hmsetRedis?key=key1&subkey=subkey2&value=value2&redisdb=15
