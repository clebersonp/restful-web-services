package com.in28minutes.rest.webservices;

import java.util.Locale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

@SpringBootApplication
public class RestfulWebServicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestfulWebServicesApplication.class, args);
	}
	
	// configuration of internationalization - i18n Locale
//	@Bean
//	public LocaleResolver localeResolver() {
//		SessionLocaleResolver sessionLocaleResolver = new SessionLocaleResolver();
//		sessionLocaleResolver.setDefaultLocale(Locale.forLanguageTag("pt_BR"));
//		return sessionLocaleResolver;
//	}
	
	// configuration of i18n para injetar no request do controller
	@Bean
	public LocaleResolver localeResolver() {
		AcceptHeaderLocaleResolver sessionLocaleResolver = new AcceptHeaderLocaleResolver();
		sessionLocaleResolver.setDefaultLocale(Locale.forLanguageTag("pt_BR"));
		return sessionLocaleResolver;
	}
	
	// configuration of bundle messages
	// configuracao do bundle de messages via .properties-> spring.messages.basename=messages
//	@Bean
//	public ResourceBundleMessageSource	messageSource() {
//		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
//		messageSource.setBasename("messages");
//		return messageSource;
//	}
}
