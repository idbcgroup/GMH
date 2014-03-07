/**
 * 
 */
package org.fourgeeks.gha.domain.gmh;

import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.fourgeeks.gha.domain.AbstractEntity;
import org.fourgeeks.gha.domain.enu.EiaMaintenanceState;
import org.fourgeeks.gha.domain.enu.EiaStateEnum;
import org.fourgeeks.gha.domain.enu.MaintenanceCancelationCause;
import org.fourgeeks.gha.domain.enu.TimePeriodEnum;
import org.fourgeeks.gha.domain.gar.Bpu;
import org.fourgeeks.gha.domain.glm.Bsp;

/**
 * @author emiliot
 * 
 */

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "m_type", discriminatorType = DiscriminatorType.STRING)
public abstract class EiaMaintenance extends AbstractEntity {
	private static final long serialVersionUID = 1L;

	private String requestNumber;
	private EiaMaintenanceState state;
	private MaintenanceCancelationCause cancelationCause;
	private Date scheduledDate;
	private Date nextMantenanceExecutionDate;
	@ManyToOne
	@JoinColumn(name = "cancelationResponsableFk")
	private Bpu cancelationResponsable;
	@ManyToOne
	@JoinColumn(name = "providerFk")
	private Bsp provider;
	@ManyToOne
	@JoinColumn(name = "technicianFk")
	private Bpu technician;
	private Timestamp beginningTimestamp;
	private Timestamp finishTimestamp;
	private int effectiveTime;
	private TimePeriodEnum effectivePoT;
	private EiaStateEnum initialEiaState;
	private EiaStateEnum finalEiaState;
	private Date deliverDate;
	private Date acceptationDate;

	/**
	 * 
	 */
	public EiaMaintenance() {
	}

	/**
	 * @return the provider
	 */
	public Bsp getProvider() {
		return provider;
	}

	/**
	 * @param provider
	 *            the provider to set
	 */
	public void setProvider(Bsp provider) {
		this.provider = provider;
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
	 * @return the technician
	 */
	public Bpu getTechnician() {
		return technician;
	}

	/**
	 * @param technician
	 *            the technician to set
	 */
	public void setTechnician(Bpu technician) {
		this.technician = technician;
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
	public EiaMaintenanceState getState() {
		return state;
	}

	/**
	 * @param state
	 *            the status to set
	 */
	public void setState(EiaMaintenanceState state) {
		this.state = state;
	}

	/**
	 * @return
	 */
	public MaintenanceCancelationCause getCancelationCause() {
		return cancelationCause;
	}

	/**
	 * @param cancelationCause
	 */
	public void setCancelationCause(MaintenanceCancelationCause cancelationCause) {
		this.cancelationCause = cancelationCause;
	}

	/**
	 * @return cancelationResponsable
	 */
	public Bpu getCancelationResponsable() {
		return cancelationResponsable;
	}

	/**
	 * @param cancelationResponsable
	 */
	public void setCancelationResponsable(Bpu cancelationResponsable) {
		this.cancelationResponsable = cancelationResponsable;
	}

	/**
	 * @return nextMantenanceExecutionDate
	 */
	public Date getNextMantenanceExecutionDate() {
		return nextMantenanceExecutionDate;
	}

	/**
	 * @param nextMantenanceExecutionDate
	 */
	public void setNextMantenanceExecutionDate(Date nextMantenanceExecutionDate) {
		this.nextMantenanceExecutionDate = nextMantenanceExecutionDate;
	}

}
