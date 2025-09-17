package co.edu.uniandes.dse.ParcialPractico1_202520.repositories;

import org.springframework.stereotype.Repository;

import co.edu.uniandes.dse.ParcialPractico1_202520.entities.SistemaSolar;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface SistemaSolarRepository extends JpaRepository<SistemaSolar, Long> {

    // Busqueda por nombre de un sistema solar
    Optional<SistemaSolar> findByNombre(String nombre);

    boolean existsByNombre(String nombre);
}