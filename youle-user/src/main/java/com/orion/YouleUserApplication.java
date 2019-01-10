package com.orion;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Administrator
 */
@RestController
@EnableAutoConfiguration
@SpringBootApplication
@EnableDubbo
@MapperScan(basePackages = "com.orion.dao")
public class YouleUserApplication {

	public static void main(String[] args) {
		SpringApplication.run(YouleUserApplication.class, args);
	}

	@Value("${server.port}")
	String port;

	@RequestMapping("/test")
	public String test() {
		return port;
	}
}

