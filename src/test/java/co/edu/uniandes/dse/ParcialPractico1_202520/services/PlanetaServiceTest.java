package co.edu.uniandes.dse.ParcialPractico1_202520.services;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.junit.jupiter.api.Test;

import co.edu.uniandes.dse.ParcialPractico1_202520.entities.PlanetaEntity;
import co.edu.uniandes.dse.ParcialPractico1_202520.exceptions.IllegalOperationException;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

public class PlanetaServiceTest {

    @Autowired
	private PlanetaService planetaService;

    @Autowired
	private TestEntityManager entityManager;

    private PodamFactory factory = new PodamFactoryImpl();

    private List<PlanetaEntity> planetaList = new ArrayList<>();

    @BeforeEach
	void setUp() {
		clearData();
		insertData();
	}
    
    private void clearData() {
		entityManager.getEntityManager().createQuery("delete from PlanetaEntity").executeUpdate();
    }

    private void insertData() {
		for (int i = 0; i < 5; i++) {
			PlanetaEntity planetaEntity = factory.manufacturePojo(PlanetaEntity.class);
			entityManager.persist(planetaEntity);
			planetaList.add(planetaEntity);
		}
	}

    @Test
	void testCreatePlaneta() throws IllegalOperationException {
		PlanetaEntity newEntity = factory.manufacturePojo(PlanetaEntity.class);
		
	}
}
