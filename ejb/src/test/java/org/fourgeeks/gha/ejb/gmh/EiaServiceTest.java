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
import org.fourgeeks.gha.domain.gmh.Eia;
import org.fourgeeks.gha.ejb.GhaServiceTest;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author vivi.torresg
 * 
 */
@RunWith(Arquillian.class)
public class EiaServiceTest extends GhaServiceTest {
	@PersistenceContext
	EntityManager em;

	@EJB(name = "gmh.EiaService")
	EiaServiceRemote service;

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

		Eia entity = new Eia();
		entity.setCode("Eia test code");
		entity.setEiaType(super.getEiaType(em));
		entity.setObu(super.getObu(em));
		entity.setProvider(super.getExternalProvider(em));
		entity.setResponsibleRole(super.getRole(em));

		entity = service.save(entity);

		Assert.assertNotNull(service.find(entity.getId()));
		System.out.println("BEFORE " + entity.getId() + " " + entity.getCode()
				+ "\nAFTER " + service.find(entity.getId()).getId() + " "
				+ service.find(entity.getId()).getCode());
		// Assert.assertEquals(entity, service.find(entity.getId()));
		Assert.assertTrue(service.find(entity) != null
				&& service.find(entity).size() >= 1);
		Assert.assertTrue(service.findByEiaType(entity.getEiaType()) != null
				&& service.findByEiaType(entity.getEiaType()).size() >= 1);
		Assert.assertTrue(service.getAll() != null
				&& service.getAll().size() >= 1);
		Assert.assertTrue(service.getAll(0, 10) != null
				&& service.getAll(0, 10).size() >= 1);
		entity.setCode("Eia test code updated");
		entity = service.update(entity);
		Assert.assertEquals("Eia test code updated",
				service.find(entity.getId()).getCode());
		Assert.assertFalse("Eia test code" == service.find(entity.getId())
				.getCode());
		long id = entity.getId();
		service.delete(entity.getId());
		Assert.assertNull(service.find(id));

		ux.commit();

	}
}
