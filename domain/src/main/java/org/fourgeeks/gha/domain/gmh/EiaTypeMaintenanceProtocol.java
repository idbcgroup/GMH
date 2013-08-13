/**
 * 
 */
package org.fourgeeks.gha.domain.gmh;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

import org.fourgeeks.gha.domain.AbstractEntity;

/**
 * @author emiliot
 * 
 */

@Entity
@NamedQueries(value = {
		@NamedQuery(name = "EiaTypeMaintenanceProtocol.getAll", 
				query = "SELECT e from EiaTypeMaintenanceProtocol e where e.parentProtocol IS NULL order by e.id"),
		@NamedQuery(name = "EiaTypeMaintenanceProtocol.findByEiaTypeMaintenancePlan", 
			query = "SELECT e from EiaTypeMaintenanceProtocol e where e.eiaTypeMaintenancePlan = :eiaTypeMaintenancePlan order by e.ordinal")}) 
public class EiaTypeMaintenanceProtocol extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "eiaTypeMaintenancePlanFk")
	private EiaTypeMaintenancePlan eiaTypeMaintenancePlan;

	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "parentProtocolFk")
	private EiaTypeMaintenanceProtocol parentProtocol;

	@OneToMany(mappedBy = "parentProtocol")
	private List<EiaTypeMaintenanceProtocol> childrenProtocols;
	
	@Column
	private int ordinal = 0;

	private String description;

	/**
	 * 
	 */
	public EiaTypeMaintenanceProtocol() {
		// TODO Auto-generated constructor stub
	}

	public EiaTypeMaintenancePlan geteMaintenancePlan() {
		return eiaTypeMaintenancePlan;
	}

	public EiaTypeMaintenanceProtocol getParentProtocol() {
		return parentProtocol;
	}

	public List<EiaTypeMaintenanceProtocol> getChildrenProtocols() {
		return childrenProtocols;
	}

	public int getOrdinal() {
		return ordinal;
	}

	public void setEiaTypeMaintenancePlan(
			EiaTypeMaintenancePlan eiaTypeMaintenancePlan) {
		this.eiaTypeMaintenancePlan = eiaTypeMaintenancePlan;
	}

	public void setParentProtocol(EiaTypeMaintenanceProtocol parentProtocol) {
		this.parentProtocol = parentProtocol;
	}

	public void setChildrenProtocols(
			List<EiaTypeMaintenanceProtocol> childrenProtocols) {
		this.childrenProtocols = childrenProtocols;
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
