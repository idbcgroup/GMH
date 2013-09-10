/**
 * 
 */
package org.fourgeeks.gha.domain.mix;

import javax.persistence.Entity;

import org.fourgeeks.gha.domain.AbstractEntity;
import org.fourgeeks.gha.domain.enu.CredentialTypeEnum;

/**
 * @author emiliot
 *
 */

@Entity
public class Bpa extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private CredentialTypeEnum credentialType;

	/**
	 * 
	 */
	public Bpa() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param credentialType
	 */
	public Bpa(CredentialTypeEnum credentialType) {
		this.credentialType = credentialType;
	}

	public CredentialTypeEnum getCredentialType() {
		return credentialType;
	}

	public void setCredentialType(CredentialTypeEnum credentialType) {
		this.credentialType = credentialType;
	}
}
