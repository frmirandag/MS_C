package com.Atenciones.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.Atenciones.model.Paciente;

public interface PacienteRepository extends JpaRepository<Paciente, Long>{
    
}
