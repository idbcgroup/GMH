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
import org.fourgeeks.gha.domain.gmh.MaintenanceProtocol;
import org.fourgeeks.gha.ejb.GhaServiceTest;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author vivi.torresg
 * 
 */
@RunWith(Arquillian.class)
public class MaintenanceProtocolServiceTest extends GhaServiceTest {
	@PersistenceContext
	EntityManager em;

	@EJB(name = "gmh.MaintenanceProtocolService")
	MaintenanceProtocolServiceRemote service;

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

		MaintenanceProtocol entity = new MaintenanceProtocol();
		entity.setDescription("MaintenanceProtocol test description");
		entity.setMaintenancePlan(super.getMaintenancePlan(em));

		entity = service.save(entity);

		Assert.assertNotNull(service.find(entity.getId()));
		System.out.println("BEFORE " + entity.getId() + " "
				+ entity.getDescription() + "\nAFTER "
				+ service.find(entity.getId()).getId() + " "
				+ service.find(entity.getId()).getDescription());
		// Assert.assertEquals(entity, service.find(entity.getId()));

		Assert.assertTrue(service.findByEiaTypeMaintenancePlan(super
				.getMaintenancePlan(em)) != null
				&& service.findByEiaTypeMaintenancePlan(
						super.getMaintenancePlan(em)).size() >= 1);
		Assert.assertTrue(service.findByEiaTypeMaintenancePlan(
				super.getMaintenancePlan(em), 0, 10) != null
				&& service.findByEiaTypeMaintenancePlan(
						super.getMaintenancePlan(em), 0, 10).size() >= 1);

		Assert.assertTrue(service.getAll() != null
				&& service.getAll().size() >= 1);
		Assert.assertTrue(service.getAll(0, 10) != null
				&& service.getAll(0, 10).size() >= 1);
		entity.setDescription("MaintenanceProtocol test description updated");
		entity = service.update(entity);
		Assert.assertEquals("MaintenanceProtocol test description updated",
				service.find(entity.getId()).getDescription());
		Assert.assertFalse("MaintenanceProtocol test description" == service
				.find(entity.getId()).getDescription());
		long id = entity.getId();
		service.delete(entity.getId());
		Assert.assertNull(service.find(id));

		ux.commit();

	}
}
