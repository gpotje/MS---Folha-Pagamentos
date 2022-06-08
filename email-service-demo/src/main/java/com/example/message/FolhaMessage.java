package com.example.message;

import java.io.Serializable;

import lombok.Data;

@Data
public class FolhaMessage implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String email;
	private String funcionarios;
	
	
	@Override
	public String toString() {
		return "FolhaMessage [email=" + email + ", funcionarios=" + funcionarios + "]";
	}
	
	

}
