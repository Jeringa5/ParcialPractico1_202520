package co.edu.uniandes.dse.ParcialPractico1_202520.repositories;

import org.springframework.stereotype.Repository;

import co.edu.uniandes.dse.ParcialPractico1_202520.entities.PlanetaEntity;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


@Repository
public interface PlanetaRepository extends JpaRepository<PlanetaEntity, Long> {

    // Retorna todo los planetas de un sistema solar
    List<PlanetaEntity> findBySistemaSolarId(Long sistemaSolarId);

    // Busqueda por nombre dentro de un sistema especifico
    Optional<PlanetaEntity> findByNombreAndSistemaSolarId(String nombre, Long sistemaSolarId);
}