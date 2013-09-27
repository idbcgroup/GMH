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

import org.fourgeeks.gha.domain.exceptions.EJBException;
import org.fourgeeks.gha.domain.gmh.EiaTypeUtility;
import org.fourgeeks.gha.ejb.GhaServiceTest;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author vivi.torresg
 * 
 */
@RunWith(Arquillian.class)
public class EiaTypeUtilityServiceTest extends GhaServiceTest {
	@PersistenceContext
	EntityManager em;

	@EJB(name = "gmh.EiaTypeUtilityService")
	EiaTypeUtilityServiceRemote service;

	@Inject
	UserTransaction ux;

	@Test
	public void test() throws NotSupportedException, SystemException,
			SecurityException, IllegalStateException, RollbackException,
			HeuristicMixedException, HeuristicRollbackException, EJBException {
		Assert.assertNotNull(em);
		Assert.assertNotNull(service);

		ux.begin();
		em.joinTransaction();

		EiaTypeUtility entity = new EiaTypeUtility();
		entity.setEiaType(super.getEiaType(em));
		entity = service.save(entity);

		Assert.assertNotNull(entity);
		Assert.assertTrue(service.findByEiaType(entity.getEiaType()) != null
				&& service.findByEiaType(entity.getEiaType()).size() >= 1);
		Assert.assertTrue(service.findByEiaType(entity.getEiaType()).size() == 1);
		service.delete(entity.getId());
		Assert.assertTrue(service.findByEiaType(entity.getEiaType()).size() == 0);

		ux.commit();

	}
}
