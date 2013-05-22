package org.fourgeeks.gha.domain.ess;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

import org.fourgeeks.gha.domain.AbstractEntity;
import org.fourgeeks.gha.domain.mix.LegalEntity;

@Entity
public class SingleSignOnUser extends AbstractEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@OneToOne
	private LegalEntity legalEntity;
}
