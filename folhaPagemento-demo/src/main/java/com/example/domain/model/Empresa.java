package com.example.domain.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.hibernate.annotations.NotFound;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
@Builder
public class Empresa  extends AbstractEntity<Long>{

		
	@Column(name="corporate_name", length = 80)
	private String corporateName;
	
	@Column(length = 100)
	@NotFound
	private String email;
	
	@NotFound
	@JsonIgnore
	@OneToOne(cascade = CascadeType.ALL, optional = false )
	@JoinColumn(name = "conta_corrente_id")
	private ContaCorrente contaCorrente;
	
	 public Empresa(String corporateName, String email, ContaCorrente contaCorrente) {
	        this.corporateName = corporateName;
	        this.email = email;
	        this.contaCorrente = contaCorrente;
	    }
	 
	 public Double obterSaldoContaCorrente() {
		 return this.contaCorrente.getBalance();
	 }
}
