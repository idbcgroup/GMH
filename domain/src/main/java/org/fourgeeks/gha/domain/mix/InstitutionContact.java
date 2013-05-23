package org.fourgeeks.gha.domain.mix;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.fourgeeks.gha.domain.AbstractEntity;

@Entity
public class InstitutionContact extends AbstractEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "institutionFk")
	private Institution institution;

	/**
	 * 
	 */
	public InstitutionContact() {
		super();
		// TODO Auto-generated constructor stub
	}
}
