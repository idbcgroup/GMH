package org.fourgeeks.gha.ejb.glm;

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

import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.glm.ExternalProvider;
import org.fourgeeks.gha.ejb.GHAArquillianBaseServiceTest;

/**
 * @author vivi.torresg
 * 
 */
// @RunWith(Arquillian.class)
public class ExternarProviderServiceTest extends GHAArquillianBaseServiceTest {
	@PersistenceContext
	EntityManager em;

	@EJB(name = "glm.ExternalProviderService")
	ExternalProviderServiceRemote service;

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

		ExternalProvider entity = new ExternalProvider();
		// entity.setInstitution(super.getInstitution(em));
		entity = service.save(entity);

		Assert.assertNotNull(entity);
		Assert.assertEquals(1, service.find(entity).size());
		System.out.println("BEFORE " + entity.getId() + " " + entity.getCode()
				+ "\nAFTER " + service.find(entity.getId()).getId() + " "
				+ service.find(entity.getId()).getCode());
		// Assert.assertEquals(entity, service.find(entity.getId()));
		Assert.assertTrue(service.getAll() != null
				&& service.getAll().size() >= 1);
		entity.setCode("ExternalProvider test code updated");
		entity = service.update(entity);
		Assert.assertEquals("ExternalProvider test code updated",
				service.find(entity.getId()).getCode());
		long id = entity.getId();
		service.delete(entity.getId());
		Assert.assertNull(service.find(id));

		ux.commit();

	}
}
