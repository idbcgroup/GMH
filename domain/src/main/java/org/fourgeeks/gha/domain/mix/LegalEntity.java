package org.fourgeeks.gha.domain.mix;

import javax.persistence.Entity;

import org.fourgeeks.gha.domain.AbstractEntity;

@Entity
public class LegalEntity extends AbstractEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	// TODO: relaciones con legalEntityAddress, institution y citizen

	/**
	 * 
	 */
	public LegalEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
}
