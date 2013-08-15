package org.fourgeeks.gha.ejb.gmh;

import java.util.List;

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
		EiaType eiaType = new EiaType("test-001");
		eiaType.setName("eiaType");
		eiaType.setMobility(EiaMobilityEnum.FIXED);
		eiaType.setType(EiaTypeEnum.EQUIPMENT);
		eiaType.setEiaUmdns("prueba");
		eiaType = service.save(eiaType);
		
		System.out.println("Testing find eiatypes by eiatype");
		List <EiaType> eiaTypes = service.find(eiaType);
		assertNotNull(eiaTypes);
		assert(eiaTypes.size() > 0);
		for(EiaType next : eiaTypes){
			System.out.println(next.getCode());
		}
		
		System.out.println("Testing find eiatypes by eiatype eiaumnds");
		EiaType eiaUmnds = new EiaType();
		eiaUmnds.setEiaUmdns("prueba");
		List<EiaType> eiaTypes2 = service.find(eiaUmnds);
		assertNotNull(eiaTypes2);
		assert(eiaTypes2.size() > 0);
		for(EiaType next : eiaTypes2){
			System.out.println(next.getCode());
		}
	}
}
