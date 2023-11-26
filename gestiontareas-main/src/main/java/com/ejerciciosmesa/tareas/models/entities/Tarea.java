package com.ejerciciosmesa.tareas.models.entities;


import java.io.Serial;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Entity;

import java.util.Objects;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


import javax.validation.constraints.NotBlank;

import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;
import javax.persistence.Lob;;



@Getter
@Entity
@Table(name="tarea")
public class Tarea implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

@Column(name="titulo")
@NotBlank(message = "El título de la tarea no puede estar en blanco")
private String titulo;


@Column(name="asignada")
@NotBlank(message = "El campo debe tener un nombre de la persona responsable de realizar la tarea")
private String asignada;


@DateTimeFormat(pattern = "yyyy-MM-dd")
//@NotBlank(message = "La tarea debe tener una fecha de inicio")
@Column(name="fechainicio")
private LocalDate fechaInicio;


@DateTimeFormat(pattern = "yyyy-MM-dd")
//@NotBlank(message = "La tarea debe tener una fecha de entrega")
@Column(name="fechafin")
private LocalDate fechaFin;


@Lob
@Column(name="descripcion")
@NotBlank(message = "Especificar más detalles de la tarea")
private String descripcion;


@Lob
@Column(name="materiales")
@NotBlank(message = "Especificar al menos un material para la realización de la tarea")
private String materiales;


@Column(name="horas")
//@NotBlank(message = "Debe contener un aproximado de las horas para realizar la tarea")
private Integer horas;


@Column(name="coste")
//@NotBlank(message = "Especificar una cotización o presupuesto de la tarea")
private Double coste;


@Column(name="foto1")
private String foto1;


@Column(name="foto2")
private String foto2;


@Column(name="porcentajecompletada")
//@NotBlank(message = "Debe contener el porcentaje de avance de la tarea")
private Double porcentajeCompletada;


@Column(name="completada")
private Boolean completada;



	
	public Tarea() {}


	public void setTitulo(String titulo) {
	this.titulo = titulo;
}

	public void setAsignada(String asignada) {
	this.asignada = asignada;
}

	public void setFechaInicio(LocalDate fechaInicio) {
	this.fechaInicio = fechaInicio;
}

	public void setFechaFin(LocalDate fechaFin) {
	this.fechaFin = fechaFin;
}

	public void setDescripcion(String descripcion) {
	this.descripcion = descripcion;
}

	public void setMateriales(String materiales) {
	this.materiales = materiales;
}

	public void setHoras(Integer horas) {
	this.horas = horas;
}

	public void setCoste(Double coste) {
	this.coste = coste;
}

	public void setFoto1(String foto1) {
	this.foto1 = foto1;
}

	public void setFoto2(String foto2) {
	this.foto2 = foto2;
}

	public void setPorcentajeCompletada(Double porcentajeCompletada) {
	this.porcentajeCompletada = porcentajeCompletada;
}

	public void setCompletada(Boolean completada) {
	this.completada = completada;
}

	

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tarea other = (Tarea) obj;
		return Objects.equals(id, other.id);
	}


	@Serial
	private static final long serialVersionUID = 1L;

	public void setId(Long taskId) {
	}
}

