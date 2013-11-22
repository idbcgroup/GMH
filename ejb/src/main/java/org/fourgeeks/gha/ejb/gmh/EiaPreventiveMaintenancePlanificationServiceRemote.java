package org.fourgeeks.gha.ejb.gmh;

import java.util.List;

import javax.ejb.Remote;

import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.gmh.EiaPreventiveMaintenancePlanification;
import org.fourgeeks.gha.domain.gmh.EiaType;

@Remote
public interface EiaPreventiveMaintenancePlanificationServiceRemote {

	public EiaPreventiveMaintenancePlanification save(
			EiaPreventiveMaintenancePlanification preventivePlanif)
			throws GHAEJBException;

	public List<EiaPreventiveMaintenancePlanification> find(EiaType eiaType)
			throws GHAEJBException;
}
