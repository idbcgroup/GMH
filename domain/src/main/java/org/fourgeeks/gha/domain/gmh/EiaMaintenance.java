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
// @DiscriminatorValue("eiaMaintenance")
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
	 * @return the acceptationDate
	 */
	public Date getAcceptationDate() {
		return acceptationDate;
	}

	/**
	 * @return the beginningTimestamp
	 */
	public Timestamp getBeginningTimestamp() {
		return beginningTimestamp;
	}

	/**
	 * @return the cancellation cause
	 */
	public MaintenanceCancelationCause getCancelationCause() {
		return cancelationCause;
	}

	/**
	 * @return cancelationResponsable
	 */
	public Bpu getCancelationResponsable() {
		return cancelationResponsable;
	}

	/**
	 * @return the deliverDate
	 */
	public Date getDeliverDate() {
		return deliverDate;
	}

	/**
	 * @return the effectivePoT
	 */
	public TimePeriodEnum getEffectivePoT() {
		return effectivePoT;
	}

	/**
	 * @return the effectiveTime
	 */
	public int getEffectiveTime() {
		return effectiveTime;
	}

	/**
	 * @return the finalEiaState
	 */
	public EiaStateEnum getFinalEiaState() {
		return finalEiaState;
	}

	/**
	 * @return the finishTimestamp
	 */
	public Timestamp getFinishTimestamp() {
		return finishTimestamp;
	}

	/**
	 * @return the initialEiaState
	 */
	public EiaStateEnum getInitialEiaState() {
		return initialEiaState;
	}

	/**
	 * @return nextMantenanceExecutionDate
	 */
	public Date getNextMantenanceExecutionDate() {
		return nextMantenanceExecutionDate;
	}

	/**
	 * @return the provider
	 */
	public Bsp getProvider() {
		return provider;
	}

	/**
	 * @return the requestNumber
	 */
	public String getRequestNumber() {
		return requestNumber;
	}

	/**
	 * @return the scheduledDate
	 */
	public Date getScheduledDate() {
		return scheduledDate;
	}

	/**
	 * @return the status
	 */
	public EiaMaintenanceState getState() {
		return state;
	}

	/**
	 * @return the technician
	 */
	public Bpu getTechnician() {
		return technician;
	}

	/**
	 * @param acceptationDate
	 *            the acceptationDate to set
	 */
	public void setAcceptationDate(final Date acceptationDate) {
		this.acceptationDate = acceptationDate;
	}

	/**
	 * @param beginningTimestamp
	 *            the beginningTimestamp to set
	 */
	public void setBeginningTimestamp(final Timestamp beginningTimestamp) {
		this.beginningTimestamp = beginningTimestamp;
	}

	/**
	 * @param cancelationCause
	 */
	public void setCancelationCause(
			final MaintenanceCancelationCause cancelationCause) {
		this.cancelationCause = cancelationCause;
	}

	/**
	 * @param cancelationResponsable
	 */
	public void setCancelationResponsable(final Bpu cancelationResponsable) {
		this.cancelationResponsable = cancelationResponsable;
	}

	/**
	 * @param deliverDate
	 *            the deliverDate to set
	 */
	public void setDeliverDate(final Date deliverDate) {
		this.deliverDate = deliverDate;
	}

	/**
	 * @param effectivePoT
	 *            the effectivePoT to set
	 */
	public void setEffectivePoT(final TimePeriodEnum effectivePoT) {
		this.effectivePoT = effectivePoT;
	}

	/**
	 * @param effectiveTime
	 *            the effectiveTime to set
	 */
	public void setEffectiveTime(final int effectiveTime) {
		this.effectiveTime = effectiveTime;
	}

	/**
	 * @param finalEiaState
	 *            the finalEiaState to set
	 */
	public void setFinalEiaState(final EiaStateEnum finalEiaState) {
		this.finalEiaState = finalEiaState;
	}

	/**
	 * @param finishTimestamp
	 *            the finishTimestamp to set
	 */
	public void setFinishTimestamp(final Timestamp finishTimestamp) {
		this.finishTimestamp = finishTimestamp;
	}

	/**
	 * @param initialEiaState
	 *            the initialEiaState to set
	 */
	public void setInitialEiaState(final EiaStateEnum initialEiaState) {
		this.initialEiaState = initialEiaState;
	}

	/**
	 * @param nextMantenanceExecutionDate
	 */
	public void setNextMantenanceExecutionDate(
			final Date nextMantenanceExecutionDate) {
		this.nextMantenanceExecutionDate = nextMantenanceExecutionDate;
	}

	/**
	 * @param provider
	 *            the provider to set
	 */
	public void setProvider(final Bsp provider) {
		this.provider = provider;
	}

	/**
	 * @param requestNumber
	 *            the requestNumber to set
	 */
	public void setRequestNumber(final String requestNumber) {
		this.requestNumber = requestNumber;
	}

	/**
	 * @param scheduledDate
	 *            the scheduledDate to set
	 */
	public void setScheduledDate(final Date scheduledDate) {
		this.scheduledDate = scheduledDate;
	}

	/**
	 * @param state
	 *            the status to set
	 */
	public void setState(final EiaMaintenanceState state) {
		this.state = state;
	}

	/**
	 * @param technician
	 *            the technician to set
	 */
	public void setTechnician(final Bpu technician) {
		this.technician = technician;
	}

}
