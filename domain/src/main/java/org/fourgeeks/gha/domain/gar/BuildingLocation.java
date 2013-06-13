package org.fourgeeks.gha.domain.gar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.fourgeeks.gha.domain.AbstractEntity;
import org.fourgeeks.gha.domain.enu.LocationLevelEnum;
import org.fourgeeks.gha.domain.mix.Bpi;

@Entity
public class BuildingLocation extends AbstractEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	@JoinColumn(name = "bpiFk", nullable=false)
	private Bpi bpi;
	
	/**
	 * This represents the children of this building location
	 */
//	@OneToMany(mappedBy = "parentBuildingLoc")
//	private Collection <BuildingLocationChild> buildingLocationChildren;
	
	/**
	 * This represents the link relation to my parent (if any),
	 * semantically it says who is my buildingLocationChild to refer to my buildingLocation
	 * Parent
	 */
//	@OneToOne(mappedBy = "buildingLocation")
//	private BuildingLocationChild buildingLocationChild;
	
//	@OneToMany(mappedBy = "buildingLocation")
//	private Collection <Waio> waios;
	
//	@OneToMany(mappedBy = "buildingLocation")
//	private Collection <Terminal> terminals;
	
//	@OneToMany(mappedBy = "buildingLocation")
//	private Collection <Eia> equipments;
	
	/**Attributes*/
	
	@Column(nullable=false)
	private String code; /** Código Ubicación en Edificio length =20 */
	
	@Column(nullable=false)
	private LocationLevelEnum locationLevel; /** Nivel de la Ubicación en Edificio definida length =60 */
	private String locationName; /** Nombre de Ubicación en Edificio length =255 */
	private String description; /** Descripción de Ubicación en Edificio length =255 */
	
	@Column(nullable=false)
	private int units; /** Unidades disponibles en Ubicación en Edificio (Pisos, Habitaciones, etc) length =4 */

	/**
	 * 
	 */
	public BuildingLocation() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param bpi
	 * @param code
	 * @param locationLevel
	 * @param units
	 */
	public BuildingLocation(Bpi bpi, String code,
			LocationLevelEnum locationLevel, int units) {
		super();
		this.bpi = bpi;
		this.code = code;
		this.locationLevel = locationLevel;
		this.units = units;
	}

	public Bpi getBpi() {
		return bpi;
	}

	public String getCode() {
		return code;
	}

	public LocationLevelEnum getLocationLevel() {
		return locationLevel;
	}

	public String getLocationName() {
		return locationName;
	}

	public String getDescription() {
		return description;
	}

	public int getUnits() {
		return units;
	}

	public void setBpi(Bpi bpi) {
		this.bpi = bpi;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setLocationLevel(LocationLevelEnum locationLevel) {
		this.locationLevel = locationLevel;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setUnits(int units) {
		this.units = units;
	}

	
}
