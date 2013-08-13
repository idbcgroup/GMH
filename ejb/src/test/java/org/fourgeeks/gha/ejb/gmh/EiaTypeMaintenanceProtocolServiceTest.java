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
public class EiaTypeMaintenanceProtocolServiceTest extends TestCase {
	private EiaTypeMaintenancePlanServiceRemote ejbMantPlan;
	private EiaTypeMaintenanceProtocolServiceRemote ejbService;
	private List<EiaTypeMaintenancePlan> maintenancePlans;
	private List<EiaTypeMaintenanceProtocol> maintenanceProtocols;

	@Override
	protected void setUp() throws Exception {
		 Context context = ContextDeployment.getContext();
		 ejbService = (EiaTypeMaintenanceProtocolServiceRemote) context
				 .lookup("java:global/ejb/gmh.EiaTypeMaintenanceProtocolService");
		 ejbMantPlan = (EiaTypeMaintenancePlanServiceRemote) context
				 .lookup("java:global/ejb/gmh.EiaTypeMaintenancePlanService");
	}

	public void test() throws Exception {

		System.out.println("testing show protocols by plan");
		maintenancePlans = ejbMantPlan.getAll();
		assertNotNull(maintenancePlans);

		maintenanceProtocols = ejbService.findByEiaTypeMaintenancePlan(maintenancePlans.get(0));
		for(EiaTypeMaintenanceProtocol protocol : maintenanceProtocols){
//			System.out.println(protocol.getDescription() + "ordinal #" + protocol.getOrdinal());
			
//			System.out.println("children");
			List <EiaTypeMaintenanceProtocol> childrenProtocols = protocol.getChildrenProtocols();
			assertNotNull(childrenProtocols);
			
			for(EiaTypeMaintenanceProtocol childProtocol : childrenProtocols){
//				System.out.println(childProtocol.getDescription());
			}
		}

	}
}
