/**
 * 
 */
package org.fourgeeks.gha.domain.mix;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.validation.constraints.NotNull;

import org.fourgeeks.gha.domain.AbstractEntity;
import org.fourgeeks.gha.domain.enu.CredentialTypeEnum;

/**
 * @author emiliot, vivi.torresg
 * 
 */

@Entity
@NamedQueries(value = {
		@NamedQuery(name = "Bpa.getAll", query = "SELECT e from Bpa e order by e.credentialType"),
		@NamedQuery(name = "Bpa.findByBpa", query = "SELECT e from Bpa e where e like :bpa order by e.credentialType") })
public class Bpa extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "legalEntityFk", nullable = false)
	private LegalEntity legalEntity;

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
