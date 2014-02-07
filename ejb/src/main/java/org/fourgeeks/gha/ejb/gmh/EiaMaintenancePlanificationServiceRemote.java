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
	 * Save a EiaMaintenancePlanification in the DB
	 * 
	 * @param planif
	 *            the EiaMaintenancePlanification to save
	 * @return the saved EiaMaintenancePlanification
	 * @throws GHAEJBException
	 */
	public EiaMaintenancePlanification save(EiaMaintenancePlanification planif)
			throws GHAEJBException;

	/**
	 * Return a list of EiaMaintenancePlanification given a EiaType
	 * 
	 * @param eiaType
	 * @return a list of EiaMaintenancePlanification entities
	 * @throws GHAEJBException
	 */
	public List<EiaMaintenancePlanification> find(EiaType eiaType)
			throws GHAEJBException;
}
