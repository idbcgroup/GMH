package org.fourgeeks.gha.ejb.gmh;

import java.math.BigDecimal;

import javax.ejb.Local;

import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.gmh.MaintenanceActivity;

/**
 * @author naramirez
 */
@Local
public interface MaintenanceSubProtocolServiceLocal {

	/**
	 * Return the number of {@link MaintenanceActivity} entities that are
	 * associated to this {@link MaintenanceActivity} sub-protocol
	 * 
	 * @param activity
	 *            the given {@link MaintenanceActivity}
	 * @return the number of {@link MaintenanceActivity}
	 * @throws GHAEJBException
	 */
	public Long getSubProtocolActivitiesCount(MaintenanceActivity activity)
			throws GHAEJBException;

	/**
	 * Return the estimated cost of the activities that conform the subprotocol
	 * activity
	 * 
	 * @param activity
	 *            the activity sub-protocol
	 * @return the estimated cost
	 * @throws GHAEJBException
	 */
	public BigDecimal getSubProtocolCost(MaintenanceActivity activity)
			throws GHAEJBException;

	/**
	 * Return the estimated duration of the activities that conform the
	 * subprotocol activity
	 * 
	 * @param activity
	 *            the activity sub-protocol
	 * @return the estimated cost
	 * @throws GHAEJBException
	 */
	public int getSubProtocolDuration(MaintenanceActivity activity)
			throws GHAEJBException;
}
