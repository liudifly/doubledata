package com.example.doubledata.mysql;

import org.apache.ibatis.mapping.DatabaseIdProvider;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;
import org.mybatis.spring.boot.autoconfigure.MybatisProperties;
import org.mybatis.spring.boot.autoconfigure.SpringBootVFS;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;
import java.util.List;

/**
 * 创建者：梁建军
 * 创建日期： 2018/2/6
 * 创建时间： 13:37
 * MybatisDbBeanConfig
 * 版本：1.0
 * 说明：
 */
public class MybatisDbBeanConfig {

	public static final String TAG = "--" + MybatisDbBeanConfig.class.getSimpleName();

	protected final DataSource dataSource;

	protected final MybatisProperties properties;

	protected final Interceptor[] interceptors;

	protected final ResourceLoader resourceLoader;

	protected final DatabaseIdProvider databaseIdProvider;

	protected final List<ConfigurationCustomizer> configurationCustomizers;

	public MybatisDbBeanConfig(DataSource dataSource, MybatisProperties properties,
	                           ObjectProvider<Interceptor[]> interceptorsProvider,
	                           ResourceLoader resourceLoader,
	                           ObjectProvider<DatabaseIdProvider> databaseIdProvider,
	                           ObjectProvider<List<ConfigurationCustomizer>> configurationCustomizersProvider) {
		this.dataSource = dataSource;
		this.properties = properties;
		this.interceptors = interceptorsProvider.getIfAvailable();
		this.resourceLoader = resourceLoader;
		this.databaseIdProvider = databaseIdProvider.getIfAvailable();
		this.configurationCustomizers = configurationCustomizersProvider.getIfAvailable();
	}

	/**
	 * 配置
	 *
	 * @param factory
	 */
	protected void configureSqlSessionFactoryBean(SqlSessionFactoryBean factory) {

		factory.setDataSource(dataSource); // 使用titan数据源, 连接titan库
		factory.setVfs(SpringBootVFS.class);
		if (StringUtils.hasText(this.properties.getConfigLocation())) {
			factory.setConfigLocation(this.resourceLoader.getResource(this.properties.getConfigLocation()));
		}
		org.apache.ibatis.session.Configuration configuration = this.properties.getConfiguration();
		if (configuration == null && !StringUtils.hasText(this.properties.getConfigLocation())) {
			configuration = new org.apache.ibatis.session.Configuration();
		}
		if (configuration != null && !CollectionUtils.isEmpty(this.configurationCustomizers)) {
			for (ConfigurationCustomizer customizer : this.configurationCustomizers) {
				customizer.customize(configuration);
			}
		}
		factory.setConfiguration(configuration);
		if (this.properties.getConfigurationProperties() != null) {
			factory.setConfigurationProperties(this.properties.getConfigurationProperties());
		}
		if (!ObjectUtils.isEmpty(this.interceptors)) {
			factory.setPlugins(this.interceptors);
		}
		if (this.databaseIdProvider != null) {
			factory.setDatabaseIdProvider(this.databaseIdProvider);
		}
		if (StringUtils.hasLength(this.properties.getTypeAliasesPackage())) {
			factory.setTypeAliasesPackage(this.properties.getTypeAliasesPackage());
		}
		if (StringUtils.hasLength(this.properties.getTypeHandlersPackage())) {
			factory.setTypeHandlersPackage(this.properties.getTypeHandlersPackage());
		}
		if (!ObjectUtils.isEmpty(this.properties.resolveMapperLocations())) {
			factory.setMapperLocations(this.properties.resolveMapperLocations());
		}
	}

	protected SqlSessionTemplate createdSqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
		ExecutorType executorType = this.properties.getExecutorType();
		if (executorType != null) {
			return new SqlSessionTemplate(sqlSessionFactory, executorType);
		} else {
			return new SqlSessionTemplate(sqlSessionFactory);
		}
	}
}
