/**
 * 
 */
package org.fourgeeks.gha.domain.gmh;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.fourgeeks.gha.domain.AbstractEntity;
import org.fourgeeks.gha.domain.enu.MaintenancePlanState;
import org.fourgeeks.gha.domain.enu.MaintenancePlanType;
import org.fourgeeks.gha.domain.enu.TimePeriodEnum;

/**
 * @author emiliot
 * 
 */

@Entity
@NamedQueries(value = {
		@NamedQuery(name = "MaintenancePlan.getAll", query = "SELECT e from MaintenancePlan e order by e.id"),
		@NamedQuery(name = "MaintenancePlan.findByEiaType", query = "SELECT mnt from EiaTypeMaintenancePlan e JOIN e.maintenancePlan mnt WHERE e.eiaType = :eiaType order by e.id") })
public class MaintenancePlan extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Size(max = 100)
	@NotNull(message = "name-not-null")
	@Column(nullable = false)
	private String name;
	private String description;
	// @Size(max = 3)
	@NotNull(message = "frecuency-not-null")
	@Column(nullable = false)
	private int frequency;
	@NotNull(message = "time-period-not-null")
	@Column(nullable = false)
	private TimePeriodEnum pot;
	@NotNull(message = "type-not-null")
	@Column(nullable = false)
	private MaintenancePlanType type;
	@NotNull(message = "state-not-null")
	@Column(nullable = false)
	private MaintenancePlanState state;

	/**
	 * 
	 */
	public MaintenancePlan() {
	}

	public MaintenancePlanType getType() {
		return type;
	}

	public void setType(MaintenancePlanType type) {
		this.type = type;
	}

	public MaintenancePlanState getState() {
		return state;
	}

	public void setState(MaintenancePlanState state) {
		this.state = state;
	}

	/**
	 * @param name
	 * @param description
	 * @param frequency
	 * @param pot
	 */
	public MaintenancePlan(String name, String description, int frequency,
			TimePeriodEnum pot, MaintenancePlanType type,
			MaintenancePlanState state) {
		this.name = name;
		this.description = description;
		this.frequency = frequency;
		this.pot = pot;
		this.type = type;
		this.state = state;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getFrequency() {
		return frequency;
	}

	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}

	public TimePeriodEnum getPot() {
		return pot;
	}

	public void setPot(TimePeriodEnum pot) {
		this.pot = pot;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
