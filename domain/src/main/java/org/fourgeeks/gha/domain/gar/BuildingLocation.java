package org.fourgeeks.gha.domain.gar;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.fourgeeks.gha.domain.enu.LocationLevelEnum;
import org.fourgeeks.gha.domain.mix.Bpi;

@Entity
@Table(name = "buildinglocation", uniqueConstraints = @UniqueConstraint(columnNames = { "code" }))
public class BuildingLocation implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	private String code;

	@ManyToOne
	@JoinColumn(name = "bpiFk", nullable = false)
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
	// @OneToOne(mappedBy = "buildingLocation")
	// private BuildingLocationChild buildingLocationChild;

	// @OneToMany(mappedBy = "buildingLocation")
	// private Collection <Waio> waios;

	// @OneToMany(mappedBy = "buildingLocation")
	// private Collection <Terminal> terminals;

	// @OneToMany(mappedBy = "buildingLocation")
	// private Collection <Eia> equipments;

	/** Attributes */

	/*@Column(nullable = false)
	private String code;
	/** Código Ubicación en Edificio length =20 */

	@Column(nullable = false)
	private LocationLevelEnum locationLevel;
	/** Nivel de la Ubicación en Edificio definida length =60 */
	private String name;
	/** Nombre de Ubicación en Edificio length =255 */
	private String description;
	/** Descripción de Ubicación en Edificio length =255 */

	@Column(nullable = false)
	private int units;

	/**
	 * Unidades disponibles en Ubicación en Edificio (Pisos, Habitaciones, etc)
	 * length =4
	 */

	/**
	 * 
	 */
	public BuildingLocation() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	/**
	 * @param code
	 */
	public BuildingLocation(String code) {
		this.code = code;
	}



	/**
	 * @param bpi
	 * @param code
	 * @param locationLevel
	 * @param units
	 */
	public BuildingLocation(Bpi bpi, String code,
			LocationLevelEnum locationLevel, String name) {
		super();
		this.bpi = bpi;
		this.code = code;
		this.locationLevel = locationLevel;
		this.name = name;
	}

	/**
	 * @param valueOf
	 */
	public Bpi getBpi() {
		return bpi;
	}

	public String getCode() {
		return code;
	}

	public LocationLevelEnum getLocationLevel() {
		return locationLevel;
	}

	public String getName() {
		return name;
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

	public void setName(String name) {
		this.name = name;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setUnits(int units) {
		this.units = units;
	}

}
