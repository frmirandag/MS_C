package com.Atenciones.service;

import com.Atenciones.model.AtencionMedica;
import java.util.List;
import java.util.Optional;

public interface AtencionMedicaService {
    List<AtencionMedica> getAllAtencionesMedicas();
    Optional<AtencionMedica> getAtencionMedicaById(Long id);
    AtencionMedica createAtencionMedica(AtencionMedica atencionMedica);
    AtencionMedica updateAtencionMedica(Long id, AtencionMedica atencionMedica);
    void deleteAtencionMedica(Long id);
    List<AtencionMedica> getAtencionesByPacienteId(Long pacienteId);
}