package org.fourgeeks.gha.domain.mix;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import org.fourgeeks.gha.domain.AbstractEntity;

@Entity
public class LegalEntityAddress extends AbstractEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// TODO: private long addressRelationType;
	@ManyToOne
	private LegalEntity legalEntity;

	/**
	 * 
	 */
	public LegalEntityAddress() {
		super();
		// TODO Auto-generated constructor stub
	}
}
