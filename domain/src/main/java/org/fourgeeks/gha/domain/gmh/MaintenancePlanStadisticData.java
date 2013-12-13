package org.fourgeeks.gha.domain.gmh;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import org.fourgeeks.gha.domain.enu.TimePeriodEnum;

/**
 * Class used to contain and send the stadistic data of a maintenancePlan from
 * the server to the client
 * 
 * @author naramirez
 */
public class MaintenancePlanStadisticData implements Serializable {
	private static final long serialVersionUID = 1L;

	private long numberActivities;
	private BigDecimal estimatedCost;
	private long estimatedDuration;
	private TimePeriodEnum pot;
	private long numberOfEias;
	private long timesEffectuated;
	private Timestamp lastTimeEffectuated;

	/** */
	public MaintenancePlanStadisticData() {
		pot = TimePeriodEnum.DAYS;
	}

	/**
	 * @return the numberActivities
	 */
	public long getNumberActivities() {
		return numberActivities;
	}

	/**
	 * @param numberActivities
	 *            the numberActivities to set
	 */
	public void setNumberActivities(long numberActivities) {
		this.numberActivities = numberActivities;
	}

	/**
	 * @return the estimatedCost
	 */
	public BigDecimal getEstimatedCost() {
		return estimatedCost;
	}

	/**
	 * @param estimatedCost
	 *            the estimatedCost to set
	 */
	public void setEstimatedCost(BigDecimal estimatedCost) {
		this.estimatedCost = estimatedCost;
	}

	/**
	 * @return the estimatedDuration
	 */
	public long getEstimatedDuration() {
		return estimatedDuration;
	}

	/**
	 * @param estimatedDuration
	 *            the estimatedDuration to set
	 */
	public void setEstimatedDuration(long estimatedDuration) {
		this.estimatedDuration = estimatedDuration;
	}

	/**
	 * @return the pot
	 */
	public TimePeriodEnum getPot() {
		return pot;
	}

	/**
	 * @param pot
	 *            the pot to set
	 */
	public void setPot(TimePeriodEnum pot) {
		this.pot = pot;
	}

	/**
	 * @return the numberOfEias
	 */
	public long getNumberOfEias() {
		return numberOfEias;
	}

	/**
	 * @param numberOfEias
	 *            the numberOfEias to set
	 */
	public void setNumberOfEias(long numberOfEias) {
		this.numberOfEias = numberOfEias;
	}

	/**
	 * @return the timesEffectuated
	 */
	public long getTimesEffectuated() {
		return timesEffectuated;
	}

	/**
	 * @param timesEffectuated
	 *            the timesEffectuated to set
	 */
	public void setTimesEffectuated(long timesEffectuated) {
		this.timesEffectuated = timesEffectuated;
	}

	/**
	 * @return the lastTimeEffectuated
	 */
	public Timestamp getLastTimeEffectuated() {
		return lastTimeEffectuated;
	}

	/**
	 * @param lastTimeEffectuated
	 *            the lastTimeEffectuated to set
	 */
	public void setLastTimeEffectuated(Timestamp lastTimeEffectuated) {
		this.lastTimeEffectuated = lastTimeEffectuated;
	}

}
