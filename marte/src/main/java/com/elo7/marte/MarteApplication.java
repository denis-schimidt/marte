package com.elo7.marte;

import static springfox.documentation.builders.PathSelectors.regex;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.rest.RepositoryRestMvcAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class, RepositoryRestMvcAutoConfiguration.class })
@ComponentScan
@EnableSwagger2
@EnableJpaRepositories
public class MarteApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(MarteApplication.class, args);
	}

	@Bean
	public Docket newsApi() {
		return new Docket(DocumentationType.SWAGGER_2).groupName("planaltos").apiInfo(apiInfo()).select()
				.paths(regex("/planaltos.*")).build();
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Marte Rest - Documentação e Testes")
				.description("Essa tela serve para mostrar os serviços Rest existentes no projeto para fins de documentação, assim como possível teste real com valores de entrada.")
				.contact(new Contact("Dênis Schimidt de Oliveira", "localhost:8080/planaltos/", "denis.schimidt@gmail.com")).version("1.0.0-SNAPSHOT")
				.build();
	}
}
