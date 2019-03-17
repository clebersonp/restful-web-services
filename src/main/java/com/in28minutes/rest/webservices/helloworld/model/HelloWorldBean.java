package com.in28minutes.rest.webservices.helloworld.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class HelloWorldBean {
	private String message;
	private String name;
}