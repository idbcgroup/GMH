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

import org.fourgeeks.gha.domain.enu.GenderTypeEnum;
import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.mix.Citizen;
import org.fourgeeks.gha.ejb.GhaServiceTest;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author vivi.torresg
 * 
 */
@RunWith(Arquillian.class)
public class CitizenServiceTest extends GhaServiceTest {
	@PersistenceContext
	EntityManager em;

	@EJB(name = "mix.CitizenService")
	CitizenServiceRemote service;

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

		Citizen entity = new Citizen();
		entity.setLegalEntity(super.getLegalEntity(em));
		entity.setGender(GenderTypeEnum.FEMALE);

		entity = service.save(entity);

		Assert.assertNotNull(service.find(entity.getId()));
		System.out.println("BEFORE " + entity.getId() + " "
				+ entity.getGender() + "\nAFTER "
				+ service.find(entity.getId()).getId() + " "
				+ service.find(entity.getId()).getGender());
		// Assert.assertEquals(entity, service.find(entity.getId()));

		Assert.assertTrue(service.find(entity) != null
				&& service.find(entity).size() >= 1);
		Assert.assertTrue(service.getAll() != null
				&& service.getAll().size() >= 1);
		Assert.assertEquals(service.find(entity.getId()).getGender(),
				GenderTypeEnum.FEMALE);
		entity.setGender(GenderTypeEnum.MALE);
		entity = service.update(entity);
		Assert.assertEquals(service.find(entity.getId()).getGender(),
				GenderTypeEnum.MALE);

		long id = entity.getId();
		service.delete(entity.getId());
		Assert.assertNull(service.find(id));

		ux.commit();

	}
}
