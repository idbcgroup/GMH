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
import org.fourgeeks.gha.domain.enu.TimePeriodEnum;
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
	private int effectiveTime;
	private TimePeriodEnum effectivePoT;
	private EiaStateEnum initialEiaState;
	private EiaStateEnum finalEiaState;
	private MaintenancePlanificationStatus status;
	private MaintenancePlanificationType type;

	public EiaMaintenancePlanification() {
	}

	/**
	 * @return the acceptationDate
	 */
	public Date getAcceptationDate() {
		return acceptationDate;
	}

	public Date getBeginningDate() {
		return beginningDate;
	}

	/**
	 * @return the deliverDate
	 */
	public Date getDeliverDate() {
		return deliverDate;
	}

	public TimePeriodEnum getEffectivePoT() {
		return effectivePoT;
	}

	public int getEffectiveTime() {
		return effectiveTime;
	}

	public Eia getEia() {
		return eia;
	}

	public EiaStateEnum getFinalEiaState() {
		return finalEiaState;
	}

	public Date getFinishDate() {
		return finishDate;
	}

	public EiaStateEnum getInitialEiaState() {
		return initialEiaState;
	}

	/**
	 * @return the provider
	 */
	public ExternalProvider getProvider() {
		return provider;
	}

	public String getRequestNumber() {
		return requestNumber;
	}

	public Role getRole() {
		return role;
	}

	public Date getScheduledDate() {
		return scheduledDate;
	}

	public MaintenancePlanificationStatus getStatus() {
		return status;
	}

	public String getTechnicianName() {
		return technicianName;
	}

	public MaintenancePlanificationType getType() {
		return type;
	}

	/**
	 * @param acceptationDate
	 *            the acceptationDate to set
	 */
	public void setAcceptationDate(Date acceptationDate) {
		this.acceptationDate = acceptationDate;
	}

	public void setBeginningDate(Date beginningDate) {
		this.beginningDate = beginningDate;
	}

	/**
	 * @param deliverDate
	 *            the deliverDate to set
	 */
	public void setDeliverDate(Date deliverDate) {
		this.deliverDate = deliverDate;
	}

	public void setEffectivePoT(TimePeriodEnum effectivePoT) {
		this.effectivePoT = effectivePoT;
	}

	public void setEffectiveTime(int effectiveTime) {
		this.effectiveTime = effectiveTime;
	}

	public void setEia(Eia eia) {
		this.eia = eia;
	}

	public void setFinalEiaState(EiaStateEnum finalEiaState) {
		this.finalEiaState = finalEiaState;
	}

	public void setFinishDate(Date finishDate) {
		this.finishDate = finishDate;
	}

	public void setInitialEiaState(EiaStateEnum initialEiaState) {
		this.initialEiaState = initialEiaState;
	}

	/**
	 * @param provider
	 *            the provider to set
	 */
	public void setProvider(ExternalProvider provider) {
		this.provider = provider;
	}

	public void setRequestNumber(String requestNumber) {
		this.requestNumber = requestNumber;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public void setScheduledDate(Date scheduledDate) {
		this.scheduledDate = scheduledDate;
	}

	public void setStatus(MaintenancePlanificationStatus status) {
		this.status = status;
	}

	public void setTechnicianName(String technicianName) {
		this.technicianName = technicianName;
	}

	public void setType(MaintenancePlanificationType type) {
		this.type = type;
	}
}
