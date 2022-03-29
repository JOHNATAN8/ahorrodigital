package com.girosyfinanzas.ahorrodigital.services;

import java.util.Optional;

import com.girosyfinanzas.ahorrodigital.models.entity.Cliente;
import com.girosyfinanzas.ahorrodigital.models.entity.Cuentaahorro;

public interface AhorroService {
	
	public Cliente save(Cliente cliente);
	public Optional<Cliente> findById(Long id);
	public void deleteClientById(Long id);
	public Cuentaahorro saveCuenta(Cuentaahorro cuenta);
	
	

}