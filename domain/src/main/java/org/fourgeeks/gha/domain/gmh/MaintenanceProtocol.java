/**
 * 
 */
package org.fourgeeks.gha.domain.gmh;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import org.fourgeeks.gha.domain.AbstractEntity;

/**
 * @author emiliot
 * 
 */

@Entity
@NamedQueries(value = {
		@NamedQuery(name = "EiaTypeMaintenanceProtocol.getAll", query = "SELECT e from EiaTypeMaintenanceProtocol e where e.parentProtocol IS NULL order by e.id"),
		@NamedQuery(name = "EiaTypeMaintenanceProtocol.findByEiaTypeMaintenancePlan", query = "SELECT e from EiaTypeMaintenanceProtocol e where e.eiaTypeMaintenancePlan = :eiaTypeMaintenancePlan order by e.ordinal") })
public class MaintenanceProtocol extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "eiaTypeMaintenancePlanFk")
	private EiaTypeMaintenancePlan eiaTypeMaintenancePlan;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "parentProtocolFk")
	private MaintenanceProtocol parentProtocol;

	// @Column
	private int ordinal = 0;

	private String description;

	/**
	 * 
	 */
	public MaintenanceProtocol() {
		// TODO Auto-generated constructor stub
	}

	public EiaTypeMaintenancePlan geteMaintenancePlan() {
		return eiaTypeMaintenancePlan;
	}

	public MaintenanceProtocol getParentProtocol() {
		return parentProtocol;
	}

	public int getOrdinal() {
		return ordinal;
	}

	public void setEiaTypeMaintenancePlan(
			EiaTypeMaintenancePlan eiaTypeMaintenancePlan) {
		this.eiaTypeMaintenancePlan = eiaTypeMaintenancePlan;
	}

	public void setParentProtocol(MaintenanceProtocol parentProtocol) {
		this.parentProtocol = parentProtocol;
	}

	public void setOrdinal(int ordinal) {
		this.ordinal = ordinal;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
