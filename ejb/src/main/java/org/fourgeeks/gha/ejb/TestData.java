package org.fourgeeks.gha.ejb;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.fourgeeks.gha.domain.gmh.Brand;
import org.fourgeeks.gha.domain.gmh.Eia;
import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.domain.gmh.Manufacturer;
import org.fourgeeks.gha.domain.mix.User;
import org.fourgeeks.gha.ejb.gmh.EiaServiceRemote;

@Startup
@Singleton
public class TestData {

	@PersistenceContext
	EntityManager em;

	@EJB(name = "gmh.EquipmentService")
	EiaServiceRemote remote;

	@PostConstruct
	public void inicializar() {
		userTestData();
		//eiaTypeTestData();
		//eiaTestData();
	}

	/**
	 * 
	 */
	private void eiaTestData() {
		
		Brand brand = new Brand();
		Manufacturer manufacturer = new Manufacturer();
		
		manufacturer.setName("Epson");
		em.persist(manufacturer);
		
		brand.setName("Stylus");
		em.persist(brand);
		
		EiaType eiaType = new EiaType();
		eiaType.setName("Impresora Epson multifuncional");
		eiaType.setModel("Stylus123");
		eiaType.setManufacturer(manufacturer);
		eiaType.setBrand(brand);
		eiaType.setCode("IMPHP9523");
		em.persist(eiaType);
		
		Eia eia = new Eia();
		eia.setEiatype(eiaType);
		eia.setCode("Impresora");
		em.persist(eia);
		
		Eia equipment2 = new Eia();
		equipment2.setEiatype(eiaType);
		equipment2.setCode("Impresora");
		em.persist(equipment2);
		
		List<Eia> equipments = remote.find(eiaType);
		if(equipments == null)System.out.println("NULL");
		else{for(Eia e : equipments){
			System.out.println(e.getCode());
		}}
	}

	private void eiaTypeTestData() {
		Manufacturer manufacturer = new Manufacturer();
		Brand brand = new Brand();
		
		manufacturer.setName("Epson");
		em.persist(manufacturer);

		try {
			manufacturer.setName("Epson");
			em.persist(manufacturer);

			brand.setName("Stylus");
			em.persist(brand);

			EiaType eiaType = new EiaType();
			eiaType.setName("Impresora Epson multifuncional");
			eiaType.setModel("Deskjet 9510");
			eiaType.setManufacturer(manufacturer);
			eiaType.setBrand(brand);
			eiaType.setCode("IMPHP9523");
			em.persist(eiaType);

		} catch (Exception e) {
			System.out.println("ERROR: No se puede cargar la data de prueba:"
					+ e.getMessage());
		}

		// search
//		EiaType entity = new EiaType();
		// entity.setCode("code");
		// entity.setModel("Deskjet");
		// entity.setName("Impresora HP");
//		entity.setManufacturer(manufacturer);
		// entity.setBrand(brand);
//		List<EiaType> result = eiaTypeService.find(entity, 0, 100);
//
//		if (result != null) {
//			System.out.println("Results " + Integer.toString(result.size()));
//			for (EiaType et : result) {
//				System.out.println(et.getName());
//			}
//		} else {
//			System.out.println("Error result=null");
//		}
	}

	private void userTestData() {
		try {
			String query = "SELECT u from User u WHERE u.username = 'admin'";
			try {
				em.createQuery(query).getSingleResult();
			} catch (NoResultException e) {
				User newUserAdmin = new User();
				newUserAdmin.setPass("admin");
				newUserAdmin.setUsername("admin");
				em.persist(newUserAdmin);
			}
		} catch (Exception e) {
			System.out.println("ERROR: No se puede cargar la data de prueba:"
					+ e.getMessage());
		}
	}
}