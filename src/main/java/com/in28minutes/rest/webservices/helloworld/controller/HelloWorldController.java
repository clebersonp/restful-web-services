package com.in28minutes.rest.webservices.helloworld.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.in28minutes.rest.webservices.helloworld.model.HelloWorldBean;

@RestController
public class HelloWorldController {

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
}