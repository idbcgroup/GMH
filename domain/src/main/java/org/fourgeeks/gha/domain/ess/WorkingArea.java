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
import org.fourgeeks.gha.domain.gar.Obu;

/**
 * @author emiliot, vivi.torresg
 * 
 */

@Entity
@NamedQueries(value = {
		@NamedQuery(name = "WorkingArea.getAll", query = "SELECT e from WorkingArea e order by e.name"),
		@NamedQuery(name = "WorkingArea.findByWorkingArea", query = "SELECT e from WorkingArea e where e like :workingArea order by e.name") })
public class WorkingArea extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@OneToOne
	@JoinColumn(name = "obuFk")
	private Obu obu;

	@ManyToOne
	@JoinColumn(name = "buildingLocationFk")
	private BuildingLocation buildingLocation;

	@ManyToOne
	@JoinColumn(name = "locationTypeFk")
	private LocationType locationType;

	private String name;

	/**
	 * 
	 */
	public WorkingArea() {
		// TODO Auto-generated constructor stub
	}

	public WorkingArea(long id) {
		this.id = id;
	}

	public Obu getObu() {
		return obu;
	}

	public void setObu(Obu obu) {
		this.obu = obu;
	}

	public BuildingLocation getBuildingLocation() {
		return buildingLocation;
	}

	public void setBuildingLocation(BuildingLocation buildingLocation) {
		this.buildingLocation = buildingLocation;
	}

	public LocationType getLocationType() {
		return locationType;
	}

	public void setLocationType(LocationType locationType) {
		this.locationType = locationType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
