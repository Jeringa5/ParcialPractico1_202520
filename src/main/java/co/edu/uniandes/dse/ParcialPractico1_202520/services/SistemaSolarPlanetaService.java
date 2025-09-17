package co.edu.uniandes.dse.ParcialPractico1_202520.services;
import co.edu.uniandes.dse.ParcialPractico1_202520.entities.PlanetaEntity;
import co.edu.uniandes.dse.ParcialPractico1_202520.entities.SistemaSolar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.uniandes.dse.ParcialPractico1_202520.repositories.PlanetaRepository;
import co.edu.uniandes.dse.ParcialPractico1_202520.repositories.SistemaSolarRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SistemaSolarPlanetaService {

    @Autowired
	private PlanetaRepository planetaRepository;

	@Autowired
	private SistemaSolarRepository sistemaSolarRepository;

    @Transactional
    public void asociarPlanetaASistema(Long sistemaId, Long planetaId) {

        // Verificamos que el sistema y el planeta existan
        SistemaSolar sistema = sistemaSolarRepository.findById(sistemaId)
                .orElseThrow(() -> new IllegalArgumentException("SistemaSolar no encontrado: " + sistemaId));

        PlanetaEntity planeta = planetaRepository.findById(planetaId)
                .orElseThrow(() -> new IllegalArgumentException("Planeta no encontrado: " + planetaId));

        if (planeta.getSistemaSolar() != null &&
            (planeta.getSistemaSolar().getId() != null) &&
            !planeta.getSistemaSolar().getId().equals(sistemaId)) {
            throw new IllegalArgumentException("El planeta ya pertenece a otro sistema solar.");
        }

        long poblacionActual = planeta.getPoblacion();

        long poblacionConNuevo = poblacionActual + (long) planeta.getPoblacion();
        if (poblacionConNuevo <= 0) {
            throw new IllegalStateException("La población total resultante debe ser mayor que 0.");
        }

        double ratioActual = ((double) sistema.getStormtroopers()) / (double) poblacionConNuevo;

        if (ratioActual < sistema.getRatio()) {
            throw new IllegalArgumentException(
                "No se puede asociar el planeta: el ratio actual (" + ratioActual +
                ") seria menor que el ratio minimo del sistema (" + sistema.getRatio() + ").");
        }

        planeta.setSistemaSolar(sistema);
        planetaRepository.save(planeta);
    }
}