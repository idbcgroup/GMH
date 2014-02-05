/**
 * 
 */
package org.fourgeeks.gha.domain.gmh;

import java.sql.Date;
import java.sql.Timestamp;

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
public class EiaMaintenance extends AbstractEntity {
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
	private Timestamp beginningTimestamp;
	private Timestamp finishTimestamp;
	private int effectiveTime;
	private TimePeriodEnum effectivePoT;
	private EiaStateEnum initialEiaState;
	private EiaStateEnum finalEiaState;
	private MaintenancePlanificationStatus status;
	private MaintenancePlanificationType type;

	/**
	 * 
	 */
	public EiaMaintenance() {
	}

	/**
	 * @return the eia
	 */
	public Eia getEia() {
		return eia;
	}

	/**
	 * @param eia
	 *            the eia to set
	 */
	public void setEia(Eia eia) {
		this.eia = eia;
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
	 * @return the role
	 */
	public Role getRole() {
		return role;
	}

	/**
	 * @param role
	 *            the role to set
	 */
	public void setRole(Role role) {
		this.role = role;
	}

	/**
	 * @return the requestNumber
	 */
	public String getRequestNumber() {
		return requestNumber;
	}

	/**
	 * @param requestNumber
	 *            the requestNumber to set
	 */
	public void setRequestNumber(String requestNumber) {
		this.requestNumber = requestNumber;
	}

	/**
	 * @return the technicianName
	 */
	public String getTechnicianName() {
		return technicianName;
	}

	/**
	 * @param technicianName
	 *            the technicianName to set
	 */
	public void setTechnicianName(String technicianName) {
		this.technicianName = technicianName;
	}

	/**
	 * @return the scheduledDate
	 */
	public Date getScheduledDate() {
		return scheduledDate;
	}

	/**
	 * @param scheduledDate
	 *            the scheduledDate to set
	 */
	public void setScheduledDate(Date scheduledDate) {
		this.scheduledDate = scheduledDate;
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

	/**
	 * @return the beginningTimestamp
	 */
	public Timestamp getBeginningTimestamp() {
		return beginningTimestamp;
	}

	/**
	 * @param beginningTimestamp
	 *            the beginningTimestamp to set
	 */
	public void setBeginningTimestamp(Timestamp beginningTimestamp) {
		this.beginningTimestamp = beginningTimestamp;
	}

	/**
	 * @return the finishTimestamp
	 */
	public Timestamp getFinishTimestamp() {
		return finishTimestamp;
	}

	/**
	 * @param finishTimestamp
	 *            the finishTimestamp to set
	 */
	public void setFinishTimestamp(Timestamp finishTimestamp) {
		this.finishTimestamp = finishTimestamp;
	}

	/**
	 * @return the effectiveTime
	 */
	public int getEffectiveTime() {
		return effectiveTime;
	}

	/**
	 * @param effectiveTime
	 *            the effectiveTime to set
	 */
	public void setEffectiveTime(int effectiveTime) {
		this.effectiveTime = effectiveTime;
	}

	/**
	 * @return the effectivePoT
	 */
	public TimePeriodEnum getEffectivePoT() {
		return effectivePoT;
	}

	/**
	 * @param effectivePoT
	 *            the effectivePoT to set
	 */
	public void setEffectivePoT(TimePeriodEnum effectivePoT) {
		this.effectivePoT = effectivePoT;
	}

	/**
	 * @return the initialEiaState
	 */
	public EiaStateEnum getInitialEiaState() {
		return initialEiaState;
	}

	/**
	 * @param initialEiaState
	 *            the initialEiaState to set
	 */
	public void setInitialEiaState(EiaStateEnum initialEiaState) {
		this.initialEiaState = initialEiaState;
	}

	/**
	 * @return the finalEiaState
	 */
	public EiaStateEnum getFinalEiaState() {
		return finalEiaState;
	}

	/**
	 * @param finalEiaState
	 *            the finalEiaState to set
	 */
	public void setFinalEiaState(EiaStateEnum finalEiaState) {
		this.finalEiaState = finalEiaState;
	}

	/**
	 * @return the status
	 */
	public MaintenancePlanificationStatus getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(MaintenancePlanificationStatus status) {
		this.status = status;
	}

	/**
	 * @return the type
	 */
	public MaintenancePlanificationType getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(MaintenancePlanificationType type) {
		this.type = type;
	}

}
