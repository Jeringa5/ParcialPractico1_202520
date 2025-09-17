package co.edu.uniandes.dse.ParcialPractico1_202520.entities;

import java.util.ArrayList;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.Data;
import uk.co.jemos.podam.common.PodamExclude;

import java.util.List;

@Data
@Entity
public class SistemaSolar extends BaseEntity {

    private String nombre;

    // Posibles son “CORE”, “MID_RIM”, “OUTER_RIM”, “COLONIES” y “WILD_SPACE”
    enum Region {
        CORE,
        MID_RIM,
        OUTER_RIM,
        COLONIES,
        WILD_SPACE
    }

    private Region region;

    // Relación de número de Stormtroopers sobre la población total del sistema solar.
    private double ratio;

    private int Stormtroopers;

    // Relacion con planeta, 1 sistema solar puede tener multiples planetas
    @PodamExclude
	@OneToMany(mappedBy = "sistemaSolar", fetch = FetchType.LAZY)
	private List<PlanetaEntity> planetas = new ArrayList<>();
}