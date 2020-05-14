package com.masivian.proyecto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import com.masivian.proyecto.model.Apuesta;
import com.masivian.proyecto.model.Ruleta;
import com.masivian.proyecto.model.Usuario;

@SpringBootApplication
public class CleanCodeRuletasApplication {
	
	@Bean
	JedisConnectionFactory jedisConnectionFactory(){
		return new JedisConnectionFactory();
	}
	
	@Bean
	RedisTemplate<String, Ruleta> redisTemplateRuleta(){
		RedisTemplate<String, Ruleta> template = new RedisTemplate<String, Ruleta>();
		template.setConnectionFactory(jedisConnectionFactory());
		return template;
	}
	
	@Bean
	RedisTemplate<String, Usuario> redisTemplateUsuario(){
		RedisTemplate<String, Usuario> template = new RedisTemplate<String, Usuario>();
		template.setConnectionFactory(jedisConnectionFactory());
		return template;
	}
	
	@Bean
	RedisTemplate<String, Apuesta> redisTemplateApuesta(){
		RedisTemplate<String, Apuesta> template = new RedisTemplate<String, Apuesta>();
		template.setConnectionFactory(jedisConnectionFactory());
		return template;
	}

	public static void main(String[] args) {
		SpringApplication.run(CleanCodeRuletasApplication.class, args);
	}

}
