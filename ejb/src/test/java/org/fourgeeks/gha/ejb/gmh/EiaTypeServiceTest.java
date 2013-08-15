package org.fourgeeks.gha.ejb.gmh;

import javax.ejb.Stateless;
import javax.naming.Context;

import junit.framework.TestCase;

import org.fourgeeks.gha.domain.enu.EiaMobilityEnum;
import org.fourgeeks.gha.domain.enu.EiaTypeEnum;
import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.ejb.ContextDeployment;
import org.junit.Test;

@Stateless
public class EiaTypeServiceTest extends TestCase {

	private Context context;
	private EiaTypeServiceRemote service;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		context = new ContextDeployment().getContext();

		service = (EiaTypeServiceRemote) context
				.lookup("java:global/ejb/gmh.EiaTypeService");

	}

	@Test
	public void test() throws Exception {
		EiaType eiaType = new EiaType();
		eiaType.setName("eiaType");
		eiaType.setMobility(EiaMobilityEnum.FIXED);
		eiaType.setType(EiaTypeEnum.EQUIPMENT);
		eiaType = service.save(eiaType);

	}
}
