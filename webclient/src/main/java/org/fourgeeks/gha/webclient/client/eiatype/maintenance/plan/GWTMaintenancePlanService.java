/**
 * 
 */
package org.fourgeeks.gha.webclient.client.eiatype.maintenance.plan;

import java.util.List;

import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.domain.gmh.MaintenancePlan;
import org.fourgeeks.gha.domain.gmh.MaintenancePlanStadisticData;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * @author emiliot
 * 
 */
@RemoteServiceRelativePath("maintenancePlanService")
public interface GWTMaintenancePlanService extends RemoteService {
	/**
	 * @param Id
	 * @throws GHAEJBException
	 */
	public void delete(long Id) throws GHAEJBException;

	/**
	 * @param maintenancePlans
	 * @throws GHAEJBException
	 */
	public void delete(List<MaintenancePlan> maintenancePlans)
			throws GHAEJBException;

	/**
	 * @param eiaType
	 * @return a list with the maintenance plans associated with this eiatype
	 * @throws GHAEJBException
	 */
	public List<MaintenancePlan> findByEiaType(EiaType eiaType)
			throws GHAEJBException;

	/**
	 * @param eiaType
	 * @param offset
	 * @param size
	 * @return a list with size maintenance plans starting on offset associated
	 *         with this eiatype
	 * @throws GHAEJBException
	 */
	public List<MaintenancePlan> findByEiaType(EiaType eiaType, int offset,
			int size) throws GHAEJBException;

	/**
	 * @param maintenancePlan
	 * @return the list of maintenance plans like the parameter
	 * @throws GHAEJBException
	 */
	public List<MaintenancePlan> find(MaintenancePlan maintenancePlan)
			throws GHAEJBException;

	/**
	 * @param Id
	 * @return the EiaTypeMaintenancePlan by id
	 * @throws GHAEJBException
	 */
	public MaintenancePlan find(long Id) throws GHAEJBException;

	/**
	 * @return the list of EiaTypeMaintenancePlan
	 * @throws GHAEJBException
	 */
	public List<MaintenancePlan> getAll() throws GHAEJBException;

	/**
	 * @param offset
	 * @param size
	 * @return the list of size EiaTypeMaintenancePlan starting on offset
	 * @throws GHAEJBException
	 */
	public List<MaintenancePlan> getAll(int offset, int size)
			throws GHAEJBException;

	/**
	 * @param maintenancePlan
	 * @return the saved maintenancePlan
	 * @throws GHAEJBException
	 */
	public MaintenancePlan save(MaintenancePlan maintenancePlan)
			throws GHAEJBException;

	/**
	 * @param maintenancePlan
	 * @return the updated maintenancePlan
	 * @throws GHAEJBException
	 */
	public MaintenancePlan update(MaintenancePlan maintenancePlan)
			throws GHAEJBException;

	/**
	 * Return Stadistic information about the maintenance plan like: number of
	 * activities, estimated cost, times effectuated, number of eias with this
	 * plan, last time effectuated
	 * 
	 * @param mantenancePlan
	 * @return the stadistic information
	 * @throws GHAEJBException
	 */
	public MaintenancePlanStadisticData getStadisticInfo(
			MaintenancePlan mantenancePlan) throws GHAEJBException;
}
