package com.Atenciones.service;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.Atenciones.model.Paciente;
import com.Atenciones.repository.PacienteRepository;

@ExtendWith(MockitoExtension.class)
public class PacienteServiceTest {

    @InjectMocks
    private PacienteServiceImpl pacienteService;

    @Mock
    private PacienteRepository pacienteRepositoryMock;



    @Test
    public void testGetPacienteByIdExistente() {
        // Crear una instancia de Paciente para simular los datos de un paciente existente
        Paciente pacienteExistente = new Paciente();
        pacienteExistente.setId(1L);

        // Configurar el comportamiento esperado del repositorio mock para devolver el paciente existente
        when(pacienteRepositoryMock.findById(1L)).thenReturn(Optional.of(pacienteExistente));

        // Invocar el método del servicio que se está probando para obtener el paciente por ID
        Optional<Paciente> pacienteOpt = pacienteService.getPacienteById(1L);

        // Verificar que se obtenga el paciente correctamente y que tenga el ID esperado
        assertTrue(pacienteOpt.isPresent());
        assertEquals(1L, pacienteOpt.get().getId());
    }

    @Test
    public void testGetPacienteByIdNoExistente() {
        // Configurar el comportamiento esperado del repositorio mock para devolver un Optional vacío
        when(pacienteRepositoryMock.findById(2L)).thenReturn(Optional.empty());

        // Invocar el método del servicio que se está probando para obtener el paciente por un ID no existente
        Optional<Paciente> pacienteOpt = pacienteService.getPacienteById(2L);

        // Verificar que no se obtenga ningún paciente
        assertTrue(pacienteOpt.isEmpty());
    }

    @Test
    public void testGetAllPacientes() {
        // Crear una lista de pacientes simulados
        List<Paciente> pacientes = new ArrayList<>();
        Paciente paciente1 = new Paciente();
        paciente1.setId(1L);
        Paciente paciente2 = new Paciente();
        paciente2.setId(2L);
        pacientes.add(paciente1);
        pacientes.add(paciente2);

        // Configurar el comportamiento esperado del repositorio mock para devolver la lista de pacientes
        when(pacienteRepositoryMock.findAll()).thenReturn(pacientes);

        // Invocar el método del servicio que se está probando para obtener todos los pacientes
        List<Paciente> resultado = pacienteService.getAllPacientes();

        // Verificar que se obtenga la lista de pacientes correctamente
        assertEquals(pacientes.size(), resultado.size());
        assertEquals(pacientes.get(0).getId(), resultado.get(0).getId());
        assertEquals(pacientes.get(1).getId(), resultado.get(1).getId());
    }


    @Test
    public void testDeletePaciente() {
        // Ejecutar el método del servicio que se está probando para eliminar un paciente
        pacienteService.deletePaciente(1L);

        // Verificar que se haya llamado al método de eliminación del repositorio mock con el ID correcto
        verify(pacienteRepositoryMock, times(1)).deleteById(1L);
    }
}
