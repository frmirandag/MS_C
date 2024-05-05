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

import com.Atenciones.model.AtencionMedica;
import com.Atenciones.repository.AtencionMedicaRepository;

@ExtendWith(MockitoExtension.class)
public class AtencionMedicaServiceTest {

    @InjectMocks
    private AtencionMedicaServiceImpl atencionMedicaService;

    @Mock
    private AtencionMedicaRepository atencionMedicaRepositoryMock;

    @Test
    public void testGetAtencionMedicaByIdExistente() {
        // Crear una instancia de AtencionMedica para simular los datos de una atención médica existente
        AtencionMedica atencionMedicaExistente = new AtencionMedica();
        atencionMedicaExistente.setId(1L);

        // Configurar el comportamiento esperado del repositorio mock para devolver la atención médica existente
        when(atencionMedicaRepositoryMock.findById(1L)).thenReturn(Optional.of(atencionMedicaExistente));

        // Invocar el método del servicio que se está probando para obtener la atención médica por ID
        Optional<AtencionMedica> atencionMedicaOpt = atencionMedicaService.getAtencionMedicaById(1L);

        // Verificar que se obtenga la atención médica correctamente y que tenga el ID esperado
        assertTrue(atencionMedicaOpt.isPresent());
        assertEquals(1L, atencionMedicaOpt.get().getId());
    }

    @Test
    public void testGetAtencionMedicaByIdNoExistente() {
        // Configurar el comportamiento esperado del repositorio mock para devolver un Optional vacío
        when(atencionMedicaRepositoryMock.findById(2L)).thenReturn(Optional.empty());

        // Invocar el método del servicio que se está probando para obtener la atención médica por un ID no existente
        Optional<AtencionMedica> atencionMedicaOpt = atencionMedicaService.getAtencionMedicaById(2L);

        // Verificar que no se obtenga ninguna atención médica
        assertTrue(atencionMedicaOpt.isEmpty());
    }

    @Test
    public void testGetAllAtencionesMedicas() {
        // Crear una lista de atenciones médicas simuladas
        List<AtencionMedica> atencionesMedicas = new ArrayList<>();
        AtencionMedica atencion1 = new AtencionMedica();
        atencion1.setId(1L);
        AtencionMedica atencion2 = new AtencionMedica();
        atencion2.setId(2L);
        atencionesMedicas.add(atencion1);
        atencionesMedicas.add(atencion2);

        // Configurar el comportamiento esperado del repositorio mock para devolver la lista de atenciones médicas
        when(atencionMedicaRepositoryMock.findAll()).thenReturn(atencionesMedicas);

        // Invocar el método del servicio que se está probando para obtener todas las atenciones médicas
        List<AtencionMedica> resultado = atencionMedicaService.getAllAtencionesMedicas();

        // Verificar que se obtenga la lista de atenciones médicas correctamente
        assertEquals(atencionesMedicas.size(), resultado.size());
        assertEquals(atencionesMedicas.get(0).getId(), resultado.get(0).getId());
        assertEquals(atencionesMedicas.get(1).getId(), resultado.get(1).getId());
    }

    @Test
    public void testDeleteAtencionMedica() {
        // Ejecutar el método del servicio que se está probando para eliminar una atención médica
        atencionMedicaService.deleteAtencionMedica(1L);

        // Verificar que se haya llamado al método de eliminación del repositorio mock con el ID correcto
        verify(atencionMedicaRepositoryMock, times(1)).deleteById(1L);
    }
}
