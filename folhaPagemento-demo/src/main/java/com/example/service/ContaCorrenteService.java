package com.example.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.model.ContaCorrente;
import com.example.mapper.AbstractService;
import com.example.repository.ContaCorrenteRepository;

@Service
public class ContaCorrenteService extends AbstractService {

	@Autowired
	ContaCorrenteRepository repository;

	
	@Transactional
	public void debit(ContaCorrente contaCorrente, Double valueToBeDebit) throws Exception {
		
		if(contaCorrente.getBalance() < valueToBeDebit) throw new Exception("There is not enough balance");
		
		contaCorrente.setBalance(contaCorrente.getBalance() - valueToBeDebit);
		repository.save(contaCorrente);
	}
	
	@Transactional
	public void credit(ContaCorrente contaCorrente, Double valueToBeCredit) {
		contaCorrente.setBalance(contaCorrente.getBalance() + valueToBeCredit);
		repository.save(contaCorrente);
	}

	public void transfer(ContaCorrente AccountToBeDebited, ContaCorrente AccountToBeCredited , Double amountToBeTransferred) throws Exception {
		debit(AccountToBeDebited, amountToBeTransferred);
		credit(AccountToBeCredited, amountToBeTransferred);
	}

}
