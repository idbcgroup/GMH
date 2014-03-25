package org.fourgeeks.gha.ejb.gmh;

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
import org.fourgeeks.gha.domain.gmh.EiaComponent;
import org.fourgeeks.gha.ejb.GHAArquillianBaseServiceTest;

/**
 * @author vivi.torresg
 * 
 */
// @RunWith(Arquillian.class)
public class EiaComponentServiceTest extends GHAArquillianBaseServiceTest {
	@PersistenceContext
	EntityManager em;

	@EJB(name = "gmh.EiaComponentService")
	EiaComponentServiceRemote service;

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

		EiaComponent entity = new EiaComponent();
		// entity.setEia(super.getEia(em));
		// entity.setParentEia(super.getEia(em));
		entity.setComponentObs("EiaComponent test componentObs");
		entity = service.save(entity);

		Assert.assertNotNull(entity);
		System.out.println("BEFORE " + entity.getId() + " "
				+ entity.getComponentObs() + "\nAFTER "
				+ service.find(entity.getId()).getId() + " "
				+ service.find(entity.getId()).getComponentObs());
		// Assert.assertEquals(entity, service.find(entity.getId()));
		Assert.assertTrue(service.findByParentEia(entity.getParentEia()) != null
				&& service.findByParentEia(entity.getParentEia()).size() >= 1);
		Assert.assertTrue(service.getAll() != null
				&& service.getAll().size() >= 1);
		Assert.assertTrue(service.getAll(0, 10) != null
				&& service.getAll(0, 10).size() >= 1);
		entity.setComponentObs("EiaComponent test componentObs updated");
		entity = service.update(entity);
		Assert.assertEquals("EiaComponent test componentObs updated", service
				.find(entity.getId()).getComponentObs());
		long id = entity.getId();
		service.delete(entity.getId());
		Assert.assertNull(service.find(id));

		ux.commit();

	}
}
