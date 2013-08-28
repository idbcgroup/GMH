/**
 * 
 */
package org.fourgeeks.gha.ejb.gmh;

import java.util.List;

import javax.naming.Context;

import junit.framework.TestCase;

import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.domain.gmh.MaintenancePlan;
import org.fourgeeks.gha.ejb.ContextDeployment;

/**
 * @author emiliot
 *
 */
public class EiaTypeMaintenancePlanServiceTest extends TestCase {
	private MaintenancePlanServiceRemote ejbService;
	private EiaTypeServiceRemote eiaTypeService;
	
	@Override
	protected void setUp() throws Exception {
		 Context context = ContextDeployment.getContext();
		 ejbService = (MaintenancePlanServiceRemote) context
				 .lookup("java:global/ejb/gmh.EiaTypeMaintenancePlanService");
		 eiaTypeService = (EiaTypeServiceRemote) context.lookup("java:global/ejb/gmh.EiaTypeService");
		 
	}

	public void test() throws Exception {
		System.out.println("Testing getAll");
		List <MaintenancePlan> getAll = ejbService.getAll();
		
		assertNotNull(getAll);
		
		for(MaintenancePlan plan : getAll){
			System.out.println(plan.getDescription());
		}
		
		System.out.println("Testing Find by EiaType");
		EiaType eiaType = eiaTypeService.find("90001");
		
		List <MaintenancePlan> findByEiaType = ejbService.findByEiaType(eiaType);
		
		assertNotNull(findByEiaType);
		
		for(MaintenancePlan plan : findByEiaType){
			System.out.println(plan.getDescription());
		}
		
		System.out.println("Testing Save");
		MaintenancePlan save = new MaintenancePlan();
		save.setDescription("testing save");
		
		MaintenancePlan saved = ejbService.save(save);
		assertNotNull(saved);
		assertNotNull(saved.getId());
		assert(saved.getId() > 0L);
		
		System.out.println("Testing find");
		MaintenancePlan find = ejbService.find(saved.getId());
		assertNotNull(find);
		System.out.println(find.getDescription());
		
		System.out.println("Testing Delete");
		ejbService.delete(find.getId());		
	}
}
