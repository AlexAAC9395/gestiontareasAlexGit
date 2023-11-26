package com.ejerciciosmesa.tareas.controller;

import com.ejerciciosmesa.tareas.controllers.TareaController;
import com.ejerciciosmesa.tareas.models.entities.Tarea;
import com.ejerciciosmesa.tareas.models.services.TareaServiceImpl;
import com.ejerciciosmesa.tareas.repository.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.util.AssertionErrors.assertEquals;

@WebMvcTest(TareaController.class)
public class UserControllerTest {
  private Tarea tarea;
  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private TareaServiceImpl tareaService;

  @MockBean
  private TaskRepository taskRepository;

  @BeforeEach
  public void configureTask(){
      tarea = new Tarea();
  }

  @Test
  public void getSuccess() throws Exception {
      Long userId = 1L;
      tarea.setId(userId);
      given(tareaService.getTask(userId)).willReturn(tarea);
      mockMvc.perform(MockMvcRequestBuilders.get("/tarea/{id}", userId))
              .andExpect(MockMvcResultMatchers.status().isOk());
  }

  @Test
  public void getIdNotFound() throws Exception {
      Long userId = (long) -1;
      given(tareaService.getTask(userId)).willReturn(null);

      mockMvc.perform(MockMvcRequestBuilders.get("/tarea/{id}", userId))
              .andExpect(MockMvcResultMatchers.status().isNotFound());
  }

  @Test
  public void getSuccessUserByTitulo() throws Exception {
      String titulo = "Gestor";
      tarea.setTitulo(titulo);
      assertEquals("Titulo: ", titulo, tarea.getTitulo());
      when(taskRepository.findByTitulo(titulo)).thenReturn(tarea);
      given(tareaService.findByTitulo(titulo)).willReturn(tarea);
      tareaService.findByTitulo(titulo);
      verify(tareaService).findByTitulo(titulo);
      mockMvc.perform(MockMvcRequestBuilders.get("/tarea/findByTitulo?titulo=", titulo))
              .andExpect(MockMvcResultMatchers.status().isOk());
  }

  @Test
  public void updateSuccess() throws Exception {
      tarea.setId(5L);
      tarea.setTitulo("Gestion");
      tarea.setAsignada("Alejandro Arroyo");
      tarea.setFechaInicio(LocalDate.parse("2023-11-26"));
      tarea.setFechaFin(LocalDate.parse("2023-11-26"));
      tarea.setDescripcion("Aplicación RestFull");
      tarea.setMateriales("Intellij IDEA");
      tarea.setHoras(10);
      tarea.setCoste(2000.00);
      tarea.setFoto1("foto1.jpg");
      tarea.setFoto2("foto2.jpg");
      tarea.setPorcentajeCompletada(50.00);
      tarea.setCompletada(false);
      when(tareaService.updateTitulo(tarea.getId(), tarea)).thenReturn(tarea);
      when(taskRepository.save(tarea)).thenReturn(tarea);
      System.out.println(tarea);
      mockMvc.perform(MockMvcRequestBuilders.put("/tarea/{id}", tarea.getId())
                      .contentType(MediaType.APPLICATION_JSON)
                      .content("{\"titulo\": \"Gestión\", \"asignada\": \"Alejandro Arroyo\", \"fechaInicio\": \"2023-11-26\", \"fechaFin\": \"2023-11-26\", \"descripcion\": \"Aplicación RestFull\", \"materiales\": \"Intellij IDEA\", \"horas\": \"10\", \"coste\": \"200.00\", \"foto1\": \"foto1.jpg\", \"foto2\": \"foto2.jpg\", \"porcentajeCompletada\": \"50.00\", \"completada\": \"false\" }"))
              .andExpect(MockMvcResultMatchers.status().isOk());
  }

  @Test
  public void deleteSuccess() throws Exception {
      tarea.setId(5L);
      when(tareaService.deleteUser(tarea.getId())).thenReturn(true);
      System.out.println(tarea);
      mockMvc.perform(MockMvcRequestBuilders.delete("/tarea/{id}", tarea.getId()))
              .andExpect(MockMvcResultMatchers.status().isOk());
  }

  @Test
  public void deleteFailed() throws Exception {
      mockMvc.perform(MockMvcRequestBuilders.delete("/tarea/{id}", 4))
              .andExpect(MockMvcResultMatchers.status().isOk());
  }
}
