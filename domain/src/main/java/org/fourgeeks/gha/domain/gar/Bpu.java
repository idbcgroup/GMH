/**
 * 
 */
package org.fourgeeks.gha.domain.gar;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.fourgeeks.gha.domain.AbstractEntity;
import org.fourgeeks.gha.domain.ess.ui.PermissionBpu;
import org.fourgeeks.gha.domain.mix.Bpi;
import org.fourgeeks.gha.domain.mix.Citizen;

/**
 * @author emiliot, vivi.torresg
 * 
 */

@Entity
@NamedQueries(value = {
		@NamedQuery(name = "Bpu.getAll", query = "SELECT e from Bpu e order by e.id"),
		@NamedQuery(name = "Bpu.findByBpu", query = "SELECT e from Bpu e where e like :bpu order by e.id") })
public class Bpu extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * This is when the bpu belongs to the bpi
	 */
	@NotNull(message = "bpi-not-null")
	@ManyToOne
	@JoinColumn(name = "bpiFk", nullable = false)
	private Bpi bpi;

	/**
	 * The citizen associated with this bpu
	 */
	@NotNull
	@OneToOne(cascade = { CascadeType.MERGE })
	@JoinColumn(name = "citizenFk", nullable = false)
	private Citizen citizen; // TODO : No debe ir aqui, los citizen son BPA,
								// debe haber una relacion con BPA

	@OneToOne
	@JoinColumn(name = "jobPositionFk")
	private JobPosition jobPosition;

	@Transient
	private List<PermissionBpu> permissions;

	private String sessionId;

	/**
	 * 
	 */
	public Bpu() {
	}

	/**
	 * @param bpi
	 * @param citizen
	 */
	public Bpu(Bpi bpi, Citizen citizen) {
		this.bpi = bpi;
		this.citizen = citizen;
	}

	public Bpi getBpi() {
		return bpi;
	}

	public Citizen getCitizen() {
		return citizen;
	}

	public JobPosition getJobPosition() {
		return jobPosition;
	}

	/**
	 * @return the permissions
	 */
	public List<PermissionBpu> getPermissions() {
		return permissions;
	}

	/**
	 * 
	 * @return the Id from session
	 */
	public String getSessionId() {
		return sessionId;
	}

	public void setBpi(Bpi bpi) {
		this.bpi = bpi;
	}

	public void setCitizen(Citizen citizen) {
		this.citizen = citizen;
	}

	public void setJobPosition(JobPosition jobPosition) {
		this.jobPosition = jobPosition;
	}

	/**
	 * @param permissions
	 *            the permissions to set
	 */
	public void setPermissions(List<PermissionBpu> permissions) {
		this.permissions = permissions;
	}

	/**
	 * 
	 * @param sessionId
	 *            the Id from session
	 */
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

}
