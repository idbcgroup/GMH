package org.fourgeeks.gha.domain.gar;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.fourgeeks.gha.domain.AbstractEntity;
import org.fourgeeks.gha.domain.ess.WorkingArea;

@Entity
public class Waio extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "obuFk")
	private Obu obu;

	@ManyToOne
	@JoinColumn(name = "buildingLocationFk")
	private BuildingLocation buildingLocation;

	@ManyToOne
	@JoinColumn(name = "workingAreaFk")
	private WorkingArea workingArea;
}
