# SpringBootApp

#### 1. Hello World

	URL: http://localhost:18080/SpringBootApi/helloWorld

#### 2. 获取DB数据

	获取用户信息： http://localhost:18080/SpringBootApi/getUser?username=admin
	使用Mybatis功能到MySql中获取用户数据信息。

#### 3. 获取Redis数据
注意: 配置没有密码时，JedisConfig中，password必须是null，是空字符串的话，会认为密码是""，而连不上的。
	
实例：
	setRedis:
		http://localhost:18080/SpringBootApi/setRedis?key=key1&value=value1&redisdb=15
	
	getRedis:
		http://localhost:18080/SpringBootApi/getRedis?key=key1&redisdb=15
	
	hmsetRedis: 
		http://localhost:18080/SpringBootApi/hmsetRedis?key=key1&subkey=subkey1&value=value1&redisdb=15
		http://localhost:18080/SpringBootApi/hmsetRedis?key=key1&subkey=subkey2&value=value2&redisdb=15

#### 4. 记录日志
	(1) 日志配置文件在src/main/resources/logback.xml。
	(2) 在需要些日志的类定义前，追加@Slf4j注解。
	(3) 使用时，不在需要定义log对象，直接用log.debug(),log.info(),log.error()等方法写入日志信息。
	(4) 可参考Controller中的使用。
