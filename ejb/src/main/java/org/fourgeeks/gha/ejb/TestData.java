package org.fourgeeks.gha.ejb;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.fourgeeks.gha.domain.mix.User;

@Startup
@Singleton
public class TestData {

	@PersistenceContext
	EntityManager em;

	@PostConstruct
	public void inicializar() {
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