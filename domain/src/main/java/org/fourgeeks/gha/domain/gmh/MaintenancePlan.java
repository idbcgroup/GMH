/**
 * 
 */
package org.fourgeeks.gha.domain.gmh;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import org.fourgeeks.gha.domain.AbstractEntity;
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
	
	private String description;
	private int frequency;
	private TimePeriodEnum pot;

	/**
	 * 
	 */
	public MaintenancePlan() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param description
	 * @param frequency
	 * @param pot
	 */
	public MaintenancePlan(String description, int frequency, TimePeriodEnum pot) {
		this.description = description;
		this.frequency = frequency;
		this.pot = pot;
	}

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
	
	
}
