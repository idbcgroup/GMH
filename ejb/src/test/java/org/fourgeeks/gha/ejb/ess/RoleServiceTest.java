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

import org.fourgeeks.gha.domain.ess.Role;
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
public class RoleServiceTest extends GhaServiceTest {
	@PersistenceContext
	EntityManager em;

	@EJB(name = "ess.RoleService")
	RoleServiceRemote service;

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

		Role entity = new Role();
		entity.setName("Role test name");
		entity = service.save(entity);

		Assert.assertNotNull(entity);
		Assert.assertEquals(1, service.find(entity).size());
		Assert.assertEquals("Role test name", service.find(entity.getId())
				.getName());
		System.out.println("BEFORE " + entity.getId() + " " + entity.getName()
				+ "\nAFTER " + service.find(entity.getId()).getId() + " "
				+ service.find(entity.getId()).getName());
		// Assert.assertEquals(entity, service.find(entity.getId()));
		Assert.assertTrue(service.getAll() != null
				&& service.getAll().size() >= 1);
		entity.setName("Role test name updated");
		entity = service.update(entity);
		Assert.assertEquals("Role test name updated",
				service.find(entity.getId()).getName());
		long id = entity.getId();
		service.delete(entity.getId());
		Assert.assertNull(service.find(id));

		ux.commit();

	}
}
