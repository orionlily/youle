package com.orion;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Administrator
 * 问题原因: Mybatis没有找到合适的加载类,其实是大部分spring - datasource - url没有加载成功,分析原因如下所示.
   DataSourceAutoConfiguration会自动加载.所以需要排除数据源初始化配置类
 */
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
@RestController
@EnableDubbo
public class YouleControllerApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(YouleControllerApplication.class, args);
	}

	@Value("${server.port}")
	String port;

	@RequestMapping("/test")
	public String test() {
		return "controller:"+port;
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(YouleControllerApplication.class);
	}
}

