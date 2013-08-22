/**
 * 
 */
package org.fourgeeks.gha.ejb.gmh;

import java.util.List;

import javax.naming.Context;

import junit.framework.TestCase;

import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.domain.gmh.EiaTypeMaintenancePlan;
import org.fourgeeks.gha.ejb.ContextDeployment;

/**
 * @author emiliot
 *
 */
public class EiaTypeMaintenancePlanServiceTest extends TestCase {
	private EiaTypeMaintenancePlanServiceRemote ejbService;
	private EiaTypeServiceRemote eiaTypeService;
	private List<EiaTypeMaintenancePlan> maintenancePlans;
	
	@Override
	protected void setUp() throws Exception {
		 Context context = ContextDeployment.getContext();
		 ejbService = (EiaTypeMaintenancePlanServiceRemote) context
				 .lookup("java:global/ejb/gmh.EiaTypeMaintenancePlanService");
		 eiaTypeService = (EiaTypeServiceRemote) context.lookup("java:global/ejb/gmh.EiaTypeService");
		 
	}

	public void test() throws Exception {
		
		System.out.println("testing get all");
		maintenancePlans = ejbService.getAll();
		assertNotNull(maintenancePlans);
		
		for(EiaTypeMaintenancePlan plan : maintenancePlans){
			System.out.println("Maintenance Plan: "+ plan.getDescription());
		}
		
		EiaType eiaType = eiaTypeService.find("90001");
		EiaTypeMaintenancePlan eiaTypeMaintenancePlan = maintenancePlans.get(0);
		eiaTypeMaintenancePlan.setEiaType(eiaType);
		ejbService.update(eiaTypeMaintenancePlan);
		
		List<EiaTypeMaintenancePlan> mantPlansByEiaType = ejbService.findByEiaType(new EiaType("90001"));
		assertNotNull(mantPlansByEiaType);
		for(EiaTypeMaintenancePlan etmp : mantPlansByEiaType){
			System.out.println(etmp.getDescription());
		}
		
	}
}
