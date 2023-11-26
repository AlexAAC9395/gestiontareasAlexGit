package com.ejerciciosmesa.tareas.models.services;

import java.util.List;

import com.ejerciciosmesa.tareas.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



import com.ejerciciosmesa.tareas.models.dao.TareaDAO;
import com.ejerciciosmesa.tareas.models.entities.Tarea;


@Service
public class TareaServiceImpl implements TareaService {
	@Autowired
	private TaskRepository taskRepository;

	private final TareaDAO tareaDAO;
	
	public TareaServiceImpl(
			
			TareaDAO tareaDAO
			) {
		
		this.tareaDAO = tareaDAO;
	}

	@Transactional(readOnly=true)
	@Override
	public List<Tarea> findAll() {
		return (List<Tarea>) tareaDAO.findAll();
	}
	
	@Transactional(readOnly=true)
	@Override
	public Page<Tarea> findAll(Pageable pageable) {
		return tareaDAO.findAll(pageable);
	}

	@Transactional(readOnly=true)
	@Override
	public Tarea findOne(Long id) {
		return tareaDAO.findById(id).orElse(null);
	}

	@Transactional
	@Override
	public void save(Tarea tarea) {
		tareaDAO.save(tarea);
	}

	@Transactional
	@Override
	public void remove(Long id) {
		tareaDAO.deleteById(id);
	}
	
	@Transactional(readOnly=true)
	@Override
	public Long count() {
		return tareaDAO.count();
	}

	public Tarea getTask(Long id){
		return this.taskRepository.findById(id).orElse(null);
	}

	public Object findByTitulo(String titulo) {
		return null;
	}

	public Tarea updateTitulo(Long id, Tarea updateTitulo) {
		// Busca un usuario existente por su ID
		Tarea existingTitulo = taskRepository.findById(id).orElse(null);
		if (existingTitulo != null) {
			// Actualiza los campos del usuario existente con los valores del usuario actualizado
			existingTitulo.setTitulo(updateTitulo.getTitulo());
			existingTitulo.setTitulo(updateTitulo.getTitulo());
			// Guarda y devuelve el usuario actualizado
			return taskRepository.save(existingTitulo);
		}
		return null; // Devuelve null si no se encuentra el usuario con el ID proporcionado
	}

	public boolean deleteUser(Long id) {
		if (taskRepository.findById(id).isPresent()){
			taskRepository.deleteById(id);
			return true;
		}
		return false;
	}

}

