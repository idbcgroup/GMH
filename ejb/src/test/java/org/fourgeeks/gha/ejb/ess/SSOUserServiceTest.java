package org.fourgeeks.gha.ejb.ess;

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

import org.fourgeeks.gha.domain.enu.UserLogonStatusEnum;
import org.fourgeeks.gha.domain.ess.SSOUser;
import org.fourgeeks.gha.domain.exceptions.EJBException;
import org.fourgeeks.gha.ejb.GhaServiceTest;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author vivi.torresg
 * 
 */
@RunWith(Arquillian.class)
public class SSOUserServiceTest extends GhaServiceTest {
	@PersistenceContext
	EntityManager em;

	@EJB(name = "ess.SSOUserService")
	SSOUserServiceRemote service;

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

		SSOUser entity = new SSOUser();
		entity.setUserName("vivivivi");
		entity.setPassword("vivi12345");
		entity.setUserLogonStatus(UserLogonStatusEnum.STAYIN);
		entity.setBpu(super.getBpu(em));
		entity = service.save(entity);

		Assert.assertNotNull(entity);
		Assert.assertEquals(1, service.find(entity).size());
		System.out.println("BEFORE " + entity.getId() + " "
				+ entity.getUserName() + "\nAFTER "
				+ service.find(entity.getId()).getId() + " "
				+ service.find(entity.getId()).getUserName());
		// Assert.assertEquals(entity, service.find(entity.getId()));
		Assert.assertEquals(entity.getId(),
				service.findByUsername(entity.getUserName()).getId());
		Assert.assertTrue(service.getAll() != null
				&& service.getAll().size() >= 1);
		entity.setPassword("12345vivi");
		entity = service.update(entity);
		Assert.assertEquals("12345vivi", service.find(entity.getId())
				.getPassword());

		long id = entity.getId();
		service.delete(entity.getId());
		Assert.assertNull(service.find(id));

		ux.commit();

	}
}
