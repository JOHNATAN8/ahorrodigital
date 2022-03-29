package com.girosyfinanzas.ahorrodigital.models.repository;

import org.springframework.data.repository.CrudRepository;

import com.girosyfinanzas.ahorrodigital.models.entity.Cliente;



public interface ClienteRepository extends CrudRepository<Cliente, Long> {
	
	
}


