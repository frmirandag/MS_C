package com.Atenciones.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.Atenciones.model.AtencionMedica;

public interface AtencionMedicaRepository extends JpaRepository<AtencionMedica, Long>{
    
}
