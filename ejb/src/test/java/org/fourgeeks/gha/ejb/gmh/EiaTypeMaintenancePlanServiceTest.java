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
import org.fourgeeks.gha.domain.gmh.EiaTypeMaintenancePlan;
import org.fourgeeks.gha.ejb.GHAArquillianBaseServiceTest;

/**
 * @author vivi.torresg
 * 
 */
// @RunWith(Arquillian.class)
public class EiaTypeMaintenancePlanServiceTest extends
		GHAArquillianBaseServiceTest {
	@PersistenceContext
	EntityManager em;

	@EJB(name = "eiaTypeMaintenancePlanService")
	EiaTypeMaintenancePlanServiceRemote service;

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

		EiaTypeMaintenancePlan entity = new EiaTypeMaintenancePlan();
		// entity.setEiaType(super.getEiaType(em));
		// entity.setMaintenancePlan(super.getMaintenancePlan(em));

		entity = service.save(entity);

		Assert.assertTrue(service.findByEiaType(entity.getEiaType()) != null
				&& service.findByEiaType(entity.getEiaType()).size() >= 1);
		Assert.assertTrue(service.findByMaintenancePlan(entity
				.getMaintenancePlan()) != null
				&& service.findByMaintenancePlan(entity.getMaintenancePlan())
						.size() >= 1);
		Assert.assertTrue(service.findByEiaType(entity.getEiaType()).size() == 1);
		service.delete(entity.getId());
		Assert.assertTrue(service.findByEiaType(entity.getEiaType()).size() == 0);

		ux.commit();

	}
}
