package org.fourgeeks.gha.domain.gmh;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import org.fourgeeks.gha.domain.AbstractEntity;
import org.fourgeeks.gha.domain.enu.EiaDamagePriorityEnum;
import org.fourgeeks.gha.domain.enu.EiaDamageStatusEnum;
import org.fourgeeks.gha.domain.enu.EiaStateEnum;
import org.fourgeeks.gha.domain.gar.Bpu;

/**
 * @author naramirez,eduardoguerere
 * 
 */
@Entity
@NamedQueries(value = { @NamedQuery(name = "GlaLog.getAll", query = "SELECT edr from GlaLog edr order by edr.id") })
public class GlaLog extends AbstractEntity {
	private static final long serialVersionUID = 1L;

	/** Estatus del daño: Equipo con falla, Equipo dañado */
	@NotNull(message = "damageStatus-not-null")
	private EiaDamageStatusEnum damageStatus;

	/** Condición del equipo: Operativo, Detenido, Dañado permanentemente */
	@NotNull(message = "eia-condition-not-null")
	private EiaStateEnum eiaCondition;

	/** Prioridad del daño o falla: Normal, Alta */
	@NotNull(message = "damagePriority-not-null")
	private EiaDamagePriorityEnum priority;

	@OneToOne
	@JoinColumn(name = "resourceFk")
	private ServiceAndResource serviceResource;

	/**
	 * @return the serviceResource
	 */
	public ServiceAndResource getServiceResource() {
		return serviceResource;
	}

	/**
	 * @param serviceResource
	 *            the serviceResource to set
	 */
	public void setServiceResource(ServiceAndResource serviceResource) {
		this.serviceResource = serviceResource;
	}

	/** Persona que hizo el registro de la falla o daño */
	@ManyToOne
	@NotNull(message = "userWhoRegistered-not-null")
	@JoinColumn(name = "userWhoRegisteredFk", nullable = false)
	private Bpu userWhoRegistered;

	/** Motivo del daño */
	private String damageMotive;

	/** Fecha y hora de la falla o daño */
	private Timestamp dateTimestamp;

	/**
	 * 
	 */
	public GlaLog() {
	}

	/**
	 * @return the damageMotive
	 */
	public String getDamageMotive() {
		return damageMotive;
	}

	/**
	 * @return the damageStatus
	 */
	public EiaDamageStatusEnum getDamageStatus() {
		return damageStatus;
	}

	/**
	 * @return the dateTimestamp
	 */
	public Timestamp getDateTimestamp() {
		return dateTimestamp;
	}

	/**
	 * @return the priority
	 */
	public EiaDamagePriorityEnum getPriority() {
		return priority;
	}

	/**
	 * @return the userWhoRegistered
	 */
	public Bpu getUserWhoRegistered() {
		return userWhoRegistered;
	}

	/**
	 * 
	 * @return the eiaCondition
	 */
	public EiaStateEnum getEiaCondition() {
		return eiaCondition;
	}

	/**
	 * @param damageMotive
	 *            the damageMotive to set
	 */
	public void setDamageMotive(String damageMotive) {
		this.damageMotive = damageMotive;
	}

	/**
	 * @param damageStatus
	 *            the damageStatus to set
	 */
	public void setDamageStatus(EiaDamageStatusEnum damageStatus) {
		this.damageStatus = damageStatus;
	}

	/**
	 * @param dateTimestamp
	 *            the dateTimestamp to set
	 */
	public void setDateTimestamp(Timestamp dateTimestamp) {
		this.dateTimestamp = dateTimestamp;
	}

	/**
	 * @param priority
	 *            the priority to set
	 */
	public void setPriority(EiaDamagePriorityEnum priority) {
		this.priority = priority;
	}

	/**
	 * @param userWhoRegistered
	 *            the userWhoRegistered to set
	 */
	public void setUserWhoRegistered(Bpu userWhoRegistered) {
		this.userWhoRegistered = userWhoRegistered;
	}

	/**
	 * 
	 * @param eiaCondition
	 *            the eiaCondition to set
	 */
	public void setEiaCondition(EiaStateEnum eiaCondition) {
		this.eiaCondition = eiaCondition;
	}

}
