package org.fourgeeks.gha.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Entity implementation class for Entity: TimerParams
 * 
 */
@Entity
@Table(schema = "conf")
@NamedQueries(value = { @NamedQuery(name = "TimerParams.getAll", query = "SELECT e from TimerParams e order by e.code") })
public class TimerParams extends AbstractCodeEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	/** */
	public static final long NO_TIME = -1;

	@NotNull
	private String jndiProcessorName;

	private long lastTimeEffectuated;
	private int seconds;
	private int minutes;
	private int hours;
	private int days;
	private int years;

	/**
	 * 
	 */
	public TimerParams() {
		super();
		lastTimeEffectuated = NO_TIME;
	}

	/**
	 * @return the days
	 */
	public int getDays() {
		return days;
	}

	/**
	 * @return the hours
	 */
	public int getHours() {
		return hours;
	}

	/**
	 * @return the jndiProcessorName
	 */
	public String getJndiProcessorName() {
		return jndiProcessorName;
	}

	/**
	 * @return the lastTimeEffectued
	 */
	public long getLastTimeEffectuated() {
		return lastTimeEffectuated;
	}

	/**
	 * @return the minutes
	 */
	public int getMinutes() {
		return minutes;
	}

	/**
	 * @return the seconds
	 */
	public int getSeconds() {
		return seconds;
	}

	/**
	 * @param days
	 *            the days to set
	 */
	public void setDays(int days) {
		this.days = days;
	}

	/**
	 * @param hours
	 *            the hours to set
	 */
	public void setHours(int hours) {
		this.hours = hours;
	}

	/**
	 * @param jndiProcessorName
	 *            the jndiProcessorName to set
	 */
	public void setJndiProcessorName(String jndiProcessorName) {
		this.jndiProcessorName = jndiProcessorName;
	}

	/**
	 * @param lastTimeEffectuated
	 *            the lastTimeEffectuated to set
	 */
	public void setLastTimeEffectuated(long lastTimeEffectuated) {
		this.lastTimeEffectuated = lastTimeEffectuated;
	}

	/**
	 * @param minutes
	 *            the minutes to set
	 */
	public void setMinutes(int minutes) {
		this.minutes = minutes;
	}

	/**
	 * @param seconds
	 *            the seconds to set
	 */
	public void setSeconds(int seconds) {
		this.seconds = seconds;
	}

	public int getYears() {
		return years;
	}

	public void setYears(int years) {
		this.years = years;
	}

}
