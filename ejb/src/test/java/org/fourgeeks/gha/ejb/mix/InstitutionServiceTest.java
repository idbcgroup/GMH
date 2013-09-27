package org.fourgeeks.gha.ejb.mix;

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
import org.fourgeeks.gha.domain.mix.Institution;
import org.fourgeeks.gha.ejb.GhaServiceTest;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author vivi.torresg
 * 
 */
@RunWith(Arquillian.class)
public class InstitutionServiceTest extends GhaServiceTest {
	@PersistenceContext
	EntityManager em;

	@EJB(name = "mix.InstitutionService")
	InstitutionServiceRemote service;

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

		Institution entity = new Institution();
		entity.setName("Institution name test");
		entity.setLegalEntity(super.getLegalEntity(em));

		entity = service.save(entity);

		Assert.assertNotNull(service.find(entity.getId()));
		System.out.println("BEFORE " + entity.getId() + " " + entity.getName()
				+ "\nAFTER " + service.find(entity.getId()).getId() + " "
				+ service.find(entity.getId()).getName());
		// Assert.assertEquals(entity, service.find(entity.getId()));

		// Assert.assertTrue(service.find(entity) != null
		// && service.find(entity).size() >= 1);
		Assert.assertTrue(service.getAll() != null
				&& service.getAll().size() >= 1);
		Assert.assertEquals(service.find(entity.getId()).getName(),
				"Institution name test");
		entity.setName("Institution name test updated");
		entity = service.update(entity);
		Assert.assertEquals(service.find(entity.getId()).getName(),
				"Institution name test updated");

		long id = entity.getId();
		service.delete(entity.getId());
		Assert.assertNull(service.find(id));

		ux.commit();

	}
}
