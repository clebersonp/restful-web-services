package com.in28minutes.rest.webservices.user;

import java.time.LocalDate;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {
	
	private Long id;
	
	@Size(min = 3, message = "Name {min.size.user.name}")
	private String name;
	
	@Past(message = "Birth date {past.birth.date.user}")
	@JsonProperty(value = "birth-date")
	private LocalDate birthDate;
}