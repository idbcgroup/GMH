package org.fourgeeks.gha.domain.ess;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.fourgeeks.gha.domain.AbstractEntity;
import org.fourgeeks.gha.domain.enu.UserLogonStatusEnum;
import org.fourgeeks.gha.domain.gar.Bpu;

/**
 * @author emiliot
 * Single Sign On User Entity
 */
@Entity
@Table(name = "SSOUser", uniqueConstraints = @UniqueConstraint(columnNames = { "userName" }))
@NamedQueries(value = {
		@NamedQuery(name = "SSOUser.getAll", query = "SELECT e from SSOUser e order by e.userName"),
		@NamedQuery(name = "SSOUser.findByUserName", query = "SELECT e from SSOUser e WHERE e.userName = :userName")})
public class SSOUser extends AbstractEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

//	@OneToOne
//	@JoinColumn(name = "legalEntityFk")
//	private LegalEntity legalEntity;
	
	@OneToOne
	@JoinColumn(name = "bpuFk")
	private Bpu bpu;

	private String password;
	private String userName;
	private UserLogonStatusEnum userLogonStatus;
	
	@Column(/* nullable = false, */columnDefinition = "boolean NOT NULL DEFAULT false")
	private boolean blocked;

//	/**
//	 * @return the legalEntity
//	 */
//	public LegalEntity getLegalEntity() {
//		return legalEntity;
//	}
//
//	/**
//	 * @param legalEntity
//	 *            the legalEntity to set
//	 */
//	public void setLegalEntity(LegalEntity legalEntity) {
//		this.legalEntity = legalEntity;
//	}

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

	public Bpu getBpu() {
		return bpu;
	}

	public void setBpu(Bpu bpu) {
		this.bpu = bpu;
	}
}
