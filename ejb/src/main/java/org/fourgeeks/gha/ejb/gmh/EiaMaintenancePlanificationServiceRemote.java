package org.fourgeeks.gha.ejb.gmh;

import java.util.List;

import javax.ejb.Remote;

import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.gmh.Eia;
import org.fourgeeks.gha.domain.gmh.EiaMaintenancePlanification;
import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.domain.gmh.EiaTypeMaintenancePlan;

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
	 * @param Id
	 * @throws GHAEJBException
	 */
	public void delete(long Id) throws GHAEJBException;

	/**
	 * @param Id
	 * @throws GHAEJBException
	 */
	public boolean existMantenancePlanification(Eia eia,
			EiaTypeMaintenancePlan plan) throws GHAEJBException;

	/**
	 * Return a list of EiaMaintenancePlanification given a EiaType
	 * 
	 * @param eiaType
	 * @return a list of EiaMaintenancePlanification entities
	 * @throws GHAEJBException
	 */
	public List<EiaMaintenancePlanification> find(EiaType eiaType)
			throws GHAEJBException;

	/**
	 * Returns a list of an Object array containing the Eia and the associated
	 * EiaMaintenancePlanification
	 * 
	 * @param eia
	 * @param plan
	 * @return
	 * @throws GHAEJBException
	 */
	public List<EiaPlanificationEntity> findEiaMaintenancePlanificationStatus(
			Eia eia, EiaTypeMaintenancePlan plan) throws GHAEJBException;

}
