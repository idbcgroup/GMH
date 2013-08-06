/**
 * 
 */
package org.fourgeeks.gha.domain.ess;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.fourgeeks.gha.domain.AbstractEntity;
import org.fourgeeks.gha.domain.gar.BuildingLocation;

/**
 * @author emiliot
 *
 */

@Entity
public class WorkingArea extends AbstractEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	@JoinColumn(name = "workingAreaBaseFk")
	private WorkingAreaBase wAreaBase;
	
	@ManyToOne
	@JoinColumn(name = "buildingLocationFk")
	private BuildingLocation buildingLocation;
}
