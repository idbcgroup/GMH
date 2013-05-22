package org.fourgeeks.gha.domain.gmh;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.fourgeeks.gha.domain.AbstractEntity;
import org.fourgeeks.gha.domain.gar.BuildingLocation;
import org.fourgeeks.gha.domain.gar.Waio;

@Entity
public class Terminal extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	@JoinColumn(name = "buildingLocationFk")
	private BuildingLocation buildingLocation;
	
	@ManyToOne
	@JoinColumn(name = "waioFk")
	private Waio waio;
	
	@OneToOne
	@JoinColumn(name = "equipmentFk")
	private Equipment equipment;

}
