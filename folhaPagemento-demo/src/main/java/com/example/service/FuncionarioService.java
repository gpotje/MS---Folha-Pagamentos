package com.example.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.example.domain.dto.FuncionarioDTO;
import com.example.domain.model.ContaCorrente;
import com.example.domain.model.Empresa;
import com.example.domain.model.Funcionario;
import com.example.exception.BusinessException;
import com.example.mapper.AbstractService;
import com.example.repository.EmpresaRepository;
import com.example.repository.FuncionarioRepository;

@Service
public class FuncionarioService extends AbstractService {

	@Autowired
	private FuncionarioRepository funcionarioRepository;

	@Autowired
	private EmpresaRepository empresaRepository;

	public List<FuncionarioDTO> findAll() {
		List<Funcionario> items = funcionarioRepository.findAll();
		if (items == null || items.isEmpty())throw new BusinessException("01", "No Employees found");
		
		return items.stream()
				.map(this::convertToDtoFuncionario)
				.collect(Collectors.toList());
		}
	
	  public FuncionarioDTO create(FuncionarioDTO payload) {
	        Optional<Empresa> empresaOptional = empresaRepository.findById(payload.getCompanyId());
	        if (!empresaOptional.isPresent()) {
	            throw new BusinessException("01", "Company not found with id: "+payload.getCompanyId());
	        }
	        ContaCorrente contaCorrente = new ContaCorrente(payload.getBalance());
	        Funcionario savedFuncionario = funcionarioRepository.save(payload.toEntity(empresaOptional.get(), contaCorrente));

	        return convertToDtoFuncionario(savedFuncionario);
	    }

	
	

	private FuncionarioDTO convertToDtoFuncionario(Funcionario funcionario) {
		FuncionarioDTO dto = convertEntityToDTO(funcionario, FuncionarioDTO.class);
		dto.setBalance(funcionario.obterSaldoContaCorrente());
		dto.setCompanyId(funcionario.getEmpresa().getId());
		return dto;
	}

}
