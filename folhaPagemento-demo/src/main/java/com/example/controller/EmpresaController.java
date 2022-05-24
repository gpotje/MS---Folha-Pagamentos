package com.example.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.dto.EmpresaDTO;
import com.example.service.EmpresaService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "Empresa")
@RestController
@RequestMapping(value = "/empresa")
public class EmpresaController{
	
	@Autowired
	private EmpresaService service; 
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = "Adiciona uma empresa", response = EmpresaDTO.class)
    public EmpresaDTO create(@Valid @RequestBody EmpresaDTO payload) {
		return service.create(payload);  
        
    }
	
	@GetMapping("/all")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Lista de empresas cadastrados")
	public List<EmpresaDTO> getAll(){
		return service.findAll();
	}
	
	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	 @ApiOperation(value = "Procura por uma empresa pelo id", response = EmpresaDTO.class)
	public EmpresaDTO findById(@PathVariable("id") Long id) {
		return service.findById(id);
	}

}
