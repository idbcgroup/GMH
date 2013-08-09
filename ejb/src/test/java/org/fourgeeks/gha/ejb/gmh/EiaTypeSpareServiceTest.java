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
	private EiaTypeServiceRemote eiaTypeService;

	@Override
	protected void setUp() throws Exception {
		super.setUp();

		contextDeployment = new ContextDeployment();
		eiaTypeSpareService = (EiaTypeSpareServiceRemote) contextDeployment
				.getContext().lookup("java:global/ejb/gmh.EiaTypeSpareService");

		// Esta variable debe ser de instancia
		eiaTypeService = (EiaTypeServiceRemote) contextDeployment.getContext()
				.lookup("java:global/ejb/gmh.EiaTypeService");

	}

	@Test
	public void test() throws Exception {
		assertNotNull(eiaTypeService);

		EiaType eiaType = new EiaType();
		eiaType.setName("eiaType");
		eiaType.setMobility(EiaMobilityEnum.FIXED);
		eiaType.setType(EiaTypeEnum.EQUIPMENT);
		eiaType = eiaTypeService.save(eiaType);

		EiaType spare = new EiaType();
		spare.setName("spare");
		spare.setMobility(EiaMobilityEnum.FIXED);
		spare.setType(EiaTypeEnum.EQUIPMENT);
		spare = eiaTypeService.save(spare);

		EiaType newSpare = new EiaType();
		newSpare.setName("newSpare");
		newSpare.setMobility(EiaMobilityEnum.FIXED);
		newSpare.setType(EiaTypeEnum.EQUIPMENT);
		newSpare = eiaTypeService.save(newSpare);

		List<EiaTypeSpare> eiaTypeSpareBefore = eiaTypeSpareService.getAll();

		EiaTypeSpare entity = new EiaTypeSpare();
		entity.setEiaType(eiaType);
		entity.setSpare(spare);
		entity = eiaTypeSpareService.save(entity);

		assertNotNull(entity);
		assertEquals(eiaType.getId(), entity.getEiaType().getId());

		List<EiaTypeSpare> eiaTypeSpareAfter = eiaTypeSpareService.getAll();

		assertTrue(eiaTypeSpareBefore.size() > eiaTypeSpareAfter.size());

		eiaTypeSpareService.delete(entity.getId());

		eiaTypeSpareAfter = eiaTypeSpareService.getAll();

		assertEquals(eiaTypeSpareBefore.size(), eiaTypeSpareAfter.size());
	}
}
