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

import org.fourgeeks.gha.domain.enu.TimePeriodEnum;
import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.gmh.MaintenancePlan;
import org.fourgeeks.gha.ejb.GhaServiceTest;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author vivi.torresg
 * 
 */
@RunWith(Arquillian.class)
public class MaintenancePlanServiceTest extends GhaServiceTest {
	@PersistenceContext
	EntityManager em;

	@EJB(name = "gmh.MaintenancePlanService")
	MaintenancePlanServiceRemote service;

	@Inject
	UserTransaction ux;

	@Test
	public void test() throws NotSupportedException, SystemException,
			SecurityException, IllegalStateException, RollbackException,
			HeuristicMixedException, HeuristicRollbackException,
			GHAEJBException {
		Assert.assertNotNull(em);
		Assert.assertNotNull(service);

		ux.begin();
		em.joinTransaction();

		MaintenancePlan entity = new MaintenancePlan();
		entity.setDescription("MaintenancePlan test description");
		entity.setName("MaintenancePlan teste name");
		entity.setPot(TimePeriodEnum.DAYS);

		entity = service.save(entity);

		Assert.assertNotNull(service.find(entity.getId()));
		System.out.println("BEFORE " + entity.getId() + " "
				+ entity.getDescription() + "\nAFTER "
				+ service.find(entity.getId()).getId() + " "
				+ service.find(entity.getId()).getDescription());
		// Assert.assertEquals(entity, service.find(entity.getId()));

		Assert.assertTrue(service.findByEiaType(super
				.getEiaTypeMaintenancePlan(em, super.getEiaType(em), entity)
				.getEiaType()) != null
				&& service.findByEiaType(
						super.getEiaTypeMaintenancePlan(em,
								super.getEiaType(em), entity).getEiaType())
						.size() >= 1);
		Assert.assertTrue(service.findByEiaType(super
				.getEiaTypeMaintenancePlan(em, super.getEiaType(em), entity)
				.getEiaType(), 0, 10) != null
				&& service.findByEiaType(
						super.getEiaTypeMaintenancePlan(em,
								super.getEiaType(em), entity).getEiaType(), 0,
						10).size() >= 1);

		em.remove(super.getEiaTypeMaintenancePlan(em, super.getEiaType(em),
				entity));
		em.flush();
		Assert.assertTrue(service.findByEiaType(super
				.getEiaTypeMaintenancePlan(em, super.getEiaType(em), entity)
				.getEiaType()) == null
				|| service.findByEiaType(
						super.getEiaTypeMaintenancePlan(em,
								super.getEiaType(em), entity).getEiaType())
						.size() == 0);

		Assert.assertTrue(service.getAll() != null
				&& service.getAll().size() >= 1);
		Assert.assertTrue(service.getAll(0, 10) != null
				&& service.getAll(0, 10).size() >= 1);
		entity.setDescription("MaintenancePlan test description updated");
		entity = service.update(entity);
		Assert.assertEquals("MaintenancePlan test description updated", service
				.find(entity.getId()).getDescription());
		Assert.assertFalse("MaintenancePlan test description" == service.find(
				entity.getId()).getDescription());
		long id = entity.getId();
		service.delete(entity.getId());
		Assert.assertNull(service.find(id));

		ux.commit();

	}
}
