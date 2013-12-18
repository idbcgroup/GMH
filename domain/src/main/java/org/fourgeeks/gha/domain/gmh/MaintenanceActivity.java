package org.fourgeeks.gha.domain.gmh;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.fourgeeks.gha.domain.AbstractEntity;
import org.fourgeeks.gha.domain.enu.CurrencyTypeEnum;
import org.fourgeeks.gha.domain.enu.MaintenanceActivityState;
import org.fourgeeks.gha.domain.enu.MaintenanceActivitySubTypeEnum;
import org.fourgeeks.gha.domain.enu.MaintenanceActivityTypeEnum;
import org.fourgeeks.gha.domain.enu.TimePeriodEnum;

/**
 * @author alacret
 * 
 */
@Entity
@NamedQueries(value = {
		@NamedQuery(name = "MaintenanceActivity.getAll", query = "SELECT e from MaintenanceActivity e order by e.id"),
		@NamedQuery(name = "MaintenanceActivity.findByMaintenanceProtocol", query = "SELECT a FROM MaintenanceActivityMaintenanceProtocol e JOIN e.activity a WHERE e.protocol = :maintenanceProtocol order by e.ordinal"),
		@NamedQuery(name = "MaintenanceActivity.findByServiceResource", query = "SELECT p FROM  MaintenanceActivityServiceResource e JOIN e.maintenanceActivity p WHERE e.serviceResource = :serviceResource") })
public class MaintenanceActivity extends AbstractEntity implements
		Comparable<MaintenanceActivity> {
	/** */
	private static final long serialVersionUID = 1L;

	@Size(max = 100)
	@NotNull(message = "name-not-null")
	@Column(nullable = false)
	private String name;

	@NotNull(message = "maintenance-activity-state-not-null")
	@Column(nullable = false)
	private MaintenanceActivityState state;

	@NotNull(message = "type-not-null")
	@Column(nullable = false)
	private MaintenanceActivityTypeEnum type;

	@NotNull(message = "sub-type-not-null")
	@Column(nullable = false)
	private MaintenanceActivitySubTypeEnum subType;

	@NotNull(message = "estimated-duration-time-not-null")
	@Column(nullable = false)
	private BigDecimal estimatedDuration;

	@NotNull(message = "time-period-not-null")
	@Column(nullable = false)
	private TimePeriodEnum estimatedDurationPoT;

	@NotNull(message = "estimated-cost-not-null")
	@Column(nullable = false)
	private BigDecimal estimatedCost;

	@NotNull(message = "currency-not-null")
	@Column(nullable = false)
	private CurrencyTypeEnum estimatedCostCurrency;

	@Column(nullable = false)
	private boolean isSubProtocol;

	private String description;
	private String instructionsAndObservations;
	private boolean materialsRequired;
	private boolean toolsRequired;
	private boolean equipsRequired;

	/** */
	public MaintenanceActivity() {
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
	 * @return the name of the maintenance activity
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the state of the maintenance activity
	 */
	public MaintenanceActivityState getState() {
		return state;
	}

	/**
	 * @return the sub-type or sub-category of the maintenance activity
	 */
	public MaintenanceActivitySubTypeEnum getSubType() {
		return subType;
	}

	/**
	 * @return the type of the maintenance activity
	 */
	public MaintenanceActivityTypeEnum getType() {
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
	 * @param name
	 *            the name of the maintenance activity
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param state
	 *            the state of the maintenance activity
	 */
	public void setState(MaintenanceActivityState state) {
		this.state = state;
	}

	/**
	 * @param subType
	 *            the sub-type or sub-category of the maintenance activity
	 */
	public void setSubType(MaintenanceActivitySubTypeEnum subType) {
		this.subType = subType;
	}

	/**
	 * @param type
	 *            the type or category of the maintenance activity
	 */
	public void setType(MaintenanceActivityTypeEnum type) {
		this.type = type;
	}

	/**
	 * @return the isSubProtocol
	 */
	public boolean getIsSubProtocol() {
		return isSubProtocol;
	}

	/**
	 * @param isSubProtocol
	 *            the isSubProtocol to set
	 */
	public void setIsSubProtocol(boolean isSubProtocol) {
		this.isSubProtocol = isSubProtocol;
	}

	/**
	 * @return true if Materials are required for this activity, false in other
	 *         case
	 */
	public boolean getIsMaterialsRequired() {
		return materialsRequired;
	}

	/**
	 * @param materialsRequired
	 *            Are Materials required for this activity?
	 */
	public void setMaterialsRequired(boolean materialsRequired) {
		this.materialsRequired = materialsRequired;
	}

	/**
	 * @return true if Tools are required for this activity, false in other case
	 */
	public boolean getIsToolsRequired() {
		return toolsRequired;
	}

	/**
	 * @param toolsRequired
	 *            Are Tools required for this activity?
	 */
	public void setToolsRequired(boolean toolsRequired) {
		this.toolsRequired = toolsRequired;
	}

	/**
	 * @return true if Work Equips are required for this activity, false in
	 *         other case
	 */
	public boolean getIsEquipsRequired() {
		return equipsRequired;
	}

	/**
	 * @param equipsRequired
	 *            Are Work Equips required for this activity?
	 */
	public void setEquipsRequired(boolean equipsRequired) {
		this.equipsRequired = equipsRequired;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(MaintenanceActivity activity) {
		if (activity.getId() != this.getId())
			return 1;
		return 0;
	}
}
