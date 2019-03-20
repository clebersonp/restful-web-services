package com.in28minutes.rest.webservices.user;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.in28minutes.rest.webservices.post.Post;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ApiModel(description = "Modelo de usuário")
@Entity
@Table(name = "user")
public class User implements Serializable {
	
	private static final long serialVersionUID = 3177421811265396811L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Size(min = 3, message = "Name {min.size.user.name}")
	@ApiModelProperty(notes = "Nome do usuário")
	@Column(name = "name")
	private String name;
	
	@Past(message = "Birth date {past.birth.date.user}")
	@JsonProperty(value = "birth-date")
	@ApiModelProperty(notes = "Data de aniversário do usuário")
	@Column(name = "birth_date")
	private LocalDate birthDate;
	
	@OneToMany(mappedBy = "user")
	private List<Post> posts;
}