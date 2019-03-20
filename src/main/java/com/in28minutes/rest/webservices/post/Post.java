package com.in28minutes.rest.webservices.post;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.in28minutes.rest.webservices.user.User;

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
@Entity
@Table(name = "post")
@ApiModel(description = "Representa o Post do usuário")
public class Post implements Serializable {
	private static final long serialVersionUID = -1987256646860399323L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiModelProperty(notes = "Id do post")
	private Long id;
	
	@ApiModelProperty(notes = "Descrição do post")
	private String description;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@ApiModelProperty(notes = "Usuário que registrou o post")
	@JsonIgnore
	private User user;
}
