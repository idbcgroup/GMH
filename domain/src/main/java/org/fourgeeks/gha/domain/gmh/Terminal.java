/**
 * 
 */
package org.fourgeeks.gha.domain.gmh;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.fourgeeks.gha.domain.AbstractEntity;
import org.fourgeeks.gha.domain.ess.WorkingArea;

/**
 * @author emiliot
 *
 */

@Entity
public class Terminal extends AbstractEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String name;
	private String ipAddress; /** length =36 */
	private String macAddress; /** Dirección MAC de equipo length =30 */
	
	/**
	 * Eia representing this terminal
	 */
	@OneToOne
	@JoinColumn(name = "eiaFk", nullable = false)
	private Eia eia;
	
	@ManyToOne
	@JoinColumn(name = "workingArea", nullable = false)
	private WorkingArea attendedArea;

	/**
	 * 
	 */
	public Terminal() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param name
	 * @param ipAddress
	 * @param macAddress
	 * @param eia
	 * @param attendedArea
	 */
	public Terminal(String name, String ipAddress, String macAddress, Eia eia,
			WorkingArea attendedArea) {
		this.name = name;
		this.ipAddress = ipAddress;
		this.macAddress = macAddress;
		this.eia = eia;
		this.attendedArea = attendedArea;
	}

	public String getName() {
		return name;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public String getMacAddress() {
		return macAddress;
	}

	public Eia getEia() {
		return eia;
	}

	public WorkingArea getAttendedArea() {
		return attendedArea;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public void setMacAddress(String macAddress) {
		this.macAddress = macAddress;
	}

	public void setEia(Eia eia) {
		this.eia = eia;
	}

	public void setAttendedArea(WorkingArea attendedArea) {
		this.attendedArea = attendedArea;
	}
}
