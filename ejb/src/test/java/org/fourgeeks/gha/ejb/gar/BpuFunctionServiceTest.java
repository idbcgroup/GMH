package org.fourgeeks.gha.ejb.gar;

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
import org.fourgeeks.gha.ejb.GhaServiceTest;
import org.fourgeeks.gha.ejb.ess.AppFormViewFunctionBpuServiceRemote;

/**
 * @author alacret, vivi.torresg
 * 
 */
// @RunWith(Arquillian.class)
public class BpuFunctionServiceTest extends GhaServiceTest {

	@PersistenceContext
	EntityManager em;

	@EJB(name = "gar.BpuFunctionService")
	AppFormViewFunctionBpuServiceRemote service;

	@Inject
	UserTransaction ux;

	/**
	 * @throws NotSupportedException
	 * @throws SystemException
	 * @throws SecurityException
	 * @throws IllegalStateException
	 * @throws RollbackException
	 * @throws HeuristicMixedException
	 * @throws HeuristicRollbackException
	 * @throws GHAEJBException
	 */
	// @Test
	public void test() throws NotSupportedException, SystemException,
			SecurityException, IllegalStateException, RollbackException,
			HeuristicMixedException, HeuristicRollbackException,
			GHAEJBException {
		Assert.assertNotNull(em);
		Assert.assertNotNull(service);

		ux.begin();
		em.joinTransaction();

		Assert.assertNotNull(service.getFunctionsByBpu(super.getBpu(em)));

		ux.commit();
	}

}
