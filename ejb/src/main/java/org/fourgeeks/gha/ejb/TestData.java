package org.fourgeeks.gha.ejb;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.fourgeeks.gha.domain.gmh.EiaType;
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
		userTestData();
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
		try {
			EiaType eiaType = new EiaType();
			eiaType.setName("Impresora HP multifuncional");
			eiaType.setModel("Deskjet 9510");
			eiaType.setManufacturer("HP");
			eiaType.setCode("IMPHP9523");
			em.persist(eiaType);
		} catch (Exception e) {
			System.out.println("ERROR: No se puede cargar la data de prueba:"
					+ e.getMessage());
		}
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