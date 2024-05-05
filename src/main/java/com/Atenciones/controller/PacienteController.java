package com.Atenciones.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.Atenciones.model.Paciente;
import com.Atenciones.service.PacienteService;

import java.util.List;
import java.util.Optional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import jakarta.validation.constraints.*;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    private static final Logger log = LoggerFactory.getLogger(PacienteController.class);

    @Autowired
    private PacienteService pacienteService;

    @GetMapping
    public List<Paciente> getAllPacientes() {
        log.info("GET /pacientes");
        log.info("Retornando todos los pacientes");
        return pacienteService.getAllPacientes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getPacienteById(@PathVariable @NotNull @Min(1) Long id) {
        Optional<Paciente> paciente = pacienteService.getPacienteById(id);
        if (paciente.isEmpty()) {
            log.error("No se encontró el paciente con ID {}", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("No se encontró el paciente con ID " + id));
        }
        return ResponseEntity.ok(paciente);
    }

    /*@PostMapping
    public ResponseEntity<Object> createPaciente(@Valid @RequestBody Paciente paciente) {
        Paciente createdPaciente = pacienteService.createPaciente(paciente);
        if (createdPaciente == null) {
            log.error("Error al crear el paciente {}", paciente);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponse("Error al crear el paciente"));
        }
        return ResponseEntity.ok(createdPaciente);
    }
*/  
// Método para actualizar un paciente existente
@PutMapping("/{id}")
public ResponseEntity<Object> updatePaciente(@PathVariable @NotNull @Min(1) Long id, @Valid @RequestBody Paciente paciente) {
    try {
        Paciente updatedPaciente = pacienteService.updatePaciente(id, paciente);
        if (updatedPaciente != null) {
            return ResponseEntity.ok(updatedPaciente);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse("No se encontró el paciente con ID " + id));
        }
    } catch (ValidationException ex) {
        log.error("Error de validación al actualizar el paciente: {}", ex.getMessage(), ex);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse("Error de validación al actualizar el paciente: " + ex.getMessage()));
    } catch (Exception ex) {
        log.error("Error interno al actualizar el paciente: {}", ex.getMessage(), ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorResponse("Error interno al actualizar el paciente: " + ex.getMessage()));
    }
}

// Método para crear un nuevo paciente
@PostMapping
public ResponseEntity<Object> createPaciente(@Valid @RequestBody Paciente paciente) {
    try {
        Paciente createdPaciente = pacienteService.createPaciente(paciente);
        if (createdPaciente != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(createdPaciente);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorResponse("Error al crear el paciente: no se pudo guardar en la base de datos"));
        }
    } catch (ValidationException ex) {
        log.error("Error de validación al crear el paciente: {}", ex.getMessage(), ex);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse("Error de validación al crear el paciente: " + ex.getMessage()));
    } catch (Exception ex) {
        log.error("Error interno al crear el paciente: {}", ex.getMessage(), ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorResponse("Error interno al crear el paciente: " + ex.getMessage()));
    }
}



    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletePaciente(@PathVariable @NotNull @Min(1) Long id) {
        pacienteService.deletePaciente(id);
        return ResponseEntity.ok().build();
    }

    static class ErrorResponse {
        private final String message;

        public ErrorResponse(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }
}
