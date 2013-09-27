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
import org.fourgeeks.gha.domain.exceptions.EJBException;
import org.fourgeeks.gha.domain.gmh.EiaPicture;
import org.fourgeeks.gha.ejb.GhaServiceTest;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author vivi.torresg
 * 
 */
@RunWith(Arquillian.class)
public class EiaPictureServiceTest extends GhaServiceTest {
	@PersistenceContext
	EntityManager em;

	@EJB(name = "gmh.EiaPictureService")
	EiaPictureServiceRemote service;

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

		EiaPicture entity = new EiaPicture();
		entity.setDescription("EiaPicture test description");
		entity.setDate(super.getDate());
		entity.setPicture("EiaPicture test picture");
		entity.setPictureState(EiaPictureStateEnum.ACTIVE);
		entity.setEia(super.getEia(em));

		entity = service.save(entity);

		Assert.assertNotNull(service.find(entity.getId()));
		System.out.println("BEFORE " + entity.getId() + " "
				+ entity.getDescription() + "\nAFTER "
				+ service.find(entity.getId()).getId() + " "
				+ service.find(entity.getId()).getDescription());
		//Assert.assertEquals(entity, service.find(entity.getId()));
		Assert.assertTrue(service.find(entity.getEia()) != null
				&& service.find(entity.getEia()).size() >= 1);
		entity.setDescription("EiaPicture test description updated");
		Assert.assertTrue(service.update(entity));
		Assert.assertEquals("EiaPicture test description updated", service
				.find(entity.getId()).getDescription());
		Assert.assertFalse("EiaPicture test description" == service.find(
				entity.getId()).getDescription());
		long id = entity.getId();
		service.delete(entity.getId());
		Assert.assertNull(service.find(id));

		ux.commit();

	}
}
