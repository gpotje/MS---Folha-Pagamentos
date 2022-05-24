package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.domain.model.Empresa;

public interface EmpresaRepository  extends JpaRepository<Empresa,Long>{

}
