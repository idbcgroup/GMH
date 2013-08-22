/**
 * 
 */
package org.fourgeeks.gha.ejb.gmh;

import java.util.List;

import javax.ejb.Remote;

import org.fourgeeks.gha.domain.exceptions.EJBException;
import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.domain.gmh.EiaTypeMaintenancePlan;

/**
 * @author emiliot
 *
 */

@Remote
public interface EiaTypeMaintenancePlanServiceRemote {
	/**
	 * Delete a Maintenance Plan from database by Id
	 * @throws EJBException
	 */
	public void delete(long Id) throws EJBException;

	/**
	 * @param eiaType
	 * @return a list with the maintenance plan related to the eiaType
	 * @throws EJBException
	 */
	public List<EiaTypeMaintenancePlan> findByEiaType(EiaType eiaType) throws EJBException;

	/**
	 * @param eiaType
	 * @param offset
	 * @param size
	 * @return a list with the maintenance plans related to eiaType
	 * @throws EJBException
	 */
	public List<EiaTypeMaintenancePlan> findByEiaType(EiaType eiaType, int offset, int size)
			throws EJBException;

	/**
	 * @param code
	 * @return the MaintenancePlan with this Id
	 * @throws EJBException
	 */
	public EiaTypeMaintenancePlan find(long Id) throws EJBException;

	/**
	 * @return the list with all MaintenancePlan Objects
	 * @throws EJBException
	 */
	public List<EiaTypeMaintenancePlan> getAll() throws EJBException;

	/**
	 * @param offset
	 * @param size
	 * @return List of MaintenancePlans beginning in offset up to size
	 * @throws EJBException
	 */
	public List<EiaTypeMaintenancePlan> getAll(int offset, int size) throws EJBException;

	/**
	 * @param EiaTypeMaintenancePlan the plan to be saved on database
	 * @throws EJBException
	 * @return MaintenancePlan saved
	 */
	public EiaTypeMaintenancePlan save(EiaTypeMaintenancePlan maintenancePlan) throws EJBException;

	/**
	 * @param EiaTypeMaintenancePlan
	 *            the MaintenancePlan to be updated
	 * @return MaintenancePlan updated
	 * @throws EJBException
	 */
	public EiaTypeMaintenancePlan update(EiaTypeMaintenancePlan maintenancePlan) throws EJBException;
}
