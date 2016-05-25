package com.elo7.marte;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;

@Configuration
@EnableAutoConfiguration
@EnableJpaRepositories
@ComponentScan
public class MarteApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(MarteApplication.class, args);
	}

	@Bean(name="jsonMessageConverter")
	public MappingJackson2HttpMessageConverter newMappingJackson2HttpMessageConverter(){
		return new MappingJackson2HttpMessageConverter();
	}
	
	@Bean
	public LocalEntityManagerFactoryBean entityManagerFactory(){
	     LocalEntityManagerFactoryBean factoryBean = new LocalEntityManagerFactoryBean();
	    factoryBean.setPersistenceUnitName("JPA_PU");
	    return factoryBean;
	}
}