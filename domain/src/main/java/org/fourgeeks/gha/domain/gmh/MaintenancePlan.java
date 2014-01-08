/**
 * 
 */
package org.fourgeeks.gha.domain.gmh;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.fourgeeks.gha.domain.AbstractEntity;
import org.fourgeeks.gha.domain.enu.MaintenancePlanCancelationOption;
import org.fourgeeks.gha.domain.enu.MaintenancePlanState;
import org.fourgeeks.gha.domain.enu.MaintenancePlanType;
import org.fourgeeks.gha.domain.enu.TimePeriodEnum;
import org.fourgeeks.gha.domain.gar.Job;
import org.fourgeeks.gha.domain.glm.ExternalProvider;

/**
 * @author emiliot
 * 
 */

@Entity
@NamedQueries(value = {
		@NamedQuery(name = "MaintenancePlan.getAll", query = "SELECT e from MaintenancePlan e order by e.id"),
		@NamedQuery(name = "MaintenancePlan.findByEiaType", query = "SELECT mnt from EiaTypeMaintenancePlan e JOIN e.maintenancePlan mnt WHERE e.eiaType = :eiaType order by e.id"),
		@NamedQuery(name = "MaintenancePlan.findEiaByMaintenancePlan", query = "SELECT pmp.planification FROM EiaPreventiveMaintenancePlanification pmp JOIN pmp.plan mp WHERE mp.maintenancePlan = :plan") })
public class MaintenancePlan extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Size(max = 100)
	@NotNull(message = "name-not-null")
	@Column(nullable = false)
	private String name;
	@NotNull(message = "frecuency-not-null")
	@Column(nullable = false)
	private Integer frequency;
	@NotNull(message = "time-period-not-null")
	@Column(nullable = false)
	private TimePeriodEnum pot;
	@NotNull(message = "type-not-null")
	@Column(nullable = false)
	private MaintenancePlanType type;
	@NotNull(message = "state-not-null")
	@Column(nullable = false)
	private MaintenancePlanState state;
	@NotNull(message = "cancelation-option-not-null")
	@Column(nullable = false)
	private MaintenancePlanCancelationOption cancelationOption;
	@ManyToOne
	@JoinColumn(name = "providerFk")
	private ExternalProvider provider;
	@ManyToOne
	@JoinColumn(name = "roleFk")
	private Job role;

	private String description;

	/** */
	public MaintenancePlan() {
	}

	/**
	 * @param name
	 * @param description
	 * @param frequency
	 * @param pot
	 * @param type
	 * @param state
	 * @param cancelationOption
	 */
	public MaintenancePlan(String name, String description, Integer frequency,
			TimePeriodEnum pot, MaintenancePlanType type,
			MaintenancePlanState state,
			MaintenancePlanCancelationOption cancelationOption) {
		this.name = name;
		this.description = description;
		this.frequency = frequency;
		this.pot = pot;
		this.type = type;
		this.state = state;
		this.cancelationOption = cancelationOption;
	}

	/**
	 * @return the cancelationOption
	 */
	public MaintenancePlanCancelationOption getCancelationOption() {
		return cancelationOption;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @return the frecuency the plan is executed
	 */
	public Integer getFrequency() {
		return frequency;
	}

	/**
	 * @return the name of the plan
	 */
	public String getName() {
		return name;
	}

	/**
	 * 
	 * @return the period of time the plan is executed
	 */
	public TimePeriodEnum getPot() {
		return pot;
	}

	/**
	 * @return the provider
	 */
	public ExternalProvider getProvider() {
		return provider;
	}

	/**
	 * @return the role
	 */
	public Job getRole() {
		return role;
	}

	/**
	 * 
	 * @return the maintanance plan state
	 */
	public MaintenancePlanState getState() {
		return state;
	}

	/**
	 * @return the maintenance plan type
	 */
	public MaintenancePlanType getType() {
		return type;
	}

	/**
	 * @param cancelationOption
	 *            the cancelationOption to set
	 */
	public void setCancelationOption(
			MaintenancePlanCancelationOption cancelationOption) {
		this.cancelationOption = cancelationOption;
	}

	/**
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @param frequency
	 */
	public void setFrequency(Integer frequency) {
		this.frequency = frequency;
	}

	/**
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param pot
	 */
	public void setPot(TimePeriodEnum pot) {
		this.pot = pot;
	}

	/**
	 * @param provider
	 *            the provider to set
	 */
	public void setProvider(ExternalProvider provider) {
		this.provider = provider;
	}

	/**
	 * @param role
	 *            the role to set
	 */
	public void setRole(Job role) {
		this.role = role;
	}

	/**
	 * @param state
	 */
	public void setState(MaintenancePlanState state) {
		this.state = state;
	}

	/**
	 * @param type
	 */
	public void setType(MaintenancePlanType type) {
		this.type = type;
	}

}
