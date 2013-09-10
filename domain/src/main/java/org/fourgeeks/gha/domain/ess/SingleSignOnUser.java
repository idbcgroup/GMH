package org.fourgeeks.gha.domain.ess;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.fourgeeks.gha.domain.AbstractEntity;
import org.fourgeeks.gha.domain.enu.UserLogonStatusEnum;
import org.fourgeeks.gha.domain.mix.LegalEntity;

@Entity
public class SingleSignOnUser extends AbstractEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@OneToOne
	@JoinColumn(name = "legalEntityFk")
	private LegalEntity legalEntity;

	private String password;
	private String userName;
	private UserLogonStatusEnum userLogonStatus;
	
	@Column(/* nullable = false, */columnDefinition = "boolean NOT NULL DEFAULT false")
	private boolean blocked;

	/**
	 * @return the legalEntity
	 */
	public LegalEntity getLegalEntity() {
		return legalEntity;
	}

	/**
	 * @param legalEntity
	 *            the legalEntity to set
	 */
	public void setLegalEntity(LegalEntity legalEntity) {
		this.legalEntity = legalEntity;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName
	 *            the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the userLogonStatus
	 */
	public UserLogonStatusEnum getUserLogonStatus() {
		return userLogonStatus;
	}

	/**
	 * @param userLogonStatus
	 *            the userLogonStatus to set
	 */
	public void setUserLogonStatus(UserLogonStatusEnum userLogonStatus) {
		this.userLogonStatus = userLogonStatus;
	}

	/**
	 * @return the blocked
	 */
	public boolean isBlocked() {
		return blocked;
	}

	/**
	 * @param blocked
	 *            the blocked to set
	 */
	public void setBlocked(boolean blocked) {
		this.blocked = blocked;
	}
}
