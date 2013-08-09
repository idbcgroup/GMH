package org.fourgeeks.gha.ejb.gmh;

import javax.naming.Context;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import junit.framework.TestCase;

import org.fourgeeks.gha.ejb.ContextDeployment;

/**
 * @author alacret
 * 
 */
public class MaterialServiceTest extends TestCase {

	private ContextDeployment contextDeployment;
	private MaterialServiceRemote service;

	@Override
	protected void setUp() throws Exception {
		Context context = ContextDeployment.getContext();
		service = (MaterialServiceRemote) context
				.lookup("java:global/ejb/gmh.MaterialService");
	}

	public void test() throws Exception {
		service.getAll();

		EntityManager em = null;

		try {

			EntityManagerFactory emf = Persistence
					.createEntityManagerFactory("gha");
			EntityManager em3 = emf.createEntityManager();
			em3.close();
			System.out.println("si es 11");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("no es 11");
		}

		// .lookup("java:global/ejb/gmh.BrandService");

	}
}
