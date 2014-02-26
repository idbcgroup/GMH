package org.fourgeeks.gha.domain.ess.auth;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.fourgeeks.gha.domain.AbstractEntity;
import org.fourgeeks.gha.domain.enu.UserLogonStatusEnum;
import org.fourgeeks.gha.domain.gar.Bpu;

/**
 * @author emiliot, vivi.torresg
 * 
 *         Single Sign On User Entity
 */
@Entity
@Table(name = "SSOUser", uniqueConstraints = @UniqueConstraint(columnNames = { "userName" }))
@NamedQueries(value = {
		@NamedQuery(name = "SSOUser.getAll", query = "SELECT e from SSOUser e order by e.userName"),
		@NamedQuery(name = "SSOUser.findBySSOUser", query = "SELECT e from SSOUser e WHERE e like :ssoUser order by e.userName"),
		@NamedQuery(name = "SSOUser.findByUserName", query = "SELECT e from SSOUser e WHERE e.userName = :userName") })
public class SSOUser extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// @OneToOne
	// @JoinColumn(name = "legalEntityFk")
	// private LegalEntity legalEntity;

	@NotNull
	@OneToOne(cascade = { CascadeType.MERGE })
	@JoinColumn(name = "bpuFk", nullable = false, columnDefinition = "bigint REFERENCES bpu(id) ON UPDATE CASCADE ON DELETE CASCADE")
	private Bpu bpu;

	@Size(max = 20)
	@NotNull(message = "password-not-null")
	@Column(nullable = false)
	private String password;

	@Size(max = 20)
	@NotNull(message = "username-not-null")
	@Column(nullable = false)
	private String userName;

	@NotNull
	@Column(nullable = false)
	private UserLogonStatusEnum userLogonStatus;

	/**
	 * @param bpu
	 * @param password
	 * @param userName
	 * @param userLogonStatus
	 */
	public SSOUser(Bpu bpu, String password, String userName,
			UserLogonStatusEnum userLogonStatus) {
		this.bpu = bpu;
		this.password = password;
		this.userName = userName;
		this.userLogonStatus = userLogonStatus;
	}

	/**
	 * 
	 */
	public SSOUser() {
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
	 * @return the bpu
	 */
	public Bpu getBpu() {
		return bpu;
	}

	/**
	 * @param bpu
	 */
	public void setBpu(Bpu bpu) {
		this.bpu = bpu;
	}
}