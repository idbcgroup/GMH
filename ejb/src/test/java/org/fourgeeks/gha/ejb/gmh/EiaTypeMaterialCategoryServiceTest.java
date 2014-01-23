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
import org.fourgeeks.gha.domain.gmh.EiaTypeMaterialCategory;
import org.fourgeeks.gha.ejb.GhaServiceTest;

/**
 * @author vivi.torresg
 * 
 */
// @RunWith(Arquillian.class)
public class EiaTypeMaterialCategoryServiceTest extends GhaServiceTest {
	@PersistenceContext
	EntityManager em;

	@EJB(name = "gmh.EiaTypeMaterialService")
	EiaTypeMaterialCategoryServiceRemote service;

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

		EiaTypeMaterialCategory entity = new EiaTypeMaterialCategory();
		entity.setEiaType(super.getEiaType(em));

		entity = service.save(entity);

		Assert.assertTrue(service.findByEiaType(entity.getEiaType()) != null
				&& service.findByEiaType(entity.getEiaType()).size() >= 1);
		Assert.assertTrue(service.findByEiaType(entity.getEiaType()).size() == 1);
		service.delete(entity.getId());
		Assert.assertTrue(service.findByEiaType(entity.getEiaType()).size() == 0);

		ux.commit();

	}
}
