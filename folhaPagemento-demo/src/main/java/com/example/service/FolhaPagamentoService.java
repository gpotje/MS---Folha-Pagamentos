package com.example.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.model.Empresa;
import com.example.domain.model.Funcionario;
import com.example.exception.FolhaPagamentoException;
import com.example.mapper.AbstractService;

@Service
public class FolhaPagamentoService extends AbstractService{

	private static final double PERCENTUAL_DESCONTO = 0.38D;

	@Autowired
	private EmpresaService empresaService;
	
	@Autowired
	private FuncionarioService funcionarioService;
	
	@Autowired
	private ContaCorrenteService contaCorrenteService;
	
	@Autowired
	private FolhaMessageSender folhaMessageSender;
	
	
	public void pegarFolhaDaEmpresa(Long idEmpresa) throws Exception {
		
		Empresa findByIdEmpresa = empresaService.findByIdEmpresa(idEmpresa);
		
		List<Funcionario> listEmployeesByCompany = funcionarioService.findAllFuncionarioByEmpresa(idEmpresa);
		
		
		Double valorTotalFolha = listEmployeesByCompany.stream().map( f -> {
			
			try {
				contaCorrenteService.transfer(findByIdEmpresa.getContaCorrente(), f.getContaCorrente(), f.getSalary());
				
			}catch (Exception e) {
				 throw new FolhaPagamentoException("Erro no pagamento do Funcionario: "+ f.getName()  + "-- erro :" + e.getMessage());
			}
			return f.getSalary();
		}).reduce(0D, Double :: sum);
		
		valorTotalFolha = valorTotalFolha * PERCENTUAL_DESCONTO / 100;
		contaCorrenteService.debit(findByIdEmpresa.getContaCorrente(), valorTotalFolha);
		
		String nomes = listEmployeesByCompany.stream().map( f -> f.getName()).collect(Collectors.joining(", "));
		
		folhaMessageSender.enviaEmailDaFolha(findByIdEmpresa.getCorporateName(), nomes);
		
	}
	
}
