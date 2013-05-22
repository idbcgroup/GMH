package org.fourgeeks.gha.domain.gar;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.fourgeeks.gha.domain.AbstractEntity;

@Entity
public class BuildingLocationChild extends AbstractEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * this represents the parent BuildingLocation
	 */
	@ManyToOne
	@JoinColumn(name = "parentBuildingLocationFk")
	private BuildingLocation parentBuildingLoc;
	
	/**
	 * this represents the buildingLocation associated with this child buildingLocation
	 */
	@OneToOne
	@JoinColumn(name = "buildingLocationFk")
	private BuildingLocation buildingLocation;

}
