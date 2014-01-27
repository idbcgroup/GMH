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
import org.fourgeeks.gha.domain.gmh.ServiceResource;
import org.fourgeeks.gha.ejb.GhaServiceTest;

/**
 * @author vivi.torresg
 * 
 */
// @RunWith(Arquillian.class)
public class ServiceResourceServiceTest extends GhaServiceTest {
	@PersistenceContext
	EntityManager em;

	@EJB(name = "gmh.ServiceResourceService")
	ServiceResourceServiceRemote service;

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

		ServiceResource entity = new ServiceResource();
		entity.setName("ServiceResorce test name");

		entity = service.save(entity);

		Assert.assertNotNull(service.find(entity.getId()));
		System.out.println("BEFORE " + entity.getId() + " " + entity.getName()
				+ "\nAFTER " + service.find(entity.getId()).getId() + " "
				+ service.find(entity.getId()).getName());
		// Assert.assertEquals(entity, service.find(entity.getId()));

		Assert.assertTrue(service.findByProtocolActivity(super
				.getMaintenanceActivityServiceResource(em,
						super.getMaintenanceActivity(em), entity)
				.getProtocolActivity()) != null
				&& service.findByProtocolActivity(
						super.getMaintenanceActivityServiceResource(em,
								super.getMaintenanceActivity(em), entity)
								.getProtocolActivity()).size() >= 1);
		em.remove(super.getMaintenanceActivityServiceResource(em,
				super.getMaintenanceActivity(em), entity));
		em.flush();
		Assert.assertTrue(service.findByProtocolActivity(super
				.getMaintenanceActivityServiceResource(em,
						super.getMaintenanceActivity(em), entity)
				.getProtocolActivity()) == null
				|| service.findByProtocolActivity(
						super.getMaintenanceActivityServiceResource(em,
								super.getMaintenanceActivity(em), entity)
								.getProtocolActivity()).size() == 0);

		Assert.assertTrue(service.getAll() != null
				&& service.getAll().size() >= 1);
		Assert.assertTrue(service.getAll(0, 10) != null
				&& service.getAll(0, 10).size() >= 1);

		entity.setName("ServiceResource test name updated");
		entity = service.update(entity);
		Assert.assertEquals("ServiceResource test name updated",
				service.find(entity.getId()).getName());
		Assert.assertFalse("ServiceResource test name" == service.find(
				entity.getId()).getName());
		long id = entity.getId();
		service.delete(entity.getId());
		Assert.assertNull(service.find(id));

		ux.commit();

	}
}
