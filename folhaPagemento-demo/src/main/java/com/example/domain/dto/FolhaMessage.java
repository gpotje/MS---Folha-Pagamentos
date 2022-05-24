package com.example.domain.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.fasterxml.jackson.annotation.JsonProperty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FolhaMessage implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@JsonProperty("email")
	private String email;
	
	@JsonProperty("email")
	private String funcionarios;

}
