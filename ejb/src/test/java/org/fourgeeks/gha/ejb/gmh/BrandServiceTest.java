package org.fourgeeks.gha.ejb.gmh;

import java.util.List;

import javax.naming.Context;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import junit.framework.TestCase;

import org.fourgeeks.gha.domain.gmh.Brand;
import org.fourgeeks.gha.ejb.ContextDeployment;
import org.junit.Before;

/**
 * @author vivi.torressg
 * 
 */
public class BrandServiceTest extends TestCase {

	private BrandServiceRemote ejbService;
	// private Brand entity;
	List<Brand> brandsBefore;
	private EntityManager em;
	private EntityManagerFactory emf;

	@Before
	protected void setUp() throws Exception {
		Context context = ContextDeployment.getContext();
		ejbService = (BrandServiceRemote) context
				.lookup("java:global/ejb/gmh.BrandService");
		emf = Persistence.createEntityManagerFactory("gha");
		em = emf.createEntityManager();
		Brand brand = new Brand();
		brand.setName("testbrand");
		em.persist(brand);
		em.close();
		emf.close();

	}

	public void test() throws Exception {
		// System.out.println("Testing not null");
		// assertNotNull(ejbService);
		//
		// System.out.println("Testing getAll()");
		// assertNotNull(ejbService.getAll());
		// assert ejbService.getAll().size() > 0;
		//
		// System.out.println("Testing save()");
		// entity = ejbService.save(entity);
		// assertNotNull(entity);
		// assertEquals("Brand Test", entity.getName());
		//
		// System.out.println("Testing find()");
		// List<Brand> brands = ejbService.find(entity);
		// Brand brandExpected = ejbService.find(entity.getId());
		// assertNotNull(brands);
		// assertNotNull(brandExpected);
		//
		// System.out.println("Testing update()");
		// entity.setName("Updating Brand Test");
		// entity = ejbService.update(entity);
		// assertEquals(entity.getName(), "Updating Brand Test");
		//
		// System.out.println("Testing delete()");
		// ejbService.delete(entity.getId());
		// List<Brand> brandsAfter = ejbService.getAll();
		// assertEquals(brandsAfter.size(), brandsBefore.size());
	}
}
