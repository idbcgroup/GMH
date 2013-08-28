/**
 * 
 */
package org.fourgeeks.gha.ejb.gmh;

import java.util.List;

import javax.naming.Context;

import junit.framework.TestCase;

import org.fourgeeks.gha.domain.gmh.EiaTypeMaintenancePlan;
import org.fourgeeks.gha.domain.gmh.EiaTypeMaintenanceProtocol;
import org.fourgeeks.gha.ejb.ContextDeployment;

/**
 * @author emiliot
 *
 */
public class EiaTypeMaintenanceProtocolServiceTest extends TestCase{
	private EiaTypeMaintenanceProtocolServiceRemote ejbService;
	private EiaTypeMaintenancePlanServiceRemote planService;
	
	@Override
	protected void setUp() throws Exception {
		 Context context = ContextDeployment.getContext();
		 ejbService = (EiaTypeMaintenanceProtocolServiceRemote) context
				 .lookup("java:global/ejb/gmh.EiaTypeMaintenanceProtocolService");
		 planService = (EiaTypeMaintenancePlanServiceRemote) context.lookup("java:global/ejb/gmh.EiaTypeMaintenancePlanService");
		 
	}

	public void test() throws Exception {
		System.out.println("Testing getAll");
		List <EiaTypeMaintenanceProtocol> getAll = ejbService.getAll();
		
		assertNotNull(getAll);
		
		for(EiaTypeMaintenanceProtocol protocol : getAll){
			System.out.println(protocol.getDescription());
		}
		
		System.out.println("Testing Find by Plan");
		EiaTypeMaintenancePlan eiaTypeMaintenancePlan = planService.find(1L);
		
		List <EiaTypeMaintenanceProtocol> findByPlan = ejbService.findByEiaTypeMaintenancePlan(eiaTypeMaintenancePlan);
		
		assertNotNull(findByPlan);
		
		for(EiaTypeMaintenanceProtocol protocol : findByPlan){
			System.out.println(protocol.getDescription());
		}
		
		System.out.println("Testing Save");
		EiaTypeMaintenanceProtocol save = new EiaTypeMaintenanceProtocol();
		save.setDescription("testing save");
		
		EiaTypeMaintenanceProtocol saved = ejbService.save(save);
		assertNotNull(saved);
		assertNotNull(saved.getId());
		assert(saved.getId() > 0L);
		
		System.out.println("Testing find");
		EiaTypeMaintenanceProtocol find = ejbService.find(saved.getId());
		assertNotNull(find);
		System.out.println(find.getDescription());
		
		System.out.println("Testing Delete");
		ejbService.delete(find.getId());		
	}
}
