package org.fourgeeks.gha.domain.ess;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.fourgeeks.gha.domain.AbstractEntity;
import org.fourgeeks.gha.domain.mix.LegalEntity;
import org.fourgeeks.gha.domain.mix.LegalEntityCredential;

@Entity
public class SingleSignOnUser extends AbstractEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@OneToOne
	@JoinColumn(name = "legalEntityFk")
	private LegalEntity legalEntity;
	
	@OneToMany(mappedBy = "singleSignOnUser")
	private Collection<LegalEntityCredential> legalEntityCredentials;
}
