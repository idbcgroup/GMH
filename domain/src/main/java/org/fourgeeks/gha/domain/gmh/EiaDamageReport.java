package org.fourgeeks.gha.domain.gmh;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import org.fourgeeks.gha.domain.AbstractEntity;
import org.fourgeeks.gha.domain.enu.EiaDamagePriorityEnum;
import org.fourgeeks.gha.domain.enu.EiaDamageStatusEnum;
import org.fourgeeks.gha.domain.ess.SSOUser;

@Entity
@NamedQuery(name = "EiaDamageReport.getAll", query = "SELECT edr from EiaDamageReport edr order by edr.id")
@NamedQueries(value = { @NamedQuery(name = "EiaDamageReport.findByEiaType", query = "SELECT edr from EiaDamageReport edr join Eia eia WHERE eia.eiaType = :eiaType order by edr.id") })
public class EiaDamageReport extends AbstractEntity {
	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "eiaFk", nullable = false)
	/** El equipo que se va a reportar como dañado o con falla */
	private Eia eia;

	/** Estatus del daño: Equipo con falla, Equipo dañado */
	private EiaDamageStatusEnum damageStatus;

	/** Motivo del daño */
	private String damageMotive;

	/** Fecha y hora de la falla o daño */
	private Date dateTimeDamage;

	/** Persona que reportó la falla o daño */
	@ManyToOne
	@JoinColumn(name = "userWhoReportedFk", nullable = false)
	private SSOUser userWhoReported;

	/** Persona que hizo el registro de la falla o daño */
	@ManyToOne
	@JoinColumn(name = "userWhoRegisteredFk", nullable = false)
	private SSOUser userWhoRegistered;

	/** Prioridad del daño o falla: Normal, Alta */
	private EiaDamagePriorityEnum priority;

	public EiaDamageReport() {
		// TODO Auto-generated constructor stub
	}

	public Eia getEia() {
		return eia;
	}

	public void setEia(Eia eia) {
		this.eia = eia;
	}

	public EiaDamageStatusEnum getDamageStatus() {
		return damageStatus;
	}

	public void setDamageStatus(EiaDamageStatusEnum damageStatus) {
		this.damageStatus = damageStatus;
	}

	public String getDamageMotive() {
		return damageMotive;
	}

	public void setDamageMotive(String damageMotive) {
		this.damageMotive = damageMotive;
	}

	public Date getDateTimeDamage() {
		return dateTimeDamage;
	}

	public void setDateTimeDamage(Date dateTimeDamage) {
		this.dateTimeDamage = dateTimeDamage;
	}

	public SSOUser getUserWhoReported() {
		return userWhoReported;
	}

	public void setUserWhoReported(SSOUser userWhoReported) {
		this.userWhoReported = userWhoReported;
	}

	public SSOUser getUserWhoRegistered() {
		return userWhoRegistered;
	}

	public void setUserWhoRegistered(SSOUser userWhoRegistered) {
		this.userWhoRegistered = userWhoRegistered;
	}

	public EiaDamagePriorityEnum getPriority() {
		return priority;
	}

	public void setPriority(EiaDamagePriorityEnum priority) {
		this.priority = priority;
	}
}
