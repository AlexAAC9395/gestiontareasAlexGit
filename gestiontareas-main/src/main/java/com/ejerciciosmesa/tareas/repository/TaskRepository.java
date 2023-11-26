package com.ejerciciosmesa.tareas.repository;

import com.ejerciciosmesa.tareas.models.entities.Tarea;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Tarea, Long> {
    Tarea findByTitulo(String email);
}
