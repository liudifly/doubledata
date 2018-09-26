package com.example.doubledata.mysql;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * 创建者：梁建军
 * 创建日期： 2018/2/6
 * 创建时间： 10:07
 * DataSourceConfig
 * 版本：1.0
 * 说明：
 */
@Configuration
public class DataSourceConfig {

	@Bean(name = "line")
	@ConfigurationProperties(prefix = "spring.datasource.line") // application.properteis中对应属性的前缀
	public DataSource dataSource1() {
		return DruidDataSourceBuilder.create().build();
	}

	@Bean(name = "other")
	@ConfigurationProperties(prefix = "spring.datasource.other") // application.properteis中对应属性的前缀
	public DataSource dataSource2() {
		return DruidDataSourceBuilder.create().build();
	}

}