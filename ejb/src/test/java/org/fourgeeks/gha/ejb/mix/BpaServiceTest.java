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

import org.fourgeeks.gha.domain.enu.CredentialTypeEnum;
import org.fourgeeks.gha.domain.exceptions.EJBException;
import org.fourgeeks.gha.domain.mix.Bpa;
import org.fourgeeks.gha.ejb.GhaServiceTest;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author vivi.torresg
 * 
 */
@RunWith(Arquillian.class)
public class BpaServiceTest extends GhaServiceTest {
	@PersistenceContext
	EntityManager em;

	@EJB(name = "mix.BpaService")
	BpaServiceRemote service;

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

		Bpa entity = new Bpa();
		entity.setCredentialType(CredentialTypeEnum.ADMISSION_AGENT);

		entity = service.save(entity);

		Assert.assertNotNull(service.find(entity.getId()));
		System.out.println("BEFORE " + entity.getId() + " "
				+ entity.getCredentialType() + "\nAFTER "
				+ service.find(entity.getId()).getId() + " "
				+ service.find(entity.getId()).getCredentialType());
		// Assert.assertEquals(entity, service.find(entity.getId()));

		// Assert.assertTrue(service.find(entity) != null
		// && service.find(entity).size() >= 1);
		Assert.assertTrue(service.getAll() != null
				&& service.getAll().size() >= 1);

		entity.setCredentialType(CredentialTypeEnum.NURSE);
		entity = service.update(entity);
		Assert.assertEquals(CredentialTypeEnum.NURSE,
				service.find(entity.getId()).getCredentialType());
		Assert.assertFalse(CredentialTypeEnum.ADMISSION_AGENT == service.find(
				entity.getId()).getCredentialType());
		long id = entity.getId();
		service.delete(entity.getId());
		Assert.assertNull(service.find(id));

		ux.commit();

	}
}
