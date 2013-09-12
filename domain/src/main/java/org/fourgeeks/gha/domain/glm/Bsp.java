/**
 * 
 */
package org.fourgeeks.gha.domain.glm;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.fourgeeks.gha.domain.AbstractEntity;
import org.fourgeeks.gha.domain.gar.Obu;

/**
 * @author emiliot
 *
 */

@Entity
public class Bsp extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	@JoinColumn(name = "obuFk")
	private Obu obu;

	/**
	 * 
	 */
	public Bsp() {
		// TODO Auto-generated constructor stub
	}

	public Obu getObu() {
		return obu;
	}

	public void setObu(Obu obu) {
		this.obu = obu;
	}
	
}
