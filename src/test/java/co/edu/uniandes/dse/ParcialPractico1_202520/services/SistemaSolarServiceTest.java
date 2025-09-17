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
@Import(SistemaSolarService.class)
public class SistemaSolarServiceTest {

    @Autowired
    private SistemaSolarService sistemaSolarService;

    @Autowired
    private SistemaSolarRepository sistemaSolarRepository;

    private SistemaSolar buildValido() {
        SistemaSolar sistema = new SistemaSolar();
        sistema.setNombre("Andromeda-12");
        sistema.setRegion(region.CORE);
        sistema.setRatio(0.25);          // dentro de [0.2, 0.6]
        sistema.setStormtroopers(5000);  // > 1000
        return sistema;
    }

    @Test
    void crearSistemaSolar_ok() {
        SistemaSolar creado = sistemaSolarService.create(buildValido());

        assertThat(creado.getId()).isNotNull();
        assertThat(creado.getNombre()).isEqualTo("Andromeda-12");
        assertThat(creado.getRegion()).isEqualTo(Region.MID_RIM);
        assertThat(creado.getRatio()).isEqualTo(0.25);
        assertThat(creado.getStormtroopers()).isEqualTo(5000);

        assertThat(sistemaSolarRepository.count()).isEqualTo(1);
    }

    @Test
    void crearSistemaSolar_fallaNombreLargo() {
        SistemaSolar s = buildValido();
        s.setNombre("x".repeat(31)); // 31 caracteres

        assertThrows(IllegalArgumentException.class, () -> sistemaSolarService.create(s));
        assertThat(sistemaSolarRepository.count()).isZero();
    }

    @Test
    void crearSistemaSolar_fallaRatioFueraDeRango() {
        SistemaSolar s = buildValido();
        s.setRatio(0.10); // fuera del rango

        assertThrows(IllegalArgumentException.class, () -> sistemaSolarService.create(s));
        assertThat(sistemaSolarRepository.count()).isZero();
    }
}