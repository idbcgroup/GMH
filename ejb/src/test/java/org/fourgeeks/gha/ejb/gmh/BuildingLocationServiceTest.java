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

import org.fourgeeks.gha.domain.enu.LocationLevelEnum;
import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.gar.BuildingLocation;
import org.fourgeeks.gha.ejb.GhaServiceTest;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author vivi.torresg
 * 
 */
@RunWith(Arquillian.class)
public class BuildingLocationServiceTest extends GhaServiceTest {
	@PersistenceContext
	EntityManager em;

	@EJB(name = "gmh.BuildingLocationService")
	BuildingLocationServiceRemote service;

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

		BuildingLocation entity = new BuildingLocation();
		entity.setCode("BuildingLocation test code");
		entity.setLocationLevel(LocationLevelEnum.AREA_HALL);
		entity.setBpi(super.getBpi(em));
		entity = service.save(entity);

		Assert.assertNotNull(entity);
		Assert.assertEquals(1, service.find(entity).size());
		System.out.println("BEFORE " + entity.getCode() + " "
				+ entity.getName() + "\nAFTER "
				+ service.find(entity.getCode()).getCode() + " "
				+ service.find(entity.getCode()).getName());
		// Assert.assertEquals(entity, service.find(entity.getCode()));
		Assert.assertTrue(service.getAll() != null
				&& service.getAll().size() >= 1);
		entity.setCode("BuildingLocation test code updated");
		entity = service.update(entity);
		Assert.assertEquals("BuildingLocation test code updated",
				service.find(entity.getCode()).getCode());
		String code = entity.getCode();
		service.delete(entity.getCode());
		Assert.assertNull(service.find(code));

		ux.commit();

	}
}
