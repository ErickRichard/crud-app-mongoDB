package com.br.mongodb.app.model;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable{

	private static final long serialVersionUID = -5770517078173590116L;

	@Id
	@ApiModelProperty(value = "id auto generated")
	private String id;

	@Field
	@ApiModelProperty(value = "First Name")
	private String firstName;

	@Field
	@ApiModelProperty(value = "Last Name")
	private String lastName;

}
