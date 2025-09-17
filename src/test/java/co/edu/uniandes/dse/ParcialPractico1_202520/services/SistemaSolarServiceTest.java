package co.edu.uniandes.dse.ParcialPractico1_202520.services;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.junit.jupiter.api.Test;

import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import co.edu.uniandes.dse.ParcialPractico1_202520.entities.SistemaSolar;
import co.edu.uniandes.dse.ParcialPractico1_202520.repositories.SistemaSolarRepository;

@DataJpaTest
@Import(SistemaSolarService.class) // Importa el servicio real
class SistemaSolarServiceTest {

    @Autowired
    private SistemaSolarService sistemaSolarService;

    @Autowired
    private SistemaSolarRepository sistemaSolarRepository;

    private SistemaSolar buildValido() {
        SistemaSolar s = new SistemaSolar();
        s.setNombre("Andromeda-12");
        s.setRegion(Region.MID_RIM);
        s.setRatio(0.25);
        s.setStormtroopers(5001);
        return s;
    }

    @Test
    @DisplayName("Crear SistemaSolar - éxito")
    void crearSistemaSolar_ok() {
        SistemaSolar creado = sistemaSolarService.create(buildValido());

        assertNotNull(creado.getId());
        assertEquals("Andromeda-12", creado.getNombre());
        assertEquals(Region.MID_RIM, creado.getRegion());
        assertEquals(0.25, creado.getRatio(), 1e-9);
        assertEquals(5001, creado.getStormtroopers());

        assertEquals(1, sistemaSolarRepository.count());
    }

    @Test
    @DisplayName("Crear SistemaSolar - falla por nombre >= 31 chars")
    void crearSistemaSolar_fallaNombreLargo() {
        SistemaSolar s = buildValido();
        s.setNombre("x".repeat(31));

        assertThrows(IllegalArgumentException.class, () -> sistemaSolarService.create(s));
        assertEquals(0, sistemaSolarRepository.count());
    }

    @Test
    @DisplayName("Crear SistemaSolar - falla por ratio fuera de [0.2, 0.6]")
    void crearSistemaSolar_fallaRatioFueraDeRango() {
        SistemaSolar s = buildValido();
        s.setRatio(0.10); // fuera de rango

        assertThrows(IllegalArgumentException.class, () -> sistemaSolarService.create(s));
        assertEquals(0, sistemaSolarRepository.count());
    }
}