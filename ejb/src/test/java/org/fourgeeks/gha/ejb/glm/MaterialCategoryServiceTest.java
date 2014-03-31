package org.fourgeeks.gha.ejb.glm;

import java.util.List;

import javax.ejb.EJB;

import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.glm.MaterialCategory;
import org.fourgeeks.gha.ejb.GHAArquillianBaseServiceTest;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author vivi.torresg
 * 
 */
@RunWith(Arquillian.class)
public class MaterialCategoryServiceTest extends GHAArquillianBaseServiceTest {

	@EJB(lookup = "java:global/test/MaterialCategoryService")
	MaterialCategoryServiceRemote service;

	/**
	 * 
	 */
	@Before
	public void set() {
	}

	/**
	 * 
	 */
	@Test
	public void test() {
		Assert.assertNotNull(service);
		MaterialCategory category1 = new MaterialCategory();
		category1.setName("test-1");
		category1.setCode("T010101");
		MaterialCategory category2 = new MaterialCategory();
		category2.setName("test-2");
		category2.setCode("T010102");

		try {
			MaterialCategory test1 = service.save(category1);
			Assert.assertNotNull(test1);
			Assert.assertEquals(category1.getName(), test1.getName());
			Assert.assertEquals(category1.getCode(), test1.getCode());
			MaterialCategory test2 = service.save(category2);
			Assert.assertNotNull(test2);
			Assert.assertEquals(category2.getName(), test2.getName());
			Assert.assertEquals(category2.getCode(), test2.getCode());
		} catch (GHAEJBException e) {
			System.out.println("Error saving materialcategories");
			Assert.fail(e.getCause().getMessage());
			unset();
		}

		try {
			List<MaterialCategory> categories = service.getAll();
			Assert.assertNotNull(categories);
			categories = null;

			categories = service.getAll(1, 1);
			Assert.assertNotNull(categories);
			Assert.assertEquals(1, categories.size());
		} catch (GHAEJBException e) {
			System.out.println("Error getting materialcategories");
			Assert.fail(e.getCause().getMessage());
			unset();
		}

		try {
			MaterialCategory criteria = new MaterialCategory();
			criteria.setName("test-");

			List<MaterialCategory> categories = service.find(criteria);

			System.out.println("DEBUG");
			for (MaterialCategory category : categories)
				System.out.println(category.getName());
			System.out.println("DEBUG");

			Assert.assertNotNull(categories);
			Assert.assertEquals(2, categories.size());

			for (MaterialCategory category : categories)
				Assert.assertEquals(true, category.getName().startsWith("test"));
		} catch (GHAEJBException e) {
			System.out
					.println("Error find materialcategories by materialcategory");
			Assert.fail(e.getCause().getMessage());
			unset();
		}

		try {
			MaterialCategory update = new MaterialCategory();
			update.setCode("T010101");
			update.setName("test-name-change");
			MaterialCategory updateRes = service.update(update);
			Assert.assertNotNull(updateRes);
			Assert.assertEquals(update.getName(), updateRes.getName());
		} catch (GHAEJBException e) {
			System.out.println("Error getting materialcategories");
			Assert.fail(e.getCause().getMessage());
			unset();
		}
	}

	/**
	 * 
	 */
	@After
	public void unset() {
		System.out.println("Beginning Unset");

		try {
			service.delete("T010101");
			service.delete("T010102");
		} catch (GHAEJBException e) {
			Assert.fail(e.getCause().getMessage());
		}
		System.out.println("Unset Done!");
	}
}
