/**
 * 
 */
package org.fourgeeks.gha.domain.glm;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import org.fourgeeks.gha.domain.AbstractEntity;
import org.fourgeeks.gha.domain.gar.Obu;

/**
 * @author emiliot
 * 
 */

@Entity
@NamedQueries(value = { @NamedQuery(name = "Bsp.getAll", query = "SELECT e from Bsp e order by e.id") })
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

	/**
	 * @return the obu
	 */
	public Obu getObu() {
		return obu;
	}

	/**
	 * @param obu
	 */
	public void setObu(Obu obu) {
		this.obu = obu;
	}

}
