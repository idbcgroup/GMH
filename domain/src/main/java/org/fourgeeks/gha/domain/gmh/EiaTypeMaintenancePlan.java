/**
 * 
 */
package org.fourgeeks.gha.domain.gmh;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.fourgeeks.gha.domain.AbstractEntity;

/**
 * @author emiliot
 * 
 */

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = { "eiaTypeFk",
		"maintenancePlanFk" }))
@NamedQueries(value = {
		@NamedQuery(name = "EiaTypeMaintenancePlan.findByMaintenancePlan", query = "SELECT e from EiaTypeMaintenancePlan e WHERE e.maintenancePlan = :maintenancePlan ORDER BY e.id"),
		@NamedQuery(name = "EiaTypeMaintenancePlan.findByEiaType", query = "SELECT e from EiaTypeMaintenancePlan e WHERE e.eiaType = :eiaType ORDER BY e.id") })
public class EiaTypeMaintenancePlan extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "eiaTypeFk")
	private EiaType eiaType;

	@ManyToOne
	@JoinColumn(name = "maintenancePlanFk", columnDefinition = "bigint REFERENCES maintenanceplan(id) ON UPDATE CASCADE ON DELETE CASCADE")
	private MaintenancePlan maintenancePlan;

	/**
	 * 
	 */
	public EiaTypeMaintenancePlan() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param eiaType
	 * @param maintenancePlan
	 */
	public EiaTypeMaintenancePlan(EiaType eiaType,
			MaintenancePlan maintenancePlan) {
		this.eiaType = eiaType;
		this.maintenancePlan = maintenancePlan;
	}

	public EiaType getEiaType() {
		return eiaType;
	}

	public MaintenancePlan getMaintenancePlan() {
		return maintenancePlan;
	}

	public void setEiaType(EiaType eiaType) {
		this.eiaType = eiaType;
	}

	public void setMaintenancePlan(MaintenancePlan maintenancePlan) {
		this.maintenancePlan = maintenancePlan;
	}

}
