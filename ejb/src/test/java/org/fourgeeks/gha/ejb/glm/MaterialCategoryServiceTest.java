package org.fourgeeks.gha.ejb.glm;

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
import org.fourgeeks.gha.domain.glm.MaterialCategory;
import org.fourgeeks.gha.domain.glm.MaterialTypeEnum;
import org.fourgeeks.gha.ejb.GhaServiceTest;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author vivi.torresg
 * 
 */
@RunWith(Arquillian.class)
public class MaterialCategoryServiceTest extends GhaServiceTest {
	@PersistenceContext
	EntityManager em;

	@EJB(name = "glm.MaterialCategoryService")
	MaterialCategoryServiceRemote service;

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

		MaterialCategory entity = new MaterialCategory();
		entity.setType(MaterialTypeEnum.MATERIAL);
		entity = service.save(entity);

		Assert.assertNotNull(entity);
		// Assert.assertEquals(1, service.find(entity).size());
		System.out.println("BEFORE " + entity.getId() + " " + entity.getCode()
				+ "\nAFTER " + service.find(entity.getId()).getId() + " "
				+ service.find(entity.getId()).getCode());
		// Assert.assertEquals(entity, service.find(entity.getId()));
		//Assert.assertTrue(service.getAll() != null
		//		&& service.getAll().size() >= 1);
		//Assert.assertTrue(service.getAll(0, 10) != null
		//		&& service.getAll(0, 10).size() >= 1);
		entity.setCode("MaterialCategory test code updated");
		entity = service.update(entity);
		Assert.assertEquals("MaterialCategory test code updated",
				service.find(entity.getId()).getCode());
		long id = entity.getId();
		service.delete(entity.getId());
		Assert.assertNull(service.find(id));

		ux.commit();

	}
}
