package com.example.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.dto.EmpresaDTO;
import com.example.domain.model.ContaCorrente;
import com.example.domain.model.Empresa;
import com.example.exception.BusinessException;
import com.example.repository.EmpresaRepository;

@Service
public class EmpresaService {

	private ModelMapper modelMapper;

	@Autowired
	private EmpresaRepository repository;

	public EmpresaDTO create(EmpresaDTO payload) {
		ContaCorrente contaCorrente = new ContaCorrente(payload.getBalance());
		Empresa savedEmpresa = repository.save(payload.toEntity(contaCorrente));
		return convertToDto(savedEmpresa);
	}

	private EmpresaDTO convertToDto(Empresa empresa) {
		EmpresaDTO dto = modelMapper.map(empresa, EmpresaDTO.class);
		dto.setBalance(empresa.obterSaldoContaCorrente());
		return dto;
	}

	public List<EmpresaDTO> findAll() {
		List<Empresa> items = repository.findAll();
		if (ObjectIsNotNullOrIsNotEmpty(items)) {
			throw new BusinessException("01", "Nenhuma empresa Encontrada");
		}
		return items.stream().map(this::convertToDto).collect(Collectors.toList());
	}

	public EmpresaDTO findById(Long id) {
		Optional<Empresa> existing = repository.findById(id);
		if (existing.isPresent())
			return convertToDto(existing.get());

		throw new BusinessException("", "Empresa não encontrada com id: " + id);
	}

	public Boolean ObjectIsNotNullOrIsNotEmpty(List<Empresa> obj) {
		if (obj == null || obj.isEmpty()) {
			return true;
		}
		return false;
	}

}
