/**
 * 
 */
package org.fourgeeks.gha.domain.ess;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;

import org.fourgeeks.gha.domain.AbstractEntity;
import org.fourgeeks.gha.domain.gar.BuildingLocation;

/**
 * @author emiliot
 *
 */

@Entity
@NamedQueries(value = { 
		@NamedQuery(name = "WorkingArea.getAll", 
				query = "SELECT e from WorkingArea e order by e.name")
})
public class WorkingArea extends AbstractEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@OneToOne
	@JoinColumn(name = "workingAreaBaseFk")
	private WorkingAreaBase wAreaBase;
	
	@ManyToOne
	@JoinColumn(name = "buildingLocationFk")
	private BuildingLocation buildingLocation;
	
	private String name;

	/**
	 * 
	 */
	public WorkingArea() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param wAreaBase
	 * @param buildingLocation
	 * @param name
	 */
	public WorkingArea(WorkingAreaBase wAreaBase,
			BuildingLocation buildingLocation, String name) {
		this.wAreaBase = wAreaBase;
		this.buildingLocation = buildingLocation;
		this.name = name;
	}

	public WorkingAreaBase getwAreaBase() {
		return wAreaBase;
	}

	public BuildingLocation getBuildingLocation() {
		return buildingLocation;
	}

	public String getName() {
		return name;
	}

	public void setwAreaBase(WorkingAreaBase wAreaBase) {
		this.wAreaBase = wAreaBase;
	}

	public void setBuildingLocation(BuildingLocation buildingLocation) {
		this.buildingLocation = buildingLocation;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
