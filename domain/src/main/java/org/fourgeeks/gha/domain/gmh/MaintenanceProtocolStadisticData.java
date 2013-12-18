package org.fourgeeks.gha.domain.gmh;

import java.io.Serializable;
import java.math.BigDecimal;

import org.fourgeeks.gha.domain.enu.CurrencyTypeEnum;
import org.fourgeeks.gha.domain.enu.TimePeriodEnum;

/**
 * Class used to contain and send the stadistic data of a maintenancePlan from
 * the server to the client
 * 
 * @author naramirez
 */
public class MaintenanceProtocolStadisticData implements Serializable {
	private static final long serialVersionUID = 1L;

	private BigDecimal estimatedCost;
	private long estimatedDuration;
	private long numberActivities;
	private long numberSubProtocols;
	private long numberSubProtocolsActivities;
	private TimePeriodEnum pot;
	private CurrencyTypeEnum currency;

	/** */
	public MaintenanceProtocolStadisticData() {
		pot = TimePeriodEnum.DAYS;
	}

	/**
	 * @return the estimatedCost
	 */
	public BigDecimal getEstimatedCost() {
		return estimatedCost;
	}

	/**
	 * @return the estimatedDuration
	 */
	public long getEstimatedDuration() {
		return estimatedDuration;
	}

	/**
	 * @return the numberActivities
	 */
	public long getNumberActivities() {
		return numberActivities;
	}

	/**
	 * 
	 * @return the number of sub-protocols
	 */
	public long getNumberSubProtocols() {
		return numberSubProtocols;
	}

	/**
	 * 
	 * @return the number of subprotocol's activities
	 */
	public long getNumberSubProtocolsActivities() {
		return numberSubProtocolsActivities;
	}

	/**
	 * @return the pot
	 */
	public TimePeriodEnum getPot() {
		return pot;
	}

	/**
	 * @param estimatedCost
	 *            the estimatedCost to set
	 */
	public void setEstimatedCost(BigDecimal estimatedCost) {
		this.estimatedCost = estimatedCost;
	}

	/**
	 * @param estimatedDuration
	 *            the estimatedDuration to set
	 */
	public void setEstimatedDuration(long estimatedDuration) {
		this.estimatedDuration = estimatedDuration;
	}

	/**
	 * @param numberActivities
	 *            the numberActivities to set
	 */
	public void setNumberActivities(long numberActivities) {
		this.numberActivities = numberActivities;
	}

	/**
	 * @param numberSubProtocols
	 *            the number of sub-protocols to set
	 */
	public void setNumberSubProtocols(long numberSubProtocols) {
		this.numberSubProtocols = numberSubProtocols;
	}

	/**
	 * @param numberSubProtocolsActivities
	 *            the number of sub-protocol's activities to set
	 */
	public void setNumberSubProtocolsActivities(
			long numberSubProtocolsActivities) {
		this.numberSubProtocolsActivities = numberSubProtocolsActivities;
	}

	/**
	 * @param pot
	 *            the pot to set
	 */
	public void setPot(TimePeriodEnum pot) {
		this.pot = pot;
	}

	/**
	 * @return the currency of the estimated cost
	 */
	public CurrencyTypeEnum getCurrency() {
		return currency;
	}

	/**
	 * @param currency
	 *            the currency of the estimated cost to set
	 */
	public void setCurrency(CurrencyTypeEnum currency) {
		this.currency = currency;
	}

}
