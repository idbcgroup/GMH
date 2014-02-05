package org.fourgeeks.gha.webclient.client.eiapreventivemaintenanceplanification;

import java.util.List;

import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.gmh.EiaMaintenancePlanification;
import org.fourgeeks.gha.domain.gmh.EiaType;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("EiaMaintenancePlanification")
public interface GWTMaintenancePlanificationService extends RemoteService {

	public List<EiaMaintenancePlanification> find(EiaType eiaType)
			throws GHAEJBException;

	public EiaMaintenancePlanification save(
			EiaMaintenancePlanification preventivePlanif)
			throws GHAEJBException;

}
