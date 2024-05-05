package com.Atenciones.service;

import com.Atenciones.model.Paciente;
import java.util.List;
import java.util.Optional;

public interface PacienteService {
    List<Paciente> getAllPacientes();
    Optional<Paciente> getPacienteById(Long id);
    Paciente createPaciente(Paciente paciente);
    Paciente updatePaciente(Long id,Paciente paciente);
    void deletePaciente(Long id);
}
