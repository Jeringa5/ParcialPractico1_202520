package co.edu.uniandes.dse.ParcialPractico1_202520.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class PlanetaEntity extends BaseEntity{
    
    private String nombre;

    private int poblacion;

    private int diametro;

    // Cada planeta pertenece a un unico sistema
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "sistema_solar_id", nullable = false)
    private SistemaSolar sistemaSolar;
}