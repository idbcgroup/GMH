package org.fourgeeks.gha.domain.mix;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.fourgeeks.gha.domain.AbstractEntity;

@Entity
public class CitizenPicture extends AbstractEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	@JoinColumn(name = "citizenFk")
	private Citizen citizen;
	
	/**
	 * 
	 */
	public CitizenPicture() {
		super();
		// TODO Auto-generated constructor stub
	}
}
