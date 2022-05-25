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
import com.example.repository.FuncionarioRepository;

@Service
public class FuncionarioService extends AbstractService {

	@Autowired
	private FuncionarioRepository funcionarioRepository;

	@Autowired
	private EmpresaService empresaService;

	public List<FuncionarioDTO> findAll() {
		List<Funcionario> items = funcionarioRepository.findAll();
		if (items == null || items.isEmpty())
			throw new BusinessException("01", "No Employees found");

		return items.stream().map(this::convertToDtoFuncionario).collect(Collectors.toList());
	}

	public FuncionarioDTO create(FuncionarioDTO payload) {
		Empresa empresaOptional = empresaService.findByIdEmpresa(payload.getId());
		ContaCorrente contaCorrente = new ContaCorrente(payload.getBalance());
		Funcionario savedFuncionario = funcionarioRepository.save(payload.toEntity(empresaOptional, contaCorrente));
		return convertToDtoFuncionario(savedFuncionario);
	}

	private Funcionario findByIdFuncionario(Long id) {
		Optional<Funcionario> existingItemOptional = funcionarioRepository.findById(id);
		if (existingItemOptional.isPresent()) {
			return existingItemOptional.get();
		}
		throw new BusinessException("01", "Employee not found with id:" + id);
	}

	public FuncionarioDTO update(Long id, FuncionarioDTO payload) {
		Empresa findByIdEmpresa = empresaService.findByIdEmpresa(payload.getId());
		Funcionario findByIdFuncionario = findByIdFuncionario(id);
		
		if(findByIdFuncionario != null) {
			findByIdFuncionario.setName(payload.getName());
			findByIdFuncionario.setSalary(payload.getSalary());
			findByIdFuncionario.setEmpresa(findByIdEmpresa);
			return convertToDtoFuncionario(funcionarioRepository.save(findByIdFuncionario));
		}
		throw new BusinessException("01", "Employee not found with id:" + id);
		
	}
	
	public FuncionarioDTO findByIdFuncionarioDto(Long id) {
		return convertToDtoFuncionario(findByIdFuncionario(id));
	}
	
	public void delete(Long id) {
		Funcionario findByIdFuncionarioDelete = findByIdFuncionario(id);
		funcionarioRepository.delete(findByIdFuncionarioDelete);
	}

	public Double getSaldo(Long id) {
		Funcionario findByIdFuncionario = findByIdFuncionario(id);
		return findByIdFuncionario.obterSaldoContaCorrente();
	}
		
	public List<Funcionario> findAllFuncionarioByEmpresa(Empresa empresa){
		List<Funcionario> findAllByEmpresa = funcionarioRepository.findAllByEmpresa(empresa);
		if(findAllByEmpresa != null && !findAllByEmpresa.isEmpty()) {
			return findAllByEmpresa;
		}
		throw new BusinessException("01", "The company " + empresa.getCorporateName() + " has no employees ");
	}
	
	
	public List<Funcionario> findAllFuncionarioByEmpresa(Long idEmpresa){
		Empresa findByIdEmpresa = empresaService.findByIdEmpresa(idEmpresa);
		return findAllFuncionarioByEmpresa(findByIdEmpresa);		
	}

	private FuncionarioDTO convertToDtoFuncionario(Funcionario funcionario) {
		FuncionarioDTO dto = convertEntityToDTO(funcionario, FuncionarioDTO.class);
		dto.setBalance(funcionario.obterSaldoContaCorrente());
		dto.setCompanyId(funcionario.getEmpresa().getId());
		return dto;
	}

}
