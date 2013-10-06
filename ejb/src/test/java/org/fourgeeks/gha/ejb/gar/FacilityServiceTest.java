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
import org.fourgeeks.gha.domain.gar.Facility;
import org.fourgeeks.gha.ejb.GhaServiceTest;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author vivi.torresg
 * 
 */
@RunWith(Arquillian.class)
public class FacilityServiceTest extends GhaServiceTest {
	@PersistenceContext
	EntityManager em;

	@EJB(name = "gar.FacilityService")
	FacilityServiceRemote service;

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

		Facility entity = new Facility();
		entity.setBuildingLocation(super.getBuildingLocation(em));
		entity = service.save(entity);

		Assert.assertNotNull(entity);
		Assert.assertEquals(1, service.find(entity).size());
		System.out.println("BEFORE " + entity.getId() + " " + entity.getName()
				+ "\nAFTER " + service.find(entity.getId()).getId() + " "
				+ service.find(entity.getId()).getName());
		// Assert.assertEquals(entity, service.find(entity.getId()));
		Assert.assertTrue(service.getAll() != null
				&& service.getAll().size() >= 1);
		entity.setName("Facility test name");
		entity = service.update(entity);
		Assert.assertEquals("Facility test name", service.find(entity.getId())
				.getName());
		long id = entity.getId();
		service.delete(entity.getId());
		Assert.assertNull(service.find(id));

		ux.commit();

	}
}
