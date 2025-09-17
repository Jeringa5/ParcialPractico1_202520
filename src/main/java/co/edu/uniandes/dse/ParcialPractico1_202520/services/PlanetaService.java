package co.edu.uniandes.dse.ParcialPractico1_202520.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.uniandes.dse.ParcialPractico1_202520.entities.PlanetaEntity;
import co.edu.uniandes.dse.ParcialPractico1_202520.repositories.PlanetaRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PlanetaService {
    
    @Autowired
    PlanetaRepository planetaRepository;

    public PlanetaService(PlanetaRepository planetaRepository) {
        this.planetaRepository = planetaRepository;
    }

    @Transactional
    public PlanetaEntity crearPlaneta(PlanetaEntity planeta) {
        validarReglasCreacionPlaneta(planeta);
        return planetaRepository.save(planeta);
    }

    private void validarReglasCreacionPlaneta(PlanetaEntity planeta) {
        // El nombre de todo planeta debe terminar con los números romanos 1, 2 o 3

        // Verificamos si es vacio
        if (planeta.getNombre() == null || planeta.getNombre().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del planeta es obligatorio.");
        }

        // Verificamos que termine con numeros romanos
        String nombre = planeta.getNombre().trim();
        if (!(nombre.endsWith("I") || nombre.endsWith("II") || nombre.endsWith("III"))) {
            throw new IllegalArgumentException("El nombre del planeta debe terminar con los numeros romanos I, II o III.");
        }

        // La población del planeta debe ser un número positivo mayor que 0
        int poblacion = planeta.getPoblacion();
        if (poblacion < 0) {
            throw new IllegalArgumentException("La poblacion debe ser mayor a 0");
        }
    }

    public PlanetaEntity create(PlanetaEntity newEntity) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'create'");
    }
}