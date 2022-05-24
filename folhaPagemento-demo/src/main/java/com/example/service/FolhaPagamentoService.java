package com.example.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.model.Empresa;
import com.example.exception.BusinessException;
import com.example.mapper.AbstractService;
import com.example.repository.EmpresaRepository;
import com.example.repository.FuncionarioRepository;

@Service
public class FolhaPagamentoService extends AbstractService{

	@Autowired
	private EmpresaRepository empresaRepository;
	
	@Autowired
	private FuncionarioRepository funcionarioRepository;
	
//	@Autowired
//	private ContaCorrenteService contaCorrenteService;
//	
//	@Autowired
//	private FolhaMessageSender folhaMessageSender;
	
	
	public void pegarFolhaDaEmpresa(Long id) throws Exception {
		Optional<Empresa> empresaOptional = empresaRepository.findById(id);
        if (!empresaOptional.isPresent()) {
        	throw new BusinessException("100", "No companies found with this id: " + id);
        }
		
        Empresa empresa =  empresaOptional.get();
        funcionarioRepository.findAllByEmpresa(empresa);
		
		
	}
	
}
