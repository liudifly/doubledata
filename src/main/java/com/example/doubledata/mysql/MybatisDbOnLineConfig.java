package com.example.doubledata.mysql;

import org.apache.ibatis.mapping.DatabaseIdProvider;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;
import org.mybatis.spring.boot.autoconfigure.MybatisProperties;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;

import javax.sql.DataSource;
import java.util.List;

/**
 * 创建者：梁建军
 * 创建日期： 2018/2/6
 * 创建时间： 10:08
 * MybatisDbAConfig
 * 版本：1.0
 * 说明：
 */
@Configuration
@MapperScan(basePackages = {"com.example.doubledata.mapper"}, sqlSessionFactoryRef = "sqlSessionFactory1")
@EnableConfigurationProperties(MybatisProperties.class)
public class MybatisDbOnLineConfig extends MybatisDbBeanConfig {
	public MybatisDbOnLineConfig(@Qualifier("line") DataSource dataSource, MybatisProperties properties, ObjectProvider<Interceptor[]> interceptorsProvider, ResourceLoader resourceLoader, ObjectProvider<DatabaseIdProvider> databaseIdProvider, ObjectProvider<List<ConfigurationCustomizer>> configurationCustomizersProvider) {
		super(dataSource, properties, interceptorsProvider, resourceLoader, databaseIdProvider, configurationCustomizersProvider);
	}

	@Bean
	public SqlSessionFactory sqlSessionFactory1() throws Exception {
		SqlSessionFactoryBean factory = new SqlSessionFactoryBean();
		configureSqlSessionFactoryBean(factory);
		return factory.getObject();
	}

	@Bean
	public SqlSessionTemplate sqlSessionTemplate1() throws Exception {
		return createdSqlSessionTemplate(sqlSessionFactory1());

	}
}