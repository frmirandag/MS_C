package com.Atenciones.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.Atenciones.model.AtencionMedica;
import com.Atenciones.service.AtencionMedicaService;

import java.util.List;
import java.util.Optional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

@RestController
@RequestMapping("/atenciones-medicas")
public class AtencionMedicaController {

    private static final Logger log = LoggerFactory.getLogger(AtencionMedicaController.class);

    @Autowired
    private AtencionMedicaService atencionMedicaService;

    @GetMapping("/{pacienteId}/atenciones")
    public ResponseEntity<List<AtencionMedica>> getAtencionesByPacienteId(@PathVariable Long pacienteId) {
        List<AtencionMedica> atenciones = atencionMedicaService.getAtencionesByPacienteId(pacienteId);
        if (atenciones.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(atenciones);
    }



    @GetMapping
    public List<AtencionMedica> getAllAtencionesMedicas() {
        log.info("GET /atenciones-medicas");
        log.info("Retornando todas las atenciones médicas");
        return atencionMedicaService.getAllAtencionesMedicas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getAtencionMedicaById(@PathVariable @NotNull @Min(1) Long id) {
        Optional<AtencionMedica> atencionMedica = atencionMedicaService.getAtencionMedicaById(id);
        if (atencionMedica.isEmpty()) {
            log.error("No se encontró la atención médica con ID {}", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("No se encontró la atención médica con ID " + id));
        }
        return ResponseEntity.ok(atencionMedica);
    }

    @PostMapping
    public ResponseEntity<Object> createAtencionMedica(@Valid @RequestBody AtencionMedica atencionMedica) {
        AtencionMedica createdAtencionMedica = atencionMedicaService.createAtencionMedica(atencionMedica);
        if (createdAtencionMedica == null) {
            log.error("Error al crear la atención médica {}", atencionMedica);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponse("Error al crear la atención médica"));
        }
        return ResponseEntity.ok(createdAtencionMedica);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateAtencionMedica(@PathVariable @NotNull @Min(1) Long id, @Valid @RequestBody AtencionMedica atencionMedica) {
        AtencionMedica updatedAtencionMedica = atencionMedicaService.updateAtencionMedica(id, atencionMedica);
        if (updatedAtencionMedica == null) {
            log.error("No se encontró la atención médica con ID {}", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("No se encontró la atención médica con ID " + id));
        }
        return ResponseEntity.ok(updatedAtencionMedica);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteAtencionMedica(@PathVariable @NotNull @Min(1) Long id) {
        atencionMedicaService.deleteAtencionMedica(id);
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
