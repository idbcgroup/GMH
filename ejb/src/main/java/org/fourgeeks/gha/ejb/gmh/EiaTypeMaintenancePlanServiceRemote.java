/**
 * 
 */
package org.fourgeeks.gha.ejb.gmh;

import java.util.List;

import javax.ejb.Remote;

import org.fourgeeks.gha.domain.exceptions.EJBException;
import org.fourgeeks.gha.domain.gmh.EiaTypeMaintenancePlan;

/**
 * @author emiliot
 * 
 */

@Remote
public interface EiaTypeMaintenancePlanServiceRemote {
	/**
	 * @param Id
	 * @throws EJBException
	 */
	public void delete(long Id) throws EJBException;

	/**
	 * @param code
	 * @return a list with the maintenance plans associated with this eiatype
	 * @throws EJBException
	 */
	public List<EiaTypeMaintenancePlan> findByEiaType(String code)
			throws EJBException;

	/**
	 * @param Id
	 * @return the EiaTypeMaintenancePlan by id
	 * @throws EJBException
	 */
	public EiaTypeMaintenancePlan find(long Id) throws EJBException;

	/**
	 * @return the list of EiaTypeMaintenancePlan
	 * @throws EJBException
	 */
	public List<EiaTypeMaintenancePlan> getAll() throws EJBException;

	/**
	 * @param EiaTypeMaintenancePlan
	 * @return the saved EiaTypeMaintenancePlan
	 * @throws EJBException
	 */
	public EiaTypeMaintenancePlan save(
			EiaTypeMaintenancePlan eiaTypeMaintenancePlan) throws EJBException;

	/**
	 * @param EiaTypeMaintenancePlan
	 * @return the updated EiaTypeMaintenancePlan
	 * @throws EJBException
	 */
	public EiaTypeMaintenancePlan update(
			EiaTypeMaintenancePlan eiaTypeMaintenancePlan) throws EJBException;
}
