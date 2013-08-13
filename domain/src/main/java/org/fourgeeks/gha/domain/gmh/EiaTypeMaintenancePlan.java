/**
 * 
 */
package org.fourgeeks.gha.domain.gmh;

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
		@NamedQuery(name = "EiaTypeMaintenancePlan.getAll", query = "SELECT e from EiaTypeMaintenancePlan e order by e.id"),
		@NamedQuery(name = "EiaTypeMaintenancePlan.findByEiaType", query = "SELECT e from EiaTypeMaintenancePlan e where e.eiaType = :eiaType order by e.id") })
public class EiaTypeMaintenancePlan extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	@JoinColumn(name = "eiaTypeFk")
	private EiaType eiaType;
	
	private String description;

	/**
	 * 
	 */
	public EiaTypeMaintenancePlan() {
		// TODO Auto-generated constructor stub
	}

	public EiaType getEiaType() {
		return eiaType;
	}

	public String getDescription() {
		return description;
	}

	public void setEiaType(EiaType eiaType) {
		this.eiaType = eiaType;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
