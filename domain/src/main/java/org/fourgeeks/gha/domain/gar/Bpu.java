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
import org.fourgeeks.gha.domain.ess.auth.FunctionBpu;
import org.fourgeeks.gha.domain.ess.ui.AppView;
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
	 * This is when the bpu belongs to a bpi
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
	private List<FunctionBpu> functions;

	@Transient
	private List<AppView> appsViews;

	// TODO: Esto para que es?
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

	/**
	 * @return the App views who has access the user
	 */
	public List<AppView> getAppsViews() {
		return appsViews;
	}

	/**
	 * @return the bpi of this user
	 */
	public Bpi getBpi() {
		return bpi;
	}

	/**
	 * @return the citizen backed by this BPU
	 */
	public Citizen getCitizen() {
		return citizen;
	}

	/**
	 * @return the permissions
	 */
	public List<FunctionBpu> getFunctions() {
		return functions;
	}

	/**
	 * @return the job position
	 */
	public JobPosition getJobPosition() {
		return jobPosition;
	}

	/**
	 * 
	 * @return the Id from session
	 */
	public String getSessionId() {
		return sessionId;
	}

	/**
	 * @param appsViews
	 */
	public void setAppsViews(List<AppView> appsViews) {
		this.appsViews = appsViews;
	}

	/**
	 * @param bpi
	 */
	public void setBpi(Bpi bpi) {
		this.bpi = bpi;
	}

	/**
	 * @param citizen
	 */
	public void setCitizen(Citizen citizen) {
		this.citizen = citizen;
	}

	/**
	 * @param functions
	 *            the functions to set
	 */
	public void setFunctions(List<FunctionBpu> functions) {
		this.functions = functions;
	}

	/**
	 * @param jobPosition
	 */
	public void setJobPosition(JobPosition jobPosition) {
		this.jobPosition = jobPosition;
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
