package org.fourgeeks.gha.domain.gar;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;

import org.fourgeeks.gha.domain.AbstractEntity;
import org.fourgeeks.gha.domain.ess.WorkingArea;

@Entity
@NamedQueries(value = { @NamedQuery(name = "Facility.getAll", query = "SELECT e from Facility e order by e.name") })
public class Facility extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@OneToOne
	@JoinColumn(name = "buildingLocationFk", nullable = false)
	private BuildingLocation buildingLocation;

	@ManyToOne
	@JoinColumn(name = "workingAreaFk")
	private WorkingArea workingArea;

	private String name;

	/**
	 * 
	 */
	public Facility() {
		// TODO Auto-generated constructor stub
	}

	public BuildingLocation getBuildingLocation() {
		return buildingLocation;
	}

	public WorkingArea getWorkingArea() {
		return workingArea;
	}

	public void setBuildingLocation(BuildingLocation buildingLocation) {
		this.buildingLocation = buildingLocation;
	}

	public void setWorkingArea(WorkingArea workingArea) {
		this.workingArea = workingArea;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
