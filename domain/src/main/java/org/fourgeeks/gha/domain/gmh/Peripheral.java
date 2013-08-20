/**
 * 
 */
package org.fourgeeks.gha.domain.gmh;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.fourgeeks.gha.domain.AbstractEntity;

/**
 * @author emiliot
 *
 */
@Entity
public class Peripheral extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@OneToOne
	@JoinColumn(name = "eiaFk", nullable = false)
	private Eia eia;
	
	private String name; /** Nombre del periférico en el sistema length =255 */
	private String description; /** length =255 */
	private String type; /** length =60 */
	private String ipAddress; /** length =36 */
	private String status; /** length =60 */


	/**
	 * @param eia
	 */
	public Peripheral(Eia eia) {
		this.eia = eia;
	}

	public Eia getEia() {
		return eia;
	}

	public void setEia(Eia eia) {
		this.eia = eia;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public String getType() {
		return type;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public String getStatus() {
		return status;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	
}
