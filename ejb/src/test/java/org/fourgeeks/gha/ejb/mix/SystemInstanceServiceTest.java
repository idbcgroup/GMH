package org.fourgeeks.gha.ejb.mix;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import junit.framework.Assert;

import org.fourgeeks.gha.domain.ess.auth.SystemInstance;
import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.mix.Institution;
import org.fourgeeks.gha.ejb.GHAArquillianBaseServiceTest;

/**
 * @author vivi.torresg
 * 
 */
// @RunWith(Arquillian.class)
public class SystemInstanceServiceTest extends GHAArquillianBaseServiceTest {
	@PersistenceContext
	EntityManager em;

	@EJB(name = "mix.SystemInstanceService")
	SystemInstanceServiceRemote service;

	@Inject
	UserTransaction ux;

	// @Test
	public void test() throws NotSupportedException, SystemException,
			SecurityException, IllegalStateException, RollbackException,
			HeuristicMixedException, HeuristicRollbackException,
			GHAEJBException {
		Assert.assertNotNull(em);
		Assert.assertNotNull(service);

		ux.begin();
		em.joinTransaction();

		SystemInstance entity = new SystemInstance();
		// entity.setInstitution(super.getInstitution(em));

		entity = service.save(entity);

		Assert.assertNotNull(service.find(entity.getId()));
		System.out.println("BEFORE " + entity.getId() + " "
				+ entity.getInstitution().getName() + "\nAFTER "
				+ service.find(entity.getId()).getId() + " "
				+ service.find(entity.getId()).getInstitution().getName());
		// Assert.assertEquals(entity, service.find(entity.getId()));

		Assert.assertTrue(service.find(entity) != null
				&& service.find(entity).size() >= 1);
		Assert.assertTrue(service.getAll() != null
				&& service.getAll().size() >= 1);

		Institution oldInstitution = entity.getInstitution();
		Institution institution = new Institution();
		institution.setName("Institution name test updated");
		// institution.setLegalEntity(getLegalEntity(em));
		em.persist(institution);
		em.flush();
		institution = em.find(Institution.class, institution.getId());
		entity.setInstitution(institution);
		entity = service.update(entity);
		Assert.assertNotSame(entity.getInstitution(), oldInstitution);
		Assert.assertEquals(institution.getId(), service.find(entity.getId())
				.getInstitution().getId());
		long id = entity.getId();
		service.delete(entity.getId());
		Assert.assertNull(service.find(id));

		ux.commit();

	}
}
