package com.ejerciciosmesa.tareas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.persistence.MappedSuperclass;

@SpringBootApplication
public class TareasApplication {

	public static void main(String[] args) {
		SpringApplication.run(TareasApplication.class, args);
	}

}
