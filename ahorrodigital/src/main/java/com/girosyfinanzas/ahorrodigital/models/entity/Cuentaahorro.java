package com.girosyfinanzas.ahorrodigital.models.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;


/**
 * The persistent class for the CUENTAAHORRO database table.
 * 
 */
@Entity
@Table(name="CUENTAAHORRO")
//@Data
@NamedQuery(name="Cuentaahorro.findAll", query="SELECT c FROM Cuentaahorro c")
public class Cuentaahorro implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Long cuentaahorroid;

	@Column(nullable=false, precision=4)
	private Integer clavecuenta;

	@Column(nullable=false, length=1)
	private Boolean estado;

	@Column(nullable=false, precision=11)
	private Long numcuenta;

	@Column(nullable=false)
	private Double valor;

	//bi-directional many-to-one association to Cliente
	@JsonIgnoreProperties(value={"cuentaahorro"}, allowSetters = true)
	@OneToMany(mappedBy="cuentaahorro", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
	private List<Cliente> clientes;

	public Long getCuentaahorroid() {
		return cuentaahorroid;
	}

	public void setCuentaahorroid(Long cuentaahorroid) {
		this.cuentaahorroid = cuentaahorroid;
	}

	public Integer getClavecuenta() {
		return clavecuenta;
	}

	public void setClavecuenta(Integer clavecuenta) {
		this.clavecuenta = clavecuenta;
	}

	public Boolean getEstado() {
		return estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}

	public Long getNumcuenta() {
		return numcuenta;
	}

	public void setNumcuenta(Long numcuenta) {
		this.numcuenta = numcuenta;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}
	
	/*@OneToOne(mappedBy = "cuenta", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
	private Cliente cliente;*/
	
	

}