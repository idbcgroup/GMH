/**
 * 
 */
package org.fourgeeks.gha.domain.glm;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.fourgeeks.gha.domain.AbstractEntity;
import org.fourgeeks.gha.domain.mix.Institution;

/**
 * @author emiliot
 *
 */

@Entity
public class BspInstitution extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@OneToOne
	@JoinColumn(name = "bspFk")
	private Bsp bsp;
	
	@ManyToOne
	@JoinColumn(name = "institutionFk")
	private Institution institution;

	/**
	 * 
	 */
	public BspInstitution() {
		// TODO Auto-generated constructor stub
	}

	public Bsp getBsp() {
		return bsp;
	}

	public void setBsp(Bsp bsp) {
		this.bsp = bsp;
	}

	public Institution getInstitution() {
		return institution;
	}

	public void setInstitution(Institution institution) {
		this.institution = institution;
	}
	
	
}
