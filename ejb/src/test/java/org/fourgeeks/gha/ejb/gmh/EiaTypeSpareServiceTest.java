package org.fourgeeks.gha.ejb.gmh;

import java.util.List;

import junit.framework.TestCase;

import org.fourgeeks.gha.domain.enu.EiaMobilityEnum;
import org.fourgeeks.gha.domain.enu.EiaTypeEnum;
import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.domain.gmh.EiaTypeSpare;
import org.fourgeeks.gha.ejb.ContextDeployment;
import org.junit.Test;

public class EiaTypeSpareServiceTest extends TestCase {

	private ContextDeployment contextDeployment;
	private EiaTypeSpareServiceRemote eiaTypeSpareService;
	private EiaTypeSpare entity;
	private List<EiaTypeSpare> eiaTypeSpareBefore;
	private EiaType eiaType, spare, newSpare;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		
		contextDeployment = new ContextDeployment();
		eiaTypeSpareService = (EiaTypeSpareServiceRemote) contextDeployment.getContext()
				.lookup("java:global/ejb/gmh.EiaTypeSpareService");
		
		// Método A: creando los eiaType
		EiaTypeServiceRemote eiaTypeService = (EiaTypeServiceRemote) contextDeployment.getContext()
				.lookup("java:global/ejb/gmh.EiaTypeService");
		
		eiaType = new EiaType();
		eiaType.setName("eiaType");
		eiaType.setMobility(EiaMobilityEnum.FIXED);
		eiaType.setType(EiaTypeEnum.EQUIPMENT);
		eiaType = eiaTypeService.save(eiaType);
		
		spare = new EiaType();
		spare.setName("spare");
		spare.setMobility(EiaMobilityEnum.FIXED);
		spare.setType(EiaTypeEnum.EQUIPMENT);
		spare = eiaTypeService.save(spare);
		
		newSpare = new EiaType();
		newSpare.setName("newSpare");
		newSpare.setMobility(EiaMobilityEnum.FIXED);
		newSpare.setType(EiaTypeEnum.EQUIPMENT);
		newSpare = eiaTypeService.save(newSpare);
		
		// Método B: usando eiaTypes ya existentes
		// List<EiaType> eiaTypes = eiaTypeService.getAll();
		// eiaType = eiaTypes.get(0);
		// spare = eiaTypes.get(1);
		// newSpare = eiaTypes.get(2);

		entity = new EiaTypeSpare();
		entity.setEiaType(eiaType);
		entity.setSpare(spare);
		eiaTypeSpareBefore = eiaTypeSpareService.getAll();
		entity = eiaTypeSpareService.save(entity);
	}

	@Test
	public void test() throws Exception {
		System.out.println("Testing not null");
		assertNotNull(eiaTypeSpareService);

		System.out.println("Testing save()");
		assertNotNull(entity);
		assertEquals(eiaType.getId(), entity.getEiaType().getId());
		assertEquals(spare.getId(), entity.getSpare().getId());
		
		System.out.println("Testing getAll()");
		assertTrue(eiaTypeSpareService.getAll().size() > eiaTypeSpareBefore.size());

		System.out.println("Testing find()");
		List<EiaTypeSpare> eiaTypeSpares = eiaTypeSpareService.find(eiaType);
		EiaTypeSpare eiaTypeSpareExpected = eiaTypeSpareService.find(entity.getId());
		assertEquals(eiaTypeSpares.get(0).getId(), entity.getId());
		assertNotNull(eiaTypeSpareExpected);

		System.out.println("Testing update()");
		entity.setSpare(newSpare);
		entity = eiaTypeSpareService.update(entity);
		assertEquals(entity.getSpare().getId(), newSpare.getId());

		System.out.println("Testing delete()");
		eiaTypeSpareService.delete(entity.getId());
		List<EiaTypeSpare> brandsAfter = eiaTypeSpareService.getAll();
		assertEquals(brandsAfter.size(), eiaTypeSpareBefore.size());
	}
}
