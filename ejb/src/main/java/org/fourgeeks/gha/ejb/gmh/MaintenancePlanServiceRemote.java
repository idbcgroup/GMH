/**
 * 
 */
package org.fourgeeks.gha.ejb.gmh;

import java.util.List;

import javax.ejb.Remote;

import org.fourgeeks.gha.domain.exceptions.EJBException;
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
	 * @throws EJBException
	 */
	public void delete(long Id) throws EJBException;

	/**
	 * @param eiaType
	 * @return a list with the maintenance plan related to the eiaType
	 * @throws EJBException
	 */
	public List<MaintenancePlan> findByEiaType(EiaType eiaType) throws EJBException;

	/**
	 * @param eiaType
	 * @param offset
	 * @param size
	 * @return a list with the maintenance plans related to eiaType
	 * @throws EJBException
	 */
	public List<MaintenancePlan> findByEiaType(EiaType eiaType, int offset, int size)
			throws EJBException;

	/**
	 * @param Id
	 * @return the MaintenancePlan with this Id
	 * @throws EJBException
	 */
	public MaintenancePlan find(long Id) throws EJBException;

	/**
	 * @return the list with all MaintenancePlan Objects
	 * @throws EJBException
	 */
	public List<MaintenancePlan> getAll() throws EJBException;

	/**
	 * @param offset
	 * @param size
	 * @return List of MaintenancePlans beginning in offset up to size
	 * @throws EJBException
	 */
	public List<MaintenancePlan> getAll(int offset, int size) throws EJBException;

	/**
	 * @param MaintenancePlan the plan to be saved on database
	 * @throws EJBException
	 * @return MaintenancePlan saved
	 */
	public MaintenancePlan save(MaintenancePlan maintenancePlan) throws EJBException;

	/**
	 * @param MaintenancePlan
	 *            the MaintenancePlan to be updated
	 * @return MaintenancePlan updated
	 * @throws EJBException
	 */
	public MaintenancePlan update(MaintenancePlan maintenancePlan) throws EJBException;
}
