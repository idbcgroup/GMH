package org.fourgeeks.gha.domain.mix;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class LegalEntityCredentials {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private CredentialTypeEnum primaryRoleCode;
	private String primaryIdentifier;
	private String secondaryIdentifier;
	private Timestamp createdDate;
	private Long portalUser;
	@OneToOne
	private LegalEntity legalEntity;
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

	public LegalEntity getLegalEntity() {
		return legalEntity;
	}

	public void setLegalEntity(LegalEntity legalEntity) {
		this.legalEntity = legalEntity;
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
