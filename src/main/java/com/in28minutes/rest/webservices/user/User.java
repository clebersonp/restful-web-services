package com.in28minutes.rest.webservices.user;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {
	
	private Long id;
	private String name;
	
	@JsonProperty(value = "birth-date")
	private LocalDate birthDate;
}