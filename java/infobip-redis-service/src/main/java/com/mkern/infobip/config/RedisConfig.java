package com.mkern.infobip.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisSentinelConfiguration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
public class RedisConfig {
	@Bean
	@ConditionalOnProperty(name = "runonwindows", havingValue = "true", matchIfMissing = false)
	JedisConnectionFactory jedisConnectionFactoryLocal() {
		RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration("localhost", 6379);
		return new JedisConnectionFactory(redisStandaloneConfiguration);
	}

	@Value("${redis.sentinel.host-ports}")
	private String sentinelhostsandports;

	@Value("${redis.sentinel.master.name}")
	private String redismaster;

	@Bean

	@ConditionalOnProperty(name = "runonwindows", havingValue = "false", matchIfMissing = true)
	public RedisTemplate<Object, Object> redisTemplate() {
		RedisTemplate<Object, Object> template = new RedisTemplate<>();
		template.setConnectionFactory(jedisConnectionFactory());
		return template;
	}

	@Bean(name = "jedisConnectionFactory")
	@ConditionalOnProperty(name = "runonwindows", havingValue = "false", matchIfMissing = true)
	public JedisConnectionFactory jedisConnectionFactory() {
		RedisSentinelConfiguration redisSentinelConfiguration = new RedisSentinelConfiguration();
		redisSentinelConfiguration.master(redismaster);
		String redisSentinelHostAndPorts = sentinelhostsandports;
		HostAndPort hostAndPort;
		if (redisSentinelHostAndPorts.contains(";")) {
			for (String node : redisSentinelHostAndPorts.split(";")) {
				if (null != node & node.contains(":")) {
					String[] nodeArr = node.split(":");
					hostAndPort = new HostAndPort(nodeArr[0], Integer.parseInt(nodeArr[1]));
					redisSentinelConfiguration.sentinel(hostAndPort.getHost(), hostAndPort.getPort());
				}
			}
		} else {
			if (redisSentinelHostAndPorts.contains(":")) {
				String[] nodeArr = redisSentinelHostAndPorts.split(":");
				hostAndPort = new HostAndPort(nodeArr[0], Integer.parseInt(nodeArr[1]));
				redisSentinelConfiguration.sentinel(hostAndPort.getHost(), hostAndPort.getPort());
			}
		}
		return new JedisConnectionFactory(redisSentinelConfiguration, poolConfig());
	}

	@Bean
	@ConditionalOnProperty(name = "runonwindows", havingValue = "false", matchIfMissing = true)
	public JedisPoolConfig poolConfig() {
		final JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
		jedisPoolConfig.setTestOnBorrow(true);
		jedisPoolConfig.setMaxTotal(100);
		jedisPoolConfig.setMaxIdle(100);
		jedisPoolConfig.setMinIdle(10);
		jedisPoolConfig.setTestOnReturn(true);
		jedisPoolConfig.setTestWhileIdle(true);
		return jedisPoolConfig;
	}
}
