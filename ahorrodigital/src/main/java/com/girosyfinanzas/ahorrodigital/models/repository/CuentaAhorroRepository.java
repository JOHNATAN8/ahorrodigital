package com.girosyfinanzas.ahorrodigital.models.repository;

import org.springframework.data.repository.CrudRepository;

import com.girosyfinanzas.ahorrodigital.models.entity.Cliente;
import com.girosyfinanzas.ahorrodigital.models.entity.Cuentaahorro;



public interface CuentaAhorroRepository extends CrudRepository<Cuentaahorro, Long> {
	
	
}


