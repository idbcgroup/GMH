/**
 * 
 */
package org.fourgeeks.gha.ejb.gmh;

import java.util.List;

import javax.naming.Context;

import junit.framework.TestCase;

import org.fourgeeks.gha.domain.gmh.MaintenancePlan;
import org.fourgeeks.gha.domain.gmh.MaintenanceProtocol;
import org.fourgeeks.gha.ejb.ContextDeployment;

/**
 * @author emiliot
 *
 */
public class EiaTypeMaintenanceProtocolServiceTest extends TestCase{
	private MaintenanceProtocolServiceRemote ejbService;
	private MaintenancePlanServiceRemote planService;
	
	@Override
	protected void setUp() throws Exception {
		 Context context = ContextDeployment.getContext();
		 ejbService = (MaintenanceProtocolServiceRemote) context
				 .lookup("java:global/ejb/gmh.EiaTypeMaintenanceProtocolService");
		 planService = (MaintenancePlanServiceRemote) context.lookup("java:global/ejb/gmh.EiaTypeMaintenancePlanService");
		 
	}

	public void test() throws Exception {
		System.out.println("Testing getAll");
		List <MaintenanceProtocol> getAll = ejbService.getAll();
		
		assertNotNull(getAll);
		
		for(MaintenanceProtocol protocol : getAll){
			System.out.println(protocol.getDescription());
		}
		
		System.out.println("Testing Find by Plan");
		MaintenancePlan eiaTypeMaintenancePlan = planService.find(1L);
		
		List <MaintenanceProtocol> findByPlan = ejbService.findByEiaTypeMaintenancePlan(eiaTypeMaintenancePlan);
		
		assertNotNull(findByPlan);
		
		for(MaintenanceProtocol protocol : findByPlan){
			System.out.println(protocol.getDescription());
		}
		
		System.out.println("Testing Save");
		MaintenanceProtocol save = new MaintenanceProtocol();
		save.setDescription("testing save");
		
		MaintenanceProtocol saved = ejbService.save(save);
		assertNotNull(saved);
		assertNotNull(saved.getId());
		assert(saved.getId() > 0L);
		
		System.out.println("Testing find");
		MaintenanceProtocol find = ejbService.find(saved.getId());
		assertNotNull(find);
		System.out.println(find.getDescription());
		
		System.out.println("Testing Delete");
		ejbService.delete(find.getId());		
	}
}
