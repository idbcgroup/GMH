package org.fourgeeks.gha.domain.mix;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.fourgeeks.gha.domain.AbstractEntity;
import org.fourgeeks.gha.domain.vcf.SingleSignOnUser;

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
	//
	private String primaryIdentifier;
	private String secondaryIdentifier;
	private Timestamp createdDate;
	private Long portalUser;
	// TODO: SYSTEM_INSTANCE_FK
	private boolean userhasSingleSignOn;
	private CredentialTypeEnum secundaryRoleCode;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public CredentialTypeEnum getPrimaryRoleCode() {
		return primaryRoleCode;
	}

	public void setPrimaryRoleCode(CredentialTypeEnum primaryRoleCode) {
		this.primaryRoleCode = primaryRoleCode;
	}

	public String getPrimaryIdentifier() {
		return primaryIdentifier;
	}

	public void setPrimaryIdentifier(String primaryIdentifier) {
		this.primaryIdentifier = primaryIdentifier;
	}

	public String getSecondaryIdentifier() {
		return secondaryIdentifier;
	}

	public void setSecondaryIdentifier(String secondaryIdentifier) {
		this.secondaryIdentifier = secondaryIdentifier;
	}

	public Timestamp getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	public Long getPortalUser() {
		return portalUser;
	}

	public void setPortalUser(Long portalUser) {
		this.portalUser = portalUser;
	}

	public boolean isUserhasSingleSignOn() {
		return userhasSingleSignOn;
	}

	public void setUserhasSingleSignOn(boolean userhasSingleSignOn) {
		this.userhasSingleSignOn = userhasSingleSignOn;
	}

	public CredentialTypeEnum getSecundaryRoleCode() {
		return secundaryRoleCode;
	}

	public void setSecundaryRoleCode(CredentialTypeEnum secundaryRoleCode) {
		this.secundaryRoleCode = secundaryRoleCode;
	}
}
