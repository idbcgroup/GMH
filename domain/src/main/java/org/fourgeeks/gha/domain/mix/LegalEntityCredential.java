package org.fourgeeks.gha.domain.mix;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.fourgeeks.gha.domain.AbstractEntity;
import org.fourgeeks.gha.domain.ess.SingleSignOnUser;

@Entity
public class LegalEntityCredential extends AbstractEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */

	private CredentialTypeEnum primaryRoleCode;
	
	@ManyToOne
	@JoinColumn(name = "singleSignOnUserFk")
	private SingleSignOnUser singleSignOnUser;
	
	@ManyToOne
	@JoinColumn(name = "systemInstanceFk")
	private SystemInstance systemInstance;

	/**
	 * 
	 */
	public LegalEntityCredential() {
		super();
		// TODO Auto-generated constructor stub
	}
}
