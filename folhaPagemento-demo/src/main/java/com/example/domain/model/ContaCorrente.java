package com.example.domain.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "conta_corrente")
@NoArgsConstructor
@Builder
public class ContaCorrente extends AbstractEntity<Long>{

	private Double balance;

	public ContaCorrente(Double balance) {
		this.balance = balance;
	}

}
