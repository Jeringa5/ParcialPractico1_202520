package co.edu.uniandes.dse.ParcialPractico1_202520.services;

import co.edu.uniandes.dse.ParcialPractico1_202520.entities.SistemaSolar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.uniandes.dse.ParcialPractico1_202520.repositories.SistemaSolarRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SistemaSolarService {

    @Autowired
    SistemaSolarRepository sistemaSolarRepository;

    public SistemaSolarService(SistemaSolarRepository sistemaSolarRepository) {
        this.sistemaSolarRepository = sistemaSolarRepository;
    }

    @Transactional
    public SistemaSolar create(SistemaSolar sistema) {
        validarReglasCreacionSistema(sistema);
        return sistemaSolarRepository.save(sistema);
    }

    private void validarReglasCreacionSistema(SistemaSolar sistema) {
        // El nombre de un sistema solar debe tener ser menor a los 31 caracteres.

        // Verificamos si es vacio
        if (sistema.getNombre() == null || sistema.getNombre().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del sistema solar es obligatorio.");
        }

        // Verificamos longitud menor a 31
        if (sistema.getNombre().trim().length() >= 31) {
            throw new IllegalArgumentException("El nombre debe tener menos de 31 caracteres.");
        }

        // El sistema obligatoriamente tiene que tener una region
        if (sistema.getRegion() == null) {
            throw new IllegalArgumentException("La región del sistema solar es obligatoria.");
        }

        // El ratio del sistema solar debe estar en un rango mayor o igual a 0,2 y menor o igual a 0,6
        double ratio = sistema.getRatio();
        if (ratio < 0.2 || ratio > 0.6) {
            throw new IllegalArgumentException("El ratio debe estar entre 0.2 y 0.6 (incluidos).");
        }

        // El número de Stormtroopers asignados a un sistema solar debe ser mayor a 1000 unidades
        if (sistema.getStormtroopers() <= 1000) {
            throw new IllegalArgumentException("El número de Stormtroopers debe ser mayor a 1000.");
        }
    }
}