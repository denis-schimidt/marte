package com.elo7.marte;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;
import org.springframework.test.context.web.WebAppConfiguration;

@Configuration
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
@WebAppConfiguration
@EnableJpaRepositories
@ComponentScan(basePackages = "com.elo7.marte")
public class MarteApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(MarteApplication.class, args);
	}

	@Bean(name = "jsonMessageConverter")
	public MappingJackson2HttpMessageConverter newMappingJackson2HttpMessageConverter() {
		return new MappingJackson2HttpMessageConverter();
	}

	@Bean(name="transactionManager")
	public JpaTransactionManager jpaTransMan() {
		JpaTransactionManager jtManager = new JpaTransactionManager(getEntityManagerFactoryBean().getObject());
		return jtManager;
	}

	@Bean(name="entityManagerFactory")
	public LocalEntityManagerFactoryBean getEntityManagerFactoryBean() {
		LocalEntityManagerFactoryBean lemfb = new LocalEntityManagerFactoryBean();
		lemfb.setPersistenceUnitName("JPA_PU");
		return lemfb;
	}
}
