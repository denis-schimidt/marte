package com.elo7.marte.infrastructure.config;

import static springfox.documentation.builders.PathSelectors.regex;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
public class SwaggerConfig {

	@Bean
	public Docket newsApi() {
		return new Docket(DocumentationType.SWAGGER_2).groupName("planaltos").apiInfo(apiInfo()).select()
				.paths(regex("/sonda-marte/api/v1/planaltos.*")).build();
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Marte Rest - Documentação e Testes")
				.description("Essa tela serve para mostrar os serviços Rest existentes no projeto para fins de documentação, assim como possível teste real com valores de entrada.")
				.contact(new Contact("Dênis Schimidt de Oliveira", null, "denis.schimidt@gmail.com")).version("1.0.0-SNAPSHOT")
				.build();
	}
}
