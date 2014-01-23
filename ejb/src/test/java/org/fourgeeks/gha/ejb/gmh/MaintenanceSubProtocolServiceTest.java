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
import org.fourgeeks.gha.domain.gmh.MaintenanceSubProtocol;
import org.fourgeeks.gha.ejb.GhaServiceTest;

/**
 * @author vivi.torresg
 * 
 */
// @RunWith(Arquillian.class)
public class MaintenanceSubProtocolServiceTest extends GhaServiceTest {
	@PersistenceContext
	EntityManager em;

	@EJB(name = "gmh.MaintenanceSubProtocolService")
	MaintenanceSubProtocolServiceRemote service;

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

		MaintenanceSubProtocol entity = new MaintenanceSubProtocol();
		entity.setParentProtocolActivity(super.getMaintenanceActivity(em));
		entity.setMaintenanceActivity(super.getMaintenanceActivity(em));

		entity = service.save(entity);

		Assert.assertNotNull(service.find(entity.getId()));
		System.out.println("BEFORE " + entity.getId() + " "
				+ entity.getMaintenanceActivity() + "\nAFTER "
				+ service.find(entity.getId()).getId() + " "
				+ service.find(entity.getId()).getMaintenanceActivity());
		// Assert.assertEquals(entity, service.find(entity.getId()));

		Assert.assertTrue(service.findByMaintenanceActivity(super
				.getMaintenanceActivity(em)) != null
				&& service.findByMaintenanceActivity(
						super.getMaintenanceActivity(em)).size() >= 1);
		Assert.assertTrue(service.getAll() != null
				&& service.getAll().size() >= 1);
		Assert.assertTrue(service.getAll(0, 10) != null
				&& service.getAll(0, 10).size() >= 1);

		Assert.assertNotNull(service.find(entity.getId())
				.getMaintenanceActivity());
		entity.setMaintenanceActivity(null);
		entity = service.update(entity);
		Assert.assertEquals(null, service.find(entity.getId())
				.getMaintenanceActivity());
		long id = entity.getId();
		service.delete(entity.getId());
		Assert.assertNull(service.find(id));

		ux.commit();

	}
}
