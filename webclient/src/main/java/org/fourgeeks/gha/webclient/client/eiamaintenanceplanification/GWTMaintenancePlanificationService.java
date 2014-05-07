package org.fourgeeks.gha.webclient.client.eiamaintenanceplanification;

import java.util.List;

import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.gmh.Eia;
import org.fourgeeks.gha.domain.gmh.EiaMaintenancePlanification;
import org.fourgeeks.gha.domain.gmh.EiaPlanificationEntity;
import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.domain.gmh.EiaTypeMaintenancePlan;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * @author naramirez
 */
@RemoteServiceRelativePath("EiaMaintenancePlanification")
public interface GWTMaintenancePlanificationService extends RemoteService {

	public List<EiaMaintenancePlanification> find(EiaType eiaType)
			throws GHAEJBException;

	public EiaMaintenancePlanification save(
			EiaMaintenancePlanification preventivePlanif)
			throws GHAEJBException;

	public boolean existMantenancePlanification(Eia eia,
			EiaTypeMaintenancePlan plan) throws GHAEJBException;

	public List<EiaPlanificationEntity> findEiaMaintenancePlanificationStatus(
			Eia eia, EiaTypeMaintenancePlan plan) throws GHAEJBException;

	public List<EiaMaintenancePlanification> save(
			List<EiaMaintenancePlanification> listPlanif)
			throws GHAEJBException;

}
