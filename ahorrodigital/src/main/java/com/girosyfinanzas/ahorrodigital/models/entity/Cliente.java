package com.girosyfinanzas.ahorrodigital.models.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;



/**
 * The persistent class for the CLIENTE database table.
 * 
 */
@Entity
@Table(name="CLIENTE")
//@Data
@NamedQuery(name="Cliente.findAll", query="SELECT c FROM Cliente c")
public class Cliente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Long clienteid;

	@Column(length=50)
	private String ciudad;

	@Column(length=50)
	private String direccion;

	@Column(length=20)
	@Email
	private String email;

	@Temporal(TemporalType.DATE)
	@Column(nullable=false)
	private Date fechanacimiento;

	@Column(nullable=false, length=200)
	private String nombre;

	@Column(nullable=false)
	private Long numdocumento;

	private Long telefono;

	//bi-directional many-to-one association to Cuentaahorro
	@JsonIgnoreProperties(value={"clientes"})
	@ManyToOne
	@JoinColumn(name="CUENTAID", nullable=false)
	private Cuentaahorro cuentaahorro;

	public Long getClienteid() {
		return clienteid;
	}

	public void setClienteid(Long clienteid) {
		this.clienteid = clienteid;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getFechanacimiento() {
		return fechanacimiento;
	}

	public void setFechanacimiento(Date fechanacimiento) {
		this.fechanacimiento = fechanacimiento;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Long getNumdocumento() {
		return numdocumento;
	}

	public void setNumdocumento(Long numdocumento) {
		this.numdocumento = numdocumento;
	}

	public Long getTelefono() {
		return telefono;
	}

	public void setTelefono(Long telefono) {
		this.telefono = telefono;
	}

	public Cuentaahorro getCuentaahorro() {
		return cuentaahorro;
	}

	public void setCuentaahorro(Cuentaahorro cuentaahorro) {
		this.cuentaahorro = cuentaahorro;
	}
	
	/*@OneToOne
    @MapsId
    @JoinColumn(name = "cuentaId")
	private CuentaAhorro cuenta;*/
	
	
	
	

}