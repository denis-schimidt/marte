package com.elo7.marte;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.rest.RepositoryRestMvcAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication(exclude={ DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class, RepositoryRestMvcAutoConfiguration.class })
@ComponentScan(basePackages = "com.elo7.marte")
@EnableWebMvc
@EnableJpaRepositories
public class MarteApplication{

	public static void main(String[] args) throws Exception {
		SpringApplication.run(MarteApplication.class, args);
	}
}
