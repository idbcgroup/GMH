package org.fourgeeks.gha.domain.gar;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.fourgeeks.gha.domain.AbstractEntity;

@Entity
public class Facility extends AbstractEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	@JoinColumn(name = "waioFk")
	private Waio waio;
	
//	@OneToMany(mappedBy = "facility")
//	private Collection <Eia> equipments;
	
	@OneToOne
	@JoinColumn(name = "resourceServiceFk")
	private ResourceService resourceService;
	
	@ManyToOne
	@JoinColumn(name = "buildingLocationFk")
	private BuildingLocation buildingLocation;

	/**
	 * 
	 */
	public Facility() {
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Waio getWaio() {
		return waio;
	}

//	public Collection<Eia> getEquipments() {
//		return equipments;
//	}

	public ResourceService getResourceService() {
		return resourceService;
	}

	public BuildingLocation getBuildingLocation() {
		return buildingLocation;
	}

	public void setWaio(Waio waio) {
		this.waio = waio;
	}

//	public void setEquipments(Collection<Eia> equipments) {
//		this.equipments = equipments;
//	}

	public void setResourceService(ResourceService resourceService) {
		this.resourceService = resourceService;
	}

	public void setBuildingLocation(BuildingLocation buildingLocation) {
		this.buildingLocation = buildingLocation;
	}
	
	

}
