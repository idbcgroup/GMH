/**
 * 
 */
package org.fourgeeks.gha.domain.gar;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.fourgeeks.gha.domain.AbstractEntity;
import org.fourgeeks.gha.domain.mix.Bpi;
import org.fourgeeks.gha.domain.mix.Citizen;
import org.fourgeeks.gha.domain.mix.Institution;

/**
 * @author emiliot
 *
 */

@Entity
public class Bpu extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * This is when the bpu belongs to the bpi
	 */
	@ManyToOne
	@JoinColumn(name = "bpiFk")
	private Bpi bpi;
	
	
	/**
	 * This is when the bpu comes from an external institution
	 */
	@ManyToOne
	@JoinColumn(name = "institutionFk")
	private Institution institution;
	
	//TODO: CONFIRMAR LA RELACION CON CITIZEN
	/**
	 * The citizen associated with this bpu
	 */
	@OneToOne
	@JoinColumn(name = "citizenFk")
	private Citizen citizen;


	/**
	 * 
	 */
	public Bpu() {
		// TODO Auto-generated constructor stub
	}


	public Bpi getBpi() {
		return bpi;
	}


	public void setBpi(Bpi bpi) {
		this.bpi = bpi;
	}


	public Institution getInstitution() {
		return institution;
	}


	public void setInstitution(Institution institution) {
		this.institution = institution;
	}

}
