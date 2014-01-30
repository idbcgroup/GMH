package org.fourgeeks.gha.ejb.gmh;

import java.math.BigDecimal;

import javax.ejb.Local;

import org.fourgeeks.gha.domain.Activity;
import org.fourgeeks.gha.domain.exceptions.GHAEJBException;

/**
 * @author naramirez
 */
@Local
public interface SubProtocolAndCheklistServiceLocal {

	/**
	 * Return the number of {@link Activity} entities that are associated to
	 * this {@link Activity} sub-protocol
	 * 
	 * @param activity
	 *            the given {@link Activity}
	 * @return the number of {@link Activity}
	 * @throws GHAEJBException
	 */
	public Long getSubProtocolActivitiesCount(Activity activity)
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
	public BigDecimal getSubProtocolCost(Activity activity)
			throws GHAEJBException;

	/**
	 * Return the estimated duration in days of the activities that conform the
	 * subprotocol activity
	 * 
	 * @param activity
	 *            the activity sub-protocol
	 * @return the estimated cost
	 * @throws GHAEJBException
	 */
	public int getSubProtocolDuration(Activity activity) throws GHAEJBException;
}
