package com.red.star.wechat.data.core.config;

import com.red.star.wechat.data.core.base.DbType;
import com.red.star.wechat.data.core.base.DynamicDataSource;
import com.red.star.wechat.data.core.base.RedisRunner;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.annotation.PreDestroy;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by xulong on 16/5/18.
 */
@Configuration
public class DataConfig {

	@Bean
	@ConfigurationProperties(prefix="spring.datasource.work")
	public DataSource workDataSource() {
		return DataSourceBuilder.create().build();
	}


	@Bean
	@Primary
	public DynamicDataSource dataSource(DataSource workDataSource){
		DynamicDataSource dataSource = new DynamicDataSource();
		Map<Object,Object> map = new HashMap<Object,Object>();
		map.put(DbType.MACALLINE_WORK,workDataSource);
		dataSource.setTargetDataSources(map);
		dataSource.setDefaultTargetDataSource(workDataSource);
		return dataSource;
	}


	@Bean
	public SqlSessionFactory sqlSessionFactory(DataSource dataSource, ApplicationContext applicationContext) throws Exception {
		SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
		sessionFactory.setDataSource(dataSource);
		sessionFactory.setTypeAliasesPackage("com.red.star.wechat.data.entity");
		sessionFactory.setMapperLocations(applicationContext.getResources("classpath*:/mappers/*/*-mapper.xml"));
		sessionFactory.getObject().getConfiguration().setMapUnderscoreToCamelCase(true);
		return sessionFactory.getObject();
	}


	/**
	 * 事务
	 */
	@Bean
	public DataSourceTransactionManager transactionManager(DataSource dataSource){
		DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
		dataSourceTransactionManager.setDataSource(dataSource);
		return dataSourceTransactionManager;
	}

	@Bean
	public RedisRunner redisRunner(@Value("${redis.server}") String redisServers,
	                               @Value("${redis.password}") String password) {
		return new RedisRunner(redisServers,password);
	}


	@PreDestroy
	public void redisDestroy() {
		RedisRunner.stop();
	}



}