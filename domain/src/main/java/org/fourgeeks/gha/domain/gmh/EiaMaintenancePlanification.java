/**
 * 
 */
package org.fourgeeks.gha.domain.gmh;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import org.fourgeeks.gha.domain.AbstractEntity;
import org.fourgeeks.gha.domain.enu.EiaStateEnum;
import org.fourgeeks.gha.domain.enu.MaintenancePlanificationStatus;
import org.fourgeeks.gha.domain.enu.MaintenancePlanificationType;
import org.fourgeeks.gha.domain.ess.Role;
import org.fourgeeks.gha.domain.glm.ExternalProvider;

/**
 * @author emiliot
 * 
 */

@Entity
@NamedQueries(value = { @NamedQuery(name = "findByEiaType", query = "SELECT emp FROM EiaMaintenancePlanification emp JOIN emp.eia empeia WHERE empeia.eiaType = :eiaType ORDER BY emp.id") })
public class EiaMaintenancePlanification extends AbstractEntity {
	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "eiaFk")
	private Eia eia;
	@ManyToOne
	@JoinColumn(name = "providerFk")
	private ExternalProvider provider;
	@ManyToOne
	@JoinColumn(name = "roleFk")
	private Role role;

	private String requestNumber;
	private String technicianName;
	private Date scheduledDate;
	private Date deliverDate;
	private Date acceptationDate;
	private Date beginningDate;
	private Date finishDate;
	private EiaStateEnum initialEiaState;
	private EiaStateEnum finalEiaState;
	private MaintenancePlanificationStatus status;
	private MaintenancePlanificationType type;

	public EiaMaintenancePlanification() {
	}

	public Eia getEia() {
		return eia;
	}

	public Date getScheduledDate() {
		return scheduledDate;
	}

	public void setEia(Eia eia) {
		this.eia = eia;
	}

	public void setScheduledDate(Date scheduledDate) {
		this.scheduledDate = scheduledDate;
	}

	/**
	 * @return the provider
	 */
	public ExternalProvider getProvider() {
		return provider;
	}

	/**
	 * @param provider
	 *            the provider to set
	 */
	public void setProvider(ExternalProvider provider) {
		this.provider = provider;
	}

	/**
	 * @return the deliverDate
	 */
	public Date getDeliverDate() {
		return deliverDate;
	}

	/**
	 * @param deliverDate
	 *            the deliverDate to set
	 */
	public void setDeliverDate(Date deliverDate) {
		this.deliverDate = deliverDate;
	}

	/**
	 * @return the acceptationDate
	 */
	public Date getAcceptationDate() {
		return acceptationDate;
	}

	/**
	 * @param acceptationDate
	 *            the acceptationDate to set
	 */
	public void setAcceptationDate(Date acceptationDate) {
		this.acceptationDate = acceptationDate;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public MaintenancePlanificationStatus getStatus() {
		return status;
	}

	public void setStatus(MaintenancePlanificationStatus status) {
		this.status = status;
	}

	public MaintenancePlanificationType getType() {
		return type;
	}

	public void setType(MaintenancePlanificationType type) {
		this.type = type;
	}

	public String getRequestNumber() {
		return requestNumber;
	}

	public void setRequestNumber(String requestNumber) {
		this.requestNumber = requestNumber;
	}

	public String getTechnicianName() {
		return technicianName;
	}

	public void setTechnicianName(String technicianName) {
		this.technicianName = technicianName;
	}

	public Date getBeginningDate() {
		return beginningDate;
	}

	public void setBeginningDate(Date beginningDate) {
		this.beginningDate = beginningDate;
	}

	public Date getFinishDate() {
		return finishDate;
	}

	public void setFinishDate(Date finishDate) {
		this.finishDate = finishDate;
	}

	public EiaStateEnum getInitialEiaState() {
		return initialEiaState;
	}

	public void setInitialEiaState(EiaStateEnum initialEiaState) {
		this.initialEiaState = initialEiaState;
	}

	public EiaStateEnum getFinalEiaState() {
		return finalEiaState;
	}

	public void setFinalEiaState(EiaStateEnum finalEiaState) {
		this.finalEiaState = finalEiaState;
	}
}
