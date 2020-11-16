package com.medical.shopbackend.config;

import java.util.Enumeration;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan(basePackages="com.medical.shopbackend.dto")
@EnableTransactionManagement
public class HibernateConfig {
	


	private final static String DATABASE_URL   	= "jdbc:postgresql://XXXXXXX/XXXXXXXX";
	private final static String DATABASE_DRIVER 	= "org.postgresql.Driver";
	private final static String DATABASE_DIALECT 	= "org.hibernate.dialect.PostgreSQL94Dialect";
	private final static String DATABASE_USERNAME 	= "XXXXX";
	private final static String DATABASE_PASSWORD 	= "XXXXX";

	// DataSource bean
	@Bean("dataSource")
	public DataSource getDatasource(){
		
		BasicDataSource datasource = new BasicDataSource();
		//Providing Database Connection Information
		datasource.setDriverClassName(DATABASE_DRIVER);
		datasource.setUrl(DATABASE_URL);
		datasource.setUsername(DATABASE_USERNAME);
		datasource.setPassword(DATABASE_PASSWORD);
		
		return datasource;
	}
	
	// Session Factory Bean
	@Bean
	public SessionFactory getSessionFactory(DataSource datasource){
		
		LocalSessionFactoryBuilder builder =new LocalSessionFactoryBuilder(datasource);
		builder.addProperties(getHibernateProperties());
		builder.scanPackages("com.medical.shopbackend.dto");
		
		return builder.buildSessionFactory();
	}

	
	// All the Hibernate properties will be returned in this method
	private Properties getHibernateProperties() {
		
		Properties properties = new Properties();
		properties.put("hibernate.dialect", DATABASE_DIALECT);
		properties.put("hibernate.show_sql", "true");
		properties.put("hibernate.format_sql", "true");
		//properties.put("hibernate.hbm2ddl.auto", "update");
		return properties;
	}
	
	// Hibernate Transaction Manager
	@Bean
	public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory){
		
		HibernateTransactionManager transactionManager = new HibernateTransactionManager(sessionFactory);
		
		return transactionManager;
	}
}
