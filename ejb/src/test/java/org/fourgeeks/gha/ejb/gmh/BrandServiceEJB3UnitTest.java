package org.fourgeeks.gha.ejb.gmh;

import java.util.List;

import org.fourgeeks.gha.domain.gmh.Brand;

import com.bm.testsuite.PoJoFixture;

/**
 * @author Viviana Torres
 * 
 */
public class BrandServiceEJB3UnitTest extends PoJoFixture {
	// TODO configurar EJB3Unit con test persistence usar BrandServiceRemote en
	// las pruebas
	private static final Class<?>[] USED_ENTITIES = { Brand.class,
			BrandService.class, BrandServiceRemote.class };

	public BrandServiceEJB3UnitTest() {
		super(USED_ENTITIES);
	}

	public void testListBrands() {
		List<Brand> brands = findAll(Brand.class);
		System.out.println("brand.name en 0 " + brands.get(0).getName());
		assertNotNull(brands);
		assertTrue(brands.size() == 4);
		assertEquals("Epson", brands.get(0).getName());
	}

	public void testGetEntityManager() {
		assertNotNull(this.getEntityManager());
	}
}
