package org.fourgeeks.gha.ejb.gmh;

import java.util.List;

import javax.ejb.Remote;

import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.gmh.EiaMaintenancePlanification;
import org.fourgeeks.gha.domain.gmh.EiaType;

/**
 * @author naramirez
 * 
 */
@Remote
public interface EiaMaintenancePlanificationServiceRemote {

	/**
	 * @param planif
	 * @return the saved EiaMaintenancePlanification
	 * @throws GHAEJBException
	 */
	public EiaMaintenancePlanification save(EiaMaintenancePlanification planif)
			throws GHAEJBException;

	/**
	 * @param eiaType
	 * @return a list of EiaPreventiveMaintenancePlanification entities
	 * @throws GHAEJBException
	 */
	public List<EiaMaintenancePlanification> find(EiaType eiaType)
			throws GHAEJBException;
}
