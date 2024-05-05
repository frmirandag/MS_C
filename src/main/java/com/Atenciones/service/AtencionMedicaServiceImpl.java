package com.Atenciones.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Atenciones.model.AtencionMedica;
import com.Atenciones.repository.AtencionMedicaRepository;


@Service
public class AtencionMedicaServiceImpl implements AtencionMedicaService {

    @Autowired
    private AtencionMedicaRepository atencionMedicaRepository;

    @Override
    public List<AtencionMedica> getAllAtencionesMedicas() {
        return atencionMedicaRepository.findAll();
    }

    @Override
    public Optional<AtencionMedica> getAtencionMedicaById(Long id) {
        return atencionMedicaRepository.findById(id);
    }

    @Override
    public AtencionMedica createAtencionMedica(AtencionMedica atencionMedica) {
        return atencionMedicaRepository.save(atencionMedica);
    }

    @Override
    public AtencionMedica updateAtencionMedica(Long id, AtencionMedica atencionMedica) {
        if (atencionMedicaRepository.existsById(id)) {
            atencionMedica.setId(id);
            return atencionMedicaRepository.save(atencionMedica);
        } else {
            return null;
        }
    }

    @Override
    public void deleteAtencionMedica(Long id) {
        atencionMedicaRepository.deleteById(id);
    }

    @Override
    public List<AtencionMedica> getAtencionesByPacienteId(Long pacienteId) {
        
        throw new UnsupportedOperationException("Unimplemented method 'getAtencionesByPacienteId'");
    }
}
