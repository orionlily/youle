package com.orion.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Administrator
 * @date 2018/12/28
 */
@Configuration
public class DruidConfig {
    /**
     * Created by LLC on 2018/4/5.
     */
        @Bean(destroyMethod = "close",initMethod = "init")
        @ConfigurationProperties(prefix = "spring.datasource")
        public DruidDataSource DruidConfig(){
            return new DruidDataSource();
        }
}
