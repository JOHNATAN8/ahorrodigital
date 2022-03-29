package com.girosyfinanzas.ahorrodigital.controllers;

import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.girosyfinanzas.ahorrodigital.models.entity.Cliente;
import com.girosyfinanzas.ahorrodigital.models.entity.Cuentaahorro;
import com.girosyfinanzas.ahorrodigital.services.AhorroService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
public class CuentaAhorroController {
	
	@Autowired
	private AhorroService service;
	
	@GetMapping("/cliente/{id}")
	@Operation(summary = "Consultar cliente")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Cliente consultado"),
			@ApiResponse(responseCode = "404", description = "No se encuentra el cliente en el sistema", content = @Content),
			@ApiResponse(responseCode = "400", description = "Datos recibidos inválidos", content = @Content),
			@ApiResponse(responseCode = "500", description = "Error en procesamiento de información", content = @Content)
	})
	public ResponseEntity<?> consultarCliente(@PathVariable Long id) {
		Optional<Cliente> cliente = service.findById(id);
		if(cliente.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(cliente.get());
	}
	
	@PostMapping("/cliente")
	@Operation(summary = "Crear cliente")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Cliente agregado"),
			@ApiResponse(responseCode = "404", description = "No se encuentra el cliente en el sistema", content = @Content),
			@ApiResponse(responseCode = "400", description = "Datos recibidos inválidos", content = @Content),
			@ApiResponse(responseCode = "500", description = "Error en procesamiento de información", content = @Content)
	})
	
	
	// @PostMapping(path="/cliente",consumes="application/json")
	public ResponseEntity<?> crearCliente(@RequestBody Cliente cliente) {
		Cuentaahorro ch = cliente.getCuentaahorro();
		if(ch!= null) {
			ch.setNumcuenta(generarNumeroCuenta());
			ch.setEstado(true);
		}
		service.save(cliente); 
	    return ResponseEntity.ok(cliente); 	      
	}
	
	@PutMapping("/cliente/{id}")
	ResponseEntity<?> actualizarCliente(@RequestBody Cliente nuevoCliente, @PathVariable Long id) {
		if(nuevoCliente != null) {
		  Optional<Cliente> actualizarCliente = service.findById(id);
		  Cliente c = null; 
		  
		  if(actualizarCliente.isPresent()) {
			  c = actualizarCliente.get();
			  c.setNombre(nuevoCliente.getNombre());
			  nuevoCliente.getCuentaahorro().setNumcuenta(generarNumeroCuenta());
			  nuevoCliente.getCuentaahorro().setEstado(true);
			  c.setCuentaahorro(nuevoCliente.getCuentaahorro());
			  c.setDireccion(nuevoCliente.getDireccion());
			  c.setNumdocumento(nuevoCliente.getNumdocumento());
		  }
		  return ResponseEntity.ok(c);
		}
		return ResponseEntity.internalServerError().body(null);
	}
	
	private Long generarNumeroCuenta() {
		char [] chars = "0123456789".toCharArray();
		int charsLength = chars.length;
		Random random = new Random();
		StringBuffer buffer = new StringBuffer();
		for (int i=0;i<11;i++){
		   buffer.append(chars[random.nextInt(charsLength)]);
		}
		try {
			return Long.parseLong(buffer.toString());
		}
		catch (Exception e) {
			return 11111111111l;
		}
	}
	
	@DeleteMapping("/cliente/{id}")
	@Operation(summary = "Eliminar cliente")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Cliente eliminado"),
			@ApiResponse(responseCode = "404", description = "No se encuentra el cliente en el sistema", content = @Content),
			@ApiResponse(responseCode = "400", description = "Datos recibidos inválidos", content = @Content),
			@ApiResponse(responseCode = "500", description = "Error en procesamiento de información", content = @Content)
	})
	public ResponseEntity<?> eliminarCliente(@PathVariable Long id) {
	    service.deleteClientById(id);
	    return ResponseEntity.noContent().build();
	}
}
