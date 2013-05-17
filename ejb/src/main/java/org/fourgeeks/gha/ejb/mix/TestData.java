package org.fourgeeks.gha.ejb.mix;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.fourgeeks.gha.domain.vcf.ItSystem;

@Startup
@Singleton
public class TestData {

	@PersistenceContext
	EntityManager em;

	@PostConstruct
	public void inicializar() {
		try {

			ItSystem itSystem = new ItSystem();
			itSystem.setName("test");
			em.persist(itSystem);
			em.close();

		} catch (Exception e) {
		}
	}
}