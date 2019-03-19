package com.in28minutes.rest.webservices.helloworld.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.in28minutes.rest.webservices.helloworld.model.HelloWorldBean;

@RestController
public class HelloWorldController {
	
	@Autowired
	private MessageSource messageSource;

	@GetMapping(path = "/hello-world")
	public String getHelloWorld() {
		return "Hello World";
	}
	
	@GetMapping(path = "/hello-world-bean")
	public HelloWorldBean getHelloWorldBean() {
		return new HelloWorldBean("Hello World Bean", "");
	}

	@GetMapping(path = "/hello-world-bean/path-variable/{name}")
	public HelloWorldBean getHelloWorldBeanWithPathVariable(@PathVariable final String name) {
		return new HelloWorldBean(String.format("Hello World Bean, %s", name), name);
	}
	
	// nao precisa passar o locale no request pois esta sendo passado via bean configuration do AcceptHeaderLocaleResolver
//	@GetMapping(path = "/hello-world/good-morning")
//	public String getGoodMorning(@RequestHeader(name = "Accept-Language", required = false, defaultValue = "pt_BR") Locale locale) {
//		return messageSource.getMessage("good.morning", null, locale);
//	}
	
	@GetMapping(path = "/hello-world/good-morning")
	public String getGoodMorning() {
		return messageSource.getMessage("good.morning", null, LocaleContextHolder.getLocale());
	}
}