package com.example.domain.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Funcionario extends AbstractEntity<Long> {

	@Column(length = 60)
	private String name;

	private Double salary;

	@JsonIgnore
	@OneToOne(cascade = CascadeType.ALL, optional = false)
	@JoinColumn(name = "conta_corrente_id")
	private ContaCorrente contaCorrente;

	@ManyToOne(optional = false)
	@JoinColumn(name = "empresa_id")
	private Empresa empresa;
}
