package com.in28minutes.rest.webservices.user;

import java.time.LocalDate;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@ApiModel(description = "Modelo de usuário")
public class User {
	
	private Long id;
	
	@Size(min = 3, message = "Name {min.size.user.name}")
	@ApiModelProperty(notes = "Nome do usuário")
	private String name;
	
	@Past(message = "Birth date {past.birth.date.user}")
	@JsonProperty(value = "birth-date")
	@ApiModelProperty(notes = "Data de aniversário do usuário")
	private LocalDate birthDate;
}