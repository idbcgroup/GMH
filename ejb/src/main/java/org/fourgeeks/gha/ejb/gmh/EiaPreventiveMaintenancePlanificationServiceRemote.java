package org.fourgeeks.gha.ejb.gmh;

import java.util.List;

import javax.ejb.Remote;

import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.gmh.EiaPreventiveMaintenancePlanification;
import org.fourgeeks.gha.domain.gmh.EiaType;

/**
 * @author naramirez
 * 
 */
@Remote
public interface EiaPreventiveMaintenancePlanificationServiceRemote {

	/**
	 * @param preventivePlanif
	 * @return the saved EiaPreventiveMaintenancePlanification
	 * @throws GHAEJBException
	 */
	public EiaPreventiveMaintenancePlanification save(
			EiaPreventiveMaintenancePlanification preventivePlanif)
			throws GHAEJBException;

	/**
	 * @param eiaType
	 * @return a list of EiaPreventiveMaintenancePlanification entities
	 * @throws GHAEJBException
	 */
	public List<EiaPreventiveMaintenancePlanification> find(EiaType eiaType)
			throws GHAEJBException;
}
