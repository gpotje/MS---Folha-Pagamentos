package com.example.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Usuario {

	private Long id;
	private String userName;
	private String passWord;
}
