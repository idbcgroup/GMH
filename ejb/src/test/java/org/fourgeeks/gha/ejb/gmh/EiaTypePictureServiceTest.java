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

import org.fourgeeks.gha.domain.enu.EiaPictureStateEnum;
import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.gmh.EiaTypePicture;
import org.fourgeeks.gha.ejb.GhaServiceTest;

/**
 * @author vivi.torresg
 * 
 */
// @RunWith(Arquillian.class)
public class EiaTypePictureServiceTest extends GhaServiceTest {
	@PersistenceContext
	EntityManager em;

	@EJB(name = "gmh.EiaTypePictureService")
	EiaTypePictureServiceRemote service;

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

		EiaTypePicture entity = new EiaTypePicture();
		entity.setEiaType(super.getEiaType(em));
		entity.setDescription("EiaTypePicture test description");
		entity.setDate(super.getDate());
		entity.setPicture("EiaTypePicture test picture");
		entity.setPictureState(EiaPictureStateEnum.ACTIVE);
		entity = service.save(entity);

		Assert.assertNotNull(entity);
		System.out.println("BEFORE " + entity.getId() + " "
				+ entity.getDescription() + "\nAFTER "
				+ service.find(entity.getId()).getId() + " "
				+ service.find(entity.getId()).getDescription());
		// Assert.assertEquals(entity, service.find(entity.getId()));
		Assert.assertTrue(service.find(entity.getEiaType()) != null
				&& service.find(entity.getEiaType()).size() >= 1);
		entity.setDescription("EiaTypePicture test description updated");
		Assert.assertTrue(service.update(entity));
		Assert.assertEquals("EiaTypePicture test description updated", service
				.find(entity.getId()).getDescription());
		Assert.assertFalse("EiaTypePicture test description" == service.find(
				entity.getId()).getDescription());
		long id = entity.getId();
		service.delete(entity.getId());
		Assert.assertNull(service.find(id));

		ux.commit();

	}
}
