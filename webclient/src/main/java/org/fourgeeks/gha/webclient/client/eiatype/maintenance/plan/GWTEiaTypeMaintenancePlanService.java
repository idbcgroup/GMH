/**
 * 
 */
package org.fourgeeks.gha.webclient.client.eiatype.maintenance.plan;

import java.util.List;

import org.fourgeeks.gha.domain.exceptions.EJBException;
import org.fourgeeks.gha.domain.gmh.EiaTypeMaintenancePlan;

import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * @author emiliot
 *
 */
@RemoteServiceRelativePath("eiaTypeMaintenancePlan")
public interface GWTEiaTypeMaintenancePlanService {
	/**
	 * @param Id
	 * @throws EJBException
	 */
	public void delete(long Id) throws EJBException;

	/**
	 * @param eiaTypeCode
	 * @return a list with the maintenance plans associated with this eiatype
	 * @throws EJBException
	 */
	public List<EiaTypeMaintenancePlan> findByEiaType(String eiaTypeCode)
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
