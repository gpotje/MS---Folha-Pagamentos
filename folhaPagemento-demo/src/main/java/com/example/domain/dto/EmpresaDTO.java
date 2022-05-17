package com.example.domain.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

import com.example.domain.model.ContaCorrente;
import com.example.domain.model.Empresa;

import io.swagger.annotations.ApiModel;

@Data
@ApiModel(value = "Empresa")
@NoArgsConstructor
public class EmpresaDTO {

	private Long id;

	@NotNull(message = "O nome  é obrigatório")
	private String corporateName;

	@NotNull(message = "Saldo bancário inicial é obrigatório")
	@Min(value = 0, message = "Valor do saldo bancário deve ser maior ou igual a zero")
	private Double balance;

	@NotNull(message = "O email é obrigatório")
	private String email;

	public EmpresaDTO(Long id, String corporateName, Double balance) {
		this.id = id;
		this.corporateName = corporateName;
		this.balance = balance;
	}

	public Empresa toEntity(ContaCorrente contaCorrente) {
		return new Empresa(this.corporateName, this.email, contaCorrente);
	}
}
