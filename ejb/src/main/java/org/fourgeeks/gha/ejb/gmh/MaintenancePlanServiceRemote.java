/**
 * 
 */
package org.fourgeeks.gha.ejb.gmh;

import java.util.List;

import javax.ejb.Remote;

import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.domain.gmh.MaintenancePlan;

/**
 * @author emiliot
 *
 */

@Remote
public interface MaintenancePlanServiceRemote {
	/**
	 * Delete a Maintenance Plan from database by Id
	 * @throws GHAEJBException
	 */
	public void delete(long Id) throws GHAEJBException;

	/**
	 * @param eiaType
	 * @return a list with the maintenance plan related to the eiaType
	 * @throws GHAEJBException
	 */
	public List<MaintenancePlan> findByEiaType(EiaType eiaType) throws GHAEJBException;

	/**
	 * @param eiaType
	 * @param offset
	 * @param size
	 * @return a list with the maintenance plans related to eiaType
	 * @throws GHAEJBException
	 */
	public List<MaintenancePlan> findByEiaType(EiaType eiaType, int offset, int size)
			throws GHAEJBException;

	/**
	 * @param Id
	 * @return the MaintenancePlan with this Id
	 * @throws GHAEJBException
	 */
	public MaintenancePlan find(long Id) throws GHAEJBException;

	/**
	 * @return the list with all MaintenancePlan Objects
	 * @throws GHAEJBException
	 */
	public List<MaintenancePlan> getAll() throws GHAEJBException;

	/**
	 * @param offset
	 * @param size
	 * @return List of MaintenancePlans beginning in offset up to size
	 * @throws GHAEJBException
	 */
	public List<MaintenancePlan> getAll(int offset, int size) throws GHAEJBException;

	/**
	 * @param MaintenancePlan the plan to be saved on database
	 * @throws GHAEJBException
	 * @return MaintenancePlan saved
	 */
	public MaintenancePlan save(MaintenancePlan maintenancePlan) throws GHAEJBException;

	/**
	 * @param MaintenancePlan
	 *            the MaintenancePlan to be updated
	 * @return MaintenancePlan updated
	 * @throws GHAEJBException
	 */
	public MaintenancePlan update(MaintenancePlan maintenancePlan) throws GHAEJBException;
}
