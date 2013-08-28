/**
 * 
 */
package org.fourgeeks.gha.webclient.client.eiatype.maintenance.plan;

import java.util.List;

import org.fourgeeks.gha.domain.exceptions.EJBException;
import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.domain.gmh.EiaTypeMaintenancePlan;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * @author emiliot
 *
 */
@RemoteServiceRelativePath("eiaTypeMaintenancePlanService")
public interface GWTEiaTypeMaintenancePlanService extends RemoteService{
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
	public List<EiaTypeMaintenancePlan> findByEiaType(EiaType eiaType)
			throws EJBException;
	
	/**
	 * @param eiaType
	 * @return a list with size maintenance plans starting on offset associated with this eiatype
	 * @throws EJBException
	 */
	public List<EiaTypeMaintenancePlan> findByEiaType(EiaType eiaType, int offset, int size)
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
	 * @param offset
	 * @param size
	 * @return the list of size EiaTypeMaintenancePlan starting on offset
	 * @throws EJBException
	 */
	public List<EiaTypeMaintenancePlan> getAll(int offset, int size) throws EJBException;

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
