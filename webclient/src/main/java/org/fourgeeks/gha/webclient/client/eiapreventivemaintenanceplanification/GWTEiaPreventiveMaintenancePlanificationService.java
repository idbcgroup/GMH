package org.fourgeeks.gha.webclient.client.eiapreventivemaintenanceplanification;

import java.util.List;

import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.gmh.EiaPreventiveMaintenancePlanification;
import org.fourgeeks.gha.domain.gmh.EiaType;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("EiaPreventiveMaintenancePlanification")
public interface GWTEiaPreventiveMaintenancePlanificationService extends
		RemoteService {

	public List<EiaPreventiveMaintenancePlanification> find(EiaType eiaType)
			throws GHAEJBException;

	public EiaPreventiveMaintenancePlanification save(
			EiaPreventiveMaintenancePlanification preventivePlanif)
			throws GHAEJBException;

}
