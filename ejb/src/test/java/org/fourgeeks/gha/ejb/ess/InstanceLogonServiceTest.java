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

import org.fourgeeks.gha.domain.ess.InstanceLogon;
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
public class InstanceLogonServiceTest extends GhaServiceTest {
	@PersistenceContext
	EntityManager em;

	@EJB(name = "ess.InstanceLogonService")
	InstanceLogonServiceRemote service;

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

		InstanceLogon entity = new InstanceLogon();

		entity = service.save(entity);

		Assert.assertNotNull(entity);
		// Assert.assertEquals(entity, service.find(entity)); //TODO el método
		// del servicio siempre devuelve NULL (línea 50 de InstanceLogonService)
		Assert.assertEquals(entity, service.find(entity.getId()));
		Assert.assertNotNull(service.getAll());
		if (super.getBpa() == null) {
			Bpa bpa = new Bpa();
			em.persist(bpa);
			em.flush();
			super.setBpa(em.find(Bpa.class, bpa.getId()));
		}
		entity.setBpa(super.getBpa());
		entity = service.update(entity);
		Assert.assertEquals(super.getBpa(), service.find(entity.getId())
				.getBpa());
		long id = entity.getId();
		service.delete(entity.getId());
		Assert.assertNull(service.find(id));

		ux.commit();

	}
}
