package com.example.domain.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import com.fasterxml.jackson.annotation.JsonProperty;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class FolhaMessage implements Serializable {

	private static final long serialVersionUID = 8802203274773714932L;

	@JsonProperty("email")
	private String email;

	@JsonProperty("funcionarios")
	private String funcionarios;

}
