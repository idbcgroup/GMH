package org.fourgeeks.gha.ejb.gmh;

import java.util.List;

import javax.ejb.Remote;

import org.fourgeeks.gha.domain.ess.MaintenanceServiceOrder;
import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.gmh.EiaCorrectiveMaintenance;
import org.fourgeeks.gha.domain.gmh.EiaMaintenance;
import org.fourgeeks.gha.domain.gmh.EiaPreventiveMaintenance;
import org.fourgeeks.gha.domain.gmh.EiaType;

/**
 * @author naramirez
 */
@Remote
public interface EiaMaintenanceServiceRemote {
	/**
	 * @param eiaType
	 * @return A list of maintenance (corrective and preventive) for the eias of
	 *         a eiaType
	 * @throws GHAEJBException
	 */
	public List<EiaMaintenance> find(EiaType eiaType) throws GHAEJBException;

	/**
	 * @param eiaMaintenance
	 *            the {@link EiaMaintenance} that have the service order
	 * @return the MaintenanceServiceOrder associated with this
	 *         {@link EiaMaintenance}
	 * @throws GHAEJBException
	 */
	public MaintenanceServiceOrder findServiceOrder(
			EiaMaintenance eiaMaintenance) throws GHAEJBException;

	/**
	 * Save a new corrective maintenace in the database
	 * 
	 * @param entity
	 *            the corrective maintenace to save
	 * @return the saved corrective maintenace
	 * @throws GHAEJBException
	 */
	public EiaCorrectiveMaintenance saveCorrectiveMaintenance(
			EiaCorrectiveMaintenance entity) throws GHAEJBException;

	/**
	 * Save a new preventive maintenace in the database
	 * 
	 * @param entity
	 *            the preventive maintenace to save
	 * @return the saved preventive maintenace
	 * @throws GHAEJBException
	 */
	public EiaPreventiveMaintenance savePreventiveMaintenance(
			EiaPreventiveMaintenance entity) throws GHAEJBException;

	/**
	 * Update a preventive maintenace instance
	 * 
	 * @param entity
	 * @return the updated preventive maintenace instance
	 * @throws GHAEJBException
	 */
	public EiaPreventiveMaintenance updatePreventiveMaintenance(
			EiaPreventiveMaintenance entity) throws GHAEJBException;

	/**
	 * Update a corrective maintenace instance
	 * 
	 * @param entity
	 * @return the updated corrective maintenace instance
	 * @throws GHAEJBException
	 */
	public EiaCorrectiveMaintenance updateCorrectiveMaintenance(
			EiaCorrectiveMaintenance entity) throws GHAEJBException;

	/**
	 * Delete a corrective maintenance
	 * 
	 * @param Id
	 * @throws GHAEJBException
	 */
	public void deleteCorrectiveMaintenance(long Id) throws GHAEJBException;

	/**
	 * Delete a preventive maintenance
	 * 
	 * @param Id
	 * @throws GHAEJBException
	 */
	public void deletePreventiveMaintenance(long Id) throws GHAEJBException;
}
