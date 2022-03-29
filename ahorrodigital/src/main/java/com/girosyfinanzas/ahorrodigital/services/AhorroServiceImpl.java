package com.girosyfinanzas.ahorrodigital.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.girosyfinanzas.ahorrodigital.models.entity.Cliente;
import com.girosyfinanzas.ahorrodigital.models.entity.Cuentaahorro;
import com.girosyfinanzas.ahorrodigital.models.repository.ClienteRepository;
import com.girosyfinanzas.ahorrodigital.models.repository.CuentaAhorroRepository;

@Service
public class AhorroServiceImpl implements AhorroService {
	
	@Autowired
	private ClienteRepository repository;
	@Autowired
	private CuentaAhorroRepository chRepository;
	

	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public Cliente save(Cliente cliente) {
		saveCuenta(cliente.getCuentaahorro());
		return repository.save(cliente);
	}
	
	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public Cuentaahorro saveCuenta(Cuentaahorro cuenta) {
		return chRepository.save(cuenta);
	}
	

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public void deleteClientById(Long id) {
		repository.deleteById(id);
	}

	@Transactional(readOnly = true)
	public Optional<Cliente> findById(Long id) {
		return repository.findById(id);
	}
	
	
	
}
