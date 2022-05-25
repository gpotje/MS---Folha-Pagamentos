package com.example.regras;


public interface PagamentoStrategy {
	
    Double calcular(Double salary, Double amountTotalPayroll);

}
