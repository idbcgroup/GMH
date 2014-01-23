package org.fourgeeks.gha.domain.gmh;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.fourgeeks.gha.domain.AbstractEntity;
import org.fourgeeks.gha.domain.enu.ActivityCategoryEnum;
import org.fourgeeks.gha.domain.enu.ActivityState;
import org.fourgeeks.gha.domain.enu.ActivitySubCategoryEnum;
import org.fourgeeks.gha.domain.enu.CurrencyTypeEnum;
import org.fourgeeks.gha.domain.enu.TimePeriodEnum;

/**
 * @author alacret
 * 
 */
@Entity
@NamedQueries(value = {
		@NamedQuery(name = "MaintenanceActivity.getAll", query = "SELECT e from MaintenanceActivity e order by e.id"),
		@NamedQuery(name = "MaintenanceActivity.findByMaintenancePlan", query = "SELECT act FROM MaintenanceProtocols mp JOIN mp.maintenanceActivity act WHERE mp.maintenancePlan = :plan order by act.id"),
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
	private ActivityState state;

	@NotNull(message = "type-not-null")
	@Column(nullable = false)
	private ActivityCategoryEnum category;

	@NotNull(message = "sub-type-not-null")
	@Column(nullable = false)
	private ActivitySubCategoryEnum subCategory;

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

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(MaintenanceActivity activity) {
		if (activity.getId() > this.getId())
			return 1;
		if (activity.getId() < this.getId())
			return -1;
		return 0;
	}

	/**
	 * @return the type of the maintenance activity
	 */
	public ActivityCategoryEnum getCategory() {
		return category;
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
	 * @return true if Work Equips are required for this activity, false in
	 *         other case
	 */
	public boolean getIsEquipsRequired() {
		return equipsRequired;
	}

	/**
	 * @return true if Materials are required for this activity, false in other
	 *         case
	 */
	public boolean getIsMaterialsRequired() {
		return materialsRequired;
	}

	/**
	 * @return the isSubProtocol
	 */
	public boolean getIsSubProtocol() {
		return isSubProtocol;
	}

	/**
	 * @return true if Tools are required for this activity, false in other case
	 */
	public boolean getIsToolsRequired() {
		return toolsRequired;
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
	public ActivityState getState() {
		return state;
	}

	/**
	 * @return the subcategory of the maintenance activity
	 */
	public ActivitySubCategoryEnum getSubCategory() {
		return subCategory;
	}

	/**
	 * @param category
	 *            the category of the maintenance activity
	 */
	public void setCategory(ActivityCategoryEnum category) {
		this.category = category;
	}

	/**
	 * @param description
	 *            the description of the maintenance activity
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @param equipsRequired
	 *            Are Work Equips required for this activity?
	 */
	public void setEquipsRequired(boolean equipsRequired) {
		this.equipsRequired = equipsRequired;
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
	 * @param materialsRequired
	 *            Are Materials required for this activity?
	 */
	public void setMaterialsRequired(boolean materialsRequired) {
		this.materialsRequired = materialsRequired;
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
	public void setState(ActivityState state) {
		this.state = state;
	}

	/**
	 * @param subCategory
	 *            the subcategory of the maintenance activity
	 */
	public void setSubCategory(ActivitySubCategoryEnum subCategory) {
		this.subCategory = subCategory;
	}

	/**
	 * @param toolsRequired
	 *            Are Tools required for this activity?
	 */
	public void setToolsRequired(boolean toolsRequired) {
		this.toolsRequired = toolsRequired;
	}
}
