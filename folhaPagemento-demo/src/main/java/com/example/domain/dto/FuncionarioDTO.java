package com.example.domain.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.example.domain.model.ContaCorrente;
import com.example.domain.model.Empresa;
import com.example.domain.model.Funcionario;

import io.swagger.annotations.ApiModel;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "Funcionario")
public class FuncionarioDTO {

	private Long id;

	@NotNull(message = "Nome do funcionário é obrigatório")
	private String name;

	@NotNull(message = "Salário do funcionário é obrigatório")
	@Min(value = 0, message = "Valor deve ser maior que zero")
	private Double salary;

	@NotNull(message = "Saldo bancário inicial é obrigatório")
	@Min(value = 0, message = "Valor do saldo bancário deve ser maior ou igual a zero")
	private Double balance;

	@NotNull(message = "ID da empresa onde o funcionário trabalha é obrigatório")
	private Long companyId;
	
	public Funcionario toEntity(Empresa empresa, ContaCorrente contaCorrente) {
        return new Funcionario(this.name, this.salary, contaCorrente, empresa);
    }

}
