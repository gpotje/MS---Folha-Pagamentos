package com.example.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.dto.FuncionarioDTO;
import com.example.exception.BusinessException;
import com.example.service.FuncionarioService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "Funcionario")
@RestController
@RequestMapping("/funcionario")
public class FuncionarioController {

	@Autowired
	private FuncionarioService service;
	
	@GetMapping("/all")
	@ApiOperation(value = "Lista de funcionarios cadastrados", response = FuncionarioDTO.class)
	public ResponseEntity<List<FuncionarioDTO>> findAll() {
		return new ResponseEntity<>(service.findAll(),HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	@ApiOperation(value = "Procura por um funcionario pelo id", response = FuncionarioDTO.class)
	public ResponseEntity<FuncionarioDTO> findbyId( @PathVariable Long id) {
		return new ResponseEntity<>(service.findByIdFuncionarioDto(id),HttpStatus.OK);
	}
	
	
	@ApiOperation(value = "Adiciona um funcionario", response = FuncionarioDTO.class)
    @PostMapping
    public ResponseEntity<FuncionarioDTO> create(@Valid @RequestBody FuncionarioDTO payload, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
 
        	 String erros = bindingResult.getFieldErrors().stream().map(e -> e.getDefaultMessage()).collect(Collectors.joining(", "));
             throw new BusinessException("10", " Validation errors : [ " + erros + " ]");
        }
        return new ResponseEntity<>(service.create(payload), HttpStatus.CREATED);
    }
	
	
	
	
	
}
