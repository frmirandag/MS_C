package com.Atenciones.controller;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.Atenciones.model.Paciente;
import com.Atenciones.service.PacienteService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

@WebMvcTest(PacienteController.class)
public class PacienteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PacienteService pacienteService;


    @Test
    public void testGetPacienteById() throws Exception {
        // Datos de prueba
        Long id = 1L;
        Paciente paciente = new Paciente();
        paciente.setId(id);
        // Agregar más datos de prueba según sea necesario

        // Mock del servicio
        when(pacienteService.getPacienteById(id)).thenReturn(Optional.of(paciente));

        // Ejecución de la solicitud HTTP y verificación de los resultados
        mockMvc.perform(get("/pacientes/{id}", id)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id));
        // Agregar más aserciones según sea necesario
    }


    @Test
    public void testDeletePaciente() throws Exception {
        // Datos de prueba
        Long id = 1L;
        
        // Ejecución de la solicitud HTTP y verificación de los resultados
        mockMvc.perform(delete("/pacientes/{id}", id))
                .andExpect(status().isOk());
        // Agregar más aserciones según sea necesario
    }
}
