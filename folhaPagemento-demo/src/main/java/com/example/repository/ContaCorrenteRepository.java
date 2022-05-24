package com.example.repository;

import org.springframework.stereotype.Repository;

import com.example.domain.model.ContaCorrente;

import org.springframework.data.repository.CrudRepository;


@Repository
public interface ContaCorrenteRepository  extends CrudRepository<ContaCorrente,Long> {

}
