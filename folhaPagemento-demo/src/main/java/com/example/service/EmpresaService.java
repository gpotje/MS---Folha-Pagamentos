package com.example.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.dto.EmpresaDTO;
import com.example.domain.model.ContaCorrente;
import com.example.domain.model.Empresa;
import com.example.exception.BusinessException;
import com.example.mapper.AbstractService;
import com.example.repository.EmpresaRepository;

@Service
public class EmpresaService extends AbstractService {

	@Autowired
	private EmpresaRepository repository;

	public EmpresaDTO create(EmpresaDTO payload) {
		ContaCorrente contaCorrente = new ContaCorrente(payload.getBalance());
		Empresa savedEmpresa = repository.save(payload.toEntity(contaCorrente));
		return convertToDto(savedEmpresa);
	}

	private EmpresaDTO convertToDto(Empresa empresa) {
		EmpresaDTO dto = convertToDTO(empresa, EmpresaDTO.class);
		dto.setBalance(empresa.obterSaldoContaCorrente());
		return dto;
	}

	public List<EmpresaDTO> findAll() {
		List<Empresa> items = repository.findAll();
		if (items == null || items.isEmpty()) {
			throw new BusinessException("01", "No companies found");
		}
		return items.stream().map(e -> convertToDto(e)).collect(Collectors.toList());
	}

	public EmpresaDTO findById(Long id) {
		Optional<Empresa> existing = repository.findById(id);
		if (existing.isPresent())
			return convertToDto(existing.get());

		throw new BusinessException("100", "No companies found with this id: " + id);
	}
	
	public Empresa findByIdEmpresa(Long id) {
		Optional<Empresa> existing = repository.findById(id);
		if (existing.isPresent())
			return existing.get();

		throw new BusinessException("100", "No companies found with this id: " + id);
	}


	
}
