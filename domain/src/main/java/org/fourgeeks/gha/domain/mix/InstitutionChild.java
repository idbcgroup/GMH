package org.fourgeeks.gha.domain.mix;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.fourgeeks.gha.domain.AbstractEntity;

@Entity
public class InstitutionChild extends AbstractEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * This represents the parent institution
	 */
	@ManyToOne
	@JoinColumn(name = "parentInstitutionFk")
	private Institution parentInstitution;
	
	//TODO: confirmar si una institucion hija tiene todos los atributos como si fuera una institucion normal
	/**
	 * This respresents the institution associated with this child institution
	 */
	@OneToOne
	@JoinColumn(name = "institutionFk")
	private Institution institution;
}
