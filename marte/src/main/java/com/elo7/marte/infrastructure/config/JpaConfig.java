package com.elo7.marte.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;

@Configuration
public class JpaConfig{

	@Bean(name = "transactionManager")
	public JpaTransactionManager newInstanceTransaction() {
		return new JpaTransactionManager(newInstanceEntityManagerFactoryBean().getObject());
	}

	@Bean(name = "entityManagerFactory")
	public LocalEntityManagerFactoryBean newInstanceEntityManagerFactoryBean() {
		LocalEntityManagerFactoryBean lemfb = new LocalEntityManagerFactoryBean();
		lemfb.setPersistenceUnitName("JPA_PU");
		return lemfb;
	}
}
