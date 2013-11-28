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
import org.fourgeeks.gha.domain.ess.ui.AppFormViewFunctionBpu;
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

	@ManyToOne
	@JoinColumn(name = "shiftFk")
	private Shift shift;

	@ManyToOne
	@JoinColumn(name = "onDutyPlanFk")
	private OnDutyPlan onDutyPlan;

	@ManyToOne
	@JoinColumn(name = "jobPositionFk")
	private JobPosition jobPosition;

	@Transient
	private List<AppFormViewFunctionBpu> permissions;

	private String sessionId;

	// @ManyToOne
	// @JoinColumn(name = "jobFk")
	// private Job job;

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

	public void setBpi(Bpi bpi) {
		this.bpi = bpi;
	}

	public Citizen getCitizen() {
		return citizen;
	}

	public void setCitizen(Citizen citizen) {
		this.citizen = citizen;
	}

	public Shift getShift() {
		return shift;
	}

	public void setShift(Shift shift) {
		this.shift = shift;
	}

	public JobPosition getJobPosition() {
		return jobPosition;
	}

	public void setJobPosition(JobPosition jobPosition) {
		this.jobPosition = jobPosition;
	}

	/**
	 * @return the permissions
	 */
	public List<AppFormViewFunctionBpu> getPermissions() {
		return permissions;
	}

	/**
	 * @param permissions
	 *            the permissions to set
	 */
	public void setPermissions(List<AppFormViewFunctionBpu> permissions) {
		this.permissions = permissions;
	}

	/**
	 * 
	 * @return the Id from session
	 */
	public String getSessionId() {
		return sessionId;
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
