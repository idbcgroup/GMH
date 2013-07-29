package org.fourgeeks.gha.ejb.gmh;

import java.util.List;

import junit.framework.TestCase;

import org.fourgeeks.gha.domain.gmh.Manufacturer;

/**
 * @author vivi.torressg
 * 
 */
public class ManufacturerServiceTest extends TestCase {

	private ContextDeployment contextDeployment;
	private ManufacturerServiceRemote ejbService;
	private Manufacturer entity;
	List<Manufacturer> manufacturersBefore;

	@Override
	protected void setUp() throws Exception {
		contextDeployment = new ContextDeployment();
		ejbService = (ManufacturerServiceRemote) contextDeployment.getContext()
				.lookup("java:global/ejb/gmh.ManufacturerService");
		entity = new Manufacturer();
		entity.setName("Manufacturer Test");
		manufacturersBefore = ejbService.getAll();
	}

	public void test() throws Exception {
		System.out.println("Testing not null");
		assertNotNull(ejbService);

		System.out.println("Testing getAll()");
		assertNotNull(ejbService.getAll());
		assert ejbService.getAll().size() > 0;

		System.out.println("Testing save()");
		entity = ejbService.save(entity);
		assertNotNull(entity);
		assertEquals("Manufacturer Test", entity.getName());

		System.out.println("Testing find()");
		List<Manufacturer> manufacturers = ejbService.find(entity);
		Manufacturer manufacturerExpected = ejbService.find(entity.getId());
		assertNotNull(manufacturers);
		assertNotNull(manufacturerExpected);

		System.out.println("Testing update()");
		entity.setName("Updating Manufacturer Test");
		entity = ejbService.update(entity);
		assertEquals(entity.getName(), "Updating Manufacturer Test");

		System.out.println("Testing delete()");
		ejbService.delete(entity.getId());
		List<Manufacturer> manufacturersAfter = ejbService.getAll();
		assertEquals(manufacturersAfter.size(), manufacturersBefore.size());
	}
}
