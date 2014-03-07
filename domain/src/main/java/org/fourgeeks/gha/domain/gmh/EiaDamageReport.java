package org.fourgeeks.gha.domain.gmh;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.validation.constraints.NotNull;

import org.fourgeeks.gha.domain.AbstractEntity;
import org.fourgeeks.gha.domain.enu.EiaDamagePriorityEnum;
import org.fourgeeks.gha.domain.enu.EiaDamageStatusEnum;
import org.fourgeeks.gha.domain.enu.EiaStateEnum;
import org.fourgeeks.gha.domain.gar.Bpu;

/**
 * @author naramirez
 * 
 */
@Entity
@NamedQueries(value = { @NamedQuery(name = "EiaDamageReport.getAll", query = "SELECT edr from EiaDamageReport edr order by edr.id") })
public class EiaDamageReport extends AbstractEntity {
	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "eiaFk", nullable = false)
	/** El equipo que se va a reportar como dañado o con falla */
	private Eia eia;

	/** Estatus del daño: Equipo con falla, Equipo dañado */
	@NotNull(message = "damageStatus-not-null")
	private EiaDamageStatusEnum damageStatus;

	/** Condición del equipo: Operativo, Detenido, Dañado permanentemente */
	@NotNull(message = "state-not-null")
	private EiaStateEnum eiaCondition;

	/** Prioridad del daño o falla: Normal, Alta */
	@NotNull(message = "damagePriority-not-null")
	private EiaDamagePriorityEnum priority;

	/** Persona que reportó la falla o daño */
	@ManyToOne
	@NotNull(message = "userWhoReported-not-null")
	@JoinColumn(name = "userWhoReportedFk", nullable = false)
	private Bpu userWhoReported;

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
	public EiaDamageReport() {
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
	 * @return the eia
	 */
	public Eia getEia() {
		return eia;
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
	 * @return the userWhoReported
	 */
	public Bpu getUserWhoReported() {
		return userWhoReported;
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
	 * @param eia
	 *            the eia to set
	 */
	public void setEia(Eia eia) {
		this.eia = eia;
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
	 * @param userWhoReported
	 *            the userWhoReported to set
	 */
	public void setUserWhoReported(Bpu userWhoReported) {
		this.userWhoReported = userWhoReported;
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
