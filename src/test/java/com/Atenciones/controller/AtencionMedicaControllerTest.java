package com.Atenciones.controller;

import com.Atenciones.service.AtencionMedicaService;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

public class AtencionMedicaControllerTest {

    @Mock
    private AtencionMedicaService atencionMedicaService;

    @InjectMocks
    private AtencionMedicaController atencionMedicaController;

    @SuppressWarnings("deprecation")
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        MockMvcBuilders.standaloneSetup(atencionMedicaController).build();
    }



    // Test similar methods for createAtencionMedica, updateAtencionMedica, deleteAtencionMedica
}
