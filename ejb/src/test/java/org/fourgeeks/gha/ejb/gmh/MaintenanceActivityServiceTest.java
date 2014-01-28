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

import org.fourgeeks.gha.domain.Activity;
import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.gmh.MaintenanceActivity;
import org.fourgeeks.gha.ejb.GhaServiceTest;

/**
 * @author vivi.torresg
 * 
 */
// @RunWith(Arquillian.class)
public class MaintenanceActivityServiceTest extends GhaServiceTest {
	@PersistenceContext
	EntityManager em;

	@EJB(name = "gmh.MaintenanceActivityService")
	MaintenanceActivityServiceRemote service;

	@Inject
	UserTransaction ux;

	// @Test
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
	public void test() throws NotSupportedException, SystemException,
			SecurityException, IllegalStateException, RollbackException,
			HeuristicMixedException, HeuristicRollbackException,
			GHAEJBException {
		Assert.assertNotNull(em);
		Assert.assertNotNull(service);

		ux.begin();
		em.joinTransaction();

		MaintenanceActivity entity = new MaintenanceActivity();
		Activity activity = new Activity();
		activity.setName("MaintenanceActivity test name");
		activity.setDescription("MaintenanceActivity test description");
		entity.setActivity(activity);

		entity = service.save(entity);

		Assert.assertNotNull(service.find(entity.getId()));
		System.out.println("BEFORE " + entity.getId() + " "
				+ activity.getDescription() + "\nAFTER "
				+ service.find(entity.getId()).getId() + " "
				+ service.find(entity.getId()).getActivity().getDescription());
		// Assert.assertEquals(entity, service.find(entity.getId()));

		Assert.assertTrue(service.findByServiceResource(super
				.getMaintenanceActivityServiceResource(em, entity,
						super.getServiceResource(em)).getServiceResource()) != null
				&& service.findByServiceResource(
						super.getMaintenanceActivityServiceResource(em, entity,
								super.getServiceResource(em))
								.getServiceResource()).size() >= 1);

		em.remove(super.getMaintenanceActivityServiceResource(em, entity,
				super.getServiceResource(em)));
		em.flush();
		Assert.assertTrue(service.findByServiceResource(super
				.getMaintenanceActivityServiceResource(em, entity,
						super.getServiceResource(em)).getServiceResource()) == null
				|| service.findByServiceResource(
						super.getMaintenanceActivityServiceResource(em, entity,
								super.getServiceResource(em))
								.getServiceResource()).size() == 0);

		Assert.assertTrue(service.getAll() != null
				&& service.getAll().size() >= 1);
		Assert.assertTrue(service.getAll(0, 10) != null
				&& service.getAll(0, 10).size() >= 1);
		activity = entity.getActivity();
		activity.setDescription("MaintenanceActivity test description updated");
		entity = service.update(entity);
		Assert.assertEquals("MaintenanceActivity test description updated",
				service.find(entity.getId()).getActivity().getDescription());
		Assert.assertFalse("MaintenanceActivity test description" == service
				.find(entity.getId()).getActivity().getDescription());
		long id = entity.getId();
		service.delete(entity.getId());
		Assert.assertNull(service.find(id));

		ux.commit();

	}
}
