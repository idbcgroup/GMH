package org.fourgeeks.gha.domain.gar;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.fourgeeks.gha.domain.AbstractEntity;
import org.fourgeeks.gha.domain.ess.WorkingArea;

@Entity
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
	
	

}
