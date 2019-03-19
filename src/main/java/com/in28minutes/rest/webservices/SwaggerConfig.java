package com.in28minutes.rest.webservices;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.StringVendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(getApiInfo()).enableUrlTemplating(true).forCodeGeneration(true);
	}

	private ApiInfo getApiInfo() {
		return new ApiInfo("Documentação Swagger", "Documentação API Restful da aplicação", "1.0.0", "By MySelf",
				new Contact("Fulano da Silva", "www.fulano.org", "fulano@gmail.com"), null, null,
				Arrays.asList(new StringVendorExtension("Vendor", "Extension")));
	}
}