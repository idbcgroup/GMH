package org.fourgeeks.gha.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.fourgeeks.gha.domain.enu.ActivityState;
import org.fourgeeks.gha.domain.enu.CurrencyTypeEnum;
import org.fourgeeks.gha.domain.enu.TimePeriodEnum;

/**
 * @author alacret
 * 
 */
@Entity
public class Activity extends AbstractEntity {
	/** */
	private static final long serialVersionUID = 1L;

	@NotNull(message = "name-activity-not-null")
	@Column(nullable = false)
	private String name;

	@NotNull(message = "activity-state-not-null")
	@Column(nullable = false)
	private ActivityState state;

	@ManyToOne
	@JoinColumn(name = "activityTypeFk")
	private ActivityType type;

	@ManyToOne
	@JoinColumn(name = "activitySubTypeFk")
	private ActivityType subType;

	private BigDecimal estimatedDuration;
	private TimePeriodEnum estimatedDurationPoT;

	private BigDecimal estimatedCost;
	private CurrencyTypeEnum estimatedCostCurrency;

	@Column(nullable = false)
	private boolean isSubProtocol;

	private String description;
	private String instructionsAndObservations;

	/** */
	public Activity() {
		isSubProtocol = false;
	}

	/**
	 * @return the description of the maintenance activity
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @return the estimated cost of the maintenance activity
	 */
	public BigDecimal getEstimatedCost() {
		return estimatedCost;
	}

	/**
	 * @return the estimatedCostCurrency
	 */
	public CurrencyTypeEnum getEstimatedCostCurrency() {
		return estimatedCostCurrency;
	}

	/**
	 * @return the value that represent the estimated duration in a period of
	 *         time of the maintenance activity
	 */
	public BigDecimal getEstimatedDuration() {
		return estimatedDuration;
	}

	/**
	 * @return the estimated duration period of time of the maintenance activity
	 */
	public TimePeriodEnum getEstimatedDurationPoT() {
		return estimatedDurationPoT;
	}

	/**
	 * @return the instructions and observations of the maintenance activity
	 */
	public String getInstructionsAndObservations() {
		return instructionsAndObservations;
	}

	/**
	 * @return the isSubProtocol
	 */
	public boolean getIsSubProtocol() {
		return isSubProtocol;
	}

	/**
	 * @return the name of the activity
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the state of the maintenance activity
	 */
	public ActivityState getState() {
		return state;
	}

	/**
	 * @return the subType
	 */
	public ActivityType getSubType() {
		return subType;
	}

	/**
	 * @return the type
	 */
	public ActivityType getType() {
		return type;
	}

	/**
	 * @param description
	 *            the description of the maintenance activity
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @param estimatedCost
	 *            the estimated cost of the maintenance activity
	 */
	public void setEstimatedCost(BigDecimal estimatedCost) {
		this.estimatedCost = estimatedCost;
	}

	/**
	 * @param estimatedCostCurrency
	 *            the estimated cost currency of the maintenance activity
	 */
	public void setEstimatedCostCurrency(CurrencyTypeEnum estimatedCostCurrency) {
		this.estimatedCostCurrency = estimatedCostCurrency;
	}

	/**
	 * @param estimatedDuration
	 *            the value that represent the estimated duration in a period of
	 *            time of the maintenance activity
	 */
	public void setEstimatedDuration(BigDecimal estimatedDuration) {
		this.estimatedDuration = estimatedDuration;
	}

	/**
	 * @param estimatedDurationPoT
	 *            the estimated duration period of time of the maintenance
	 *            activity
	 */
	public void setEstimatedDurationPoT(TimePeriodEnum estimatedDurationPoT) {
		this.estimatedDurationPoT = estimatedDurationPoT;
	}

	/**
	 * @param instructionsAndObservations
	 *            the instructions and observations the maintenance activity
	 */
	public void setInstructionsAndObservations(
			String instructionsAndObservations) {
		this.instructionsAndObservations = instructionsAndObservations;
	}

	/**
	 * @param isSubProtocol
	 *            the isSubProtocol to set
	 */
	public void setIsSubProtocol(boolean isSubProtocol) {
		this.isSubProtocol = isSubProtocol;
	}

	/**
	 * 
	 * @param name
	 *            the name of the activity
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param state
	 *            the state of the maintenance activity
	 */
	public void setState(ActivityState state) {
		this.state = state;
	}

	/**
	 * @param subType
	 *            the subType to set
	 */
	public void setSubType(ActivityType subType) {
		this.subType = subType;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(ActivityType type) {
		this.type = type;
	}

}
