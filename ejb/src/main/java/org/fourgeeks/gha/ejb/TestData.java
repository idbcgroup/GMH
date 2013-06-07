package org.fourgeeks.gha.ejb;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.fourgeeks.gha.domain.gmh.Brand;
import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.domain.gmh.Manufacturer;
import org.fourgeeks.gha.domain.mix.User;
import org.fourgeeks.gha.ejb.gmh.EiaTypeServiceRemote;

@Startup
@Singleton
public class TestData {

	@PersistenceContext
	EntityManager em;

	@EJB(name = "gmh.EiaTypeService")
	EiaTypeServiceRemote eiaTypeService;

	@PostConstruct
	public void inicializar() {
//		userTestData();
		eiaTypeTestData();

		// EiaType entity = new EiaType();
		// entity.setName("testName");
		// entity.setBrand("brand");
		// entity.setCode("code");
		// entity.setModel("model");
		// entity.setManufacturer("manufacturer");
		//
		// eiaTypeService.save(entity);
		//
		// entity = eiaTypeService.find(3);
		// entity.setName("newName");
		// eiaTypeService.update(entity);
		//
		// List <EiaType> entities = eiaTypeService.getAll();
		//
		// for(EiaType next : entities){
		// System.out.println(next.getName());
		// }
		//
		// entities = eiaTypeService.getAll(2, 3);
		//
		// for(EiaType next : entities){
		// System.out.println(next.getName());
		// }

	}

	private void eiaTypeTestData() {
		Manufacturer manufacturer = new Manufacturer();
		Brand brand = new Brand();

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