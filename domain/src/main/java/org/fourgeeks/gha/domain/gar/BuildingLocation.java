package org.fourgeeks.gha.domain.gar;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.fourgeeks.gha.domain.AbstractEntity;
import org.fourgeeks.gha.domain.mix.Bpi;

@Entity
public class BuildingLocation extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "bpiFk")
	private Bpi bpi;

	/**
	 * This represents the children of this building location
	 */
	// @OneToMany(mappedBy = "parentBuildingLoc")
	// private Collection <BuildingLocationChild> buildingLocationChildren;

	/**
	 * This represents the link relation to my parent (if any), semantically it
	 * says who is my buildingLocationChild to refer to my buildingLocation
	 * Parent
	 */
	@OneToOne(mappedBy = "buildingLocation")
	private BuildingLocationChild buildingLocationChild;

	// @OneToMany(mappedBy = "buildingLocation")
	// private Collection <Waio> waios;

	// @OneToMany(mappedBy = "buildingLocation")
	// private Collection <Terminal> terminals;

	// @OneToMany(mappedBy = "buildingLocation")
	// private Collection <Eia> equipments;
}
