package org.fourgeeks.gha.domain.gmh;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.fourgeeks.gha.domain.AbstractEntity;
import org.fourgeeks.gha.domain.gar.Facility;

@Entity
public class Equipment extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	@OneToOne(mappedBy = "equipment")
	private Terminal terminal;

	@ManyToOne
	@JoinColumn(name = "facilityFk")
	private Facility facility;

	@ManyToOne
	@JoinColumn(name = "eiatypeFk")
	private EiaType eiatype;

//	@ManyToMany
//	private Collection<Waio> waios;

	@ManyToOne
	@JoinColumn(name = "facilityFk")
	private Facility buildingLocation;
	
	@OneToOne(mappedBy = "equipment")
	private SystemPeripheral systemPeripheral;
	
	private String serial;

	/**
	 * 
	 */
	public Equipment() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Terminal getTerminal() {
		return terminal;
	}

	public void setTerminal(Terminal terminal) {
		this.terminal = terminal;
	}

	public Facility getFacility() {
		return facility;
	}

	public void setFacility(Facility facility) {
		this.facility = facility;
	}

	public EiaType getEiatype() {
		return eiatype;
	}

	public void setEiatype(EiaType eiatype) {
		this.eiatype = eiatype;
	}

	public Facility getBuildingLocation() {
		return buildingLocation;
	}

	public void setBuildingLocation(Facility buildingLocation) {
		this.buildingLocation = buildingLocation;
	}

	public SystemPeripheral getSystemPeripheral() {
		return systemPeripheral;
	}

	public void setSystemPeripheral(SystemPeripheral systemPeripheral) {
		this.systemPeripheral = systemPeripheral;
	}

	public String getSerial() {
		return serial;
	}

	public void setSerial(String serial) {
		this.serial = serial;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
