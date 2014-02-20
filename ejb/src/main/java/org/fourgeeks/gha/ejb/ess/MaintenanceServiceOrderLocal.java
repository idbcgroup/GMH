package org.fourgeeks.gha.ejb.ess;

import java.util.List;

import javax.ejb.Local;

import org.fourgeeks.gha.domain.ess.MaintenanceServiceOrder;
import org.fourgeeks.gha.domain.exceptions.GHAEJBException;

/**
 * @author naramirez
 */
@Local
public interface MaintenanceServiceOrderLocal {
	/**
	 * @param Id
	 * @throws GHAEJBException
	 */
	public void delete(long Id) throws GHAEJBException;

	/**
	 * @param Id
	 * @return the MaintenanceServiceOrder
	 * @throws GHAEJBException
	 */
	public MaintenanceServiceOrder find(long Id) throws GHAEJBException;

	/**
	 * @param entity
	 * @return the list of MaintenanceServiceOrder
	 * @throws GHAEJBException
	 */
	public List<MaintenanceServiceOrder> find(MaintenanceServiceOrder entity)
			throws GHAEJBException;

	/**
	 * @return the list of MaintenanceServiceOrder
	 * @throws GHAEJBException
	 */
	public List<MaintenanceServiceOrder> getAll() throws GHAEJBException;

	/**
	 * @param entity
	 * @return the saved MaintenanceServiceOrder
	 * @throws GHAEJBException
	 */
	public MaintenanceServiceOrder save(MaintenanceServiceOrder entity)
			throws GHAEJBException;

	/**
	 * @param entity
	 * @return the updated MaintenanceServiceOrder
	 * @throws GHAEJBException
	 */
	public MaintenanceServiceOrder update(MaintenanceServiceOrder entity)
			throws GHAEJBException;
}
