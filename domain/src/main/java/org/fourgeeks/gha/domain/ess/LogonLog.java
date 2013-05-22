package org.fourgeeks.gha.domain.ess;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.fourgeeks.gha.domain.AbstractEntity;
import org.fourgeeks.gha.domain.mix.LegalEntityCredential;

@Entity
public class LogonLog extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@ManyToOne
	@JoinColumn(name = "legalEntityCredentialFk")
	private LegalEntityCredential legalEntityCredential;
}
