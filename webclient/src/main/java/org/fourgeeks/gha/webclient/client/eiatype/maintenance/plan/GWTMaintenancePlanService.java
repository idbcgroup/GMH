/**
 * 
 */
package org.fourgeeks.gha.webclient.client.eiatype.maintenance.plan;

import java.util.List;

import org.fourgeeks.gha.domain.exceptions.EJBException;
import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.domain.gmh.MaintenancePlan;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * @author emiliot
 *
 */
@RemoteServiceRelativePath("maintenancePlanService")
public interface GWTMaintenancePlanService extends RemoteService{
	/**
	 * @param Id
	 * @throws EJBException
	 */
	public void delete(long Id) throws EJBException;

	/**
	 * @param eiaType
	 * @return a list with the maintenance plans associated with this eiatype
	 * @throws EJBException
	 */
	public List<MaintenancePlan> findByEiaType(EiaType eiaType)
			throws EJBException;
	
	/**
	 * @param eiaType
	 * @return a list with size maintenance plans starting on offset associated with this eiatype
	 * @throws EJBException
	 */
	public List<MaintenancePlan> findByEiaType(EiaType eiaType, int offset, int size)
			throws EJBException;

	/**
	 * @param Id
	 * @return the EiaTypeMaintenancePlan by id
	 * @throws EJBException
	 */
	public MaintenancePlan find(long Id) throws EJBException;

	/**
	 * @return the list of EiaTypeMaintenancePlan
	 * @throws EJBException
	 */
	public List<MaintenancePlan> getAll() throws EJBException;
	
	/**
	 * @param offset
	 * @param size
	 * @return the list of size EiaTypeMaintenancePlan starting on offset
	 * @throws EJBException
	 */
	public List<MaintenancePlan> getAll(int offset, int size) throws EJBException;

	/**
	 * @param MaintenancePlan
	 * @return the saved maintenancePlan
	 * @throws EJBException
	 */
	public MaintenancePlan save(
			MaintenancePlan maintenancePlan) throws EJBException;

	/**
	 * @param MaintenancePlan
	 * @return the updated maintenancePlan
	 * @throws EJBException
	 */
	public MaintenancePlan update(
			MaintenancePlan maintenancePlan) throws EJBException;
}
