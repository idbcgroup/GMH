/**
 * 
 */
package org.fourgeeks.gha.domain.gmh;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.fourgeeks.gha.domain.AbstractEntity;
import org.fourgeeks.gha.domain.glm.ExternalProvider;

/**
 * @author emiliot
 * 
 */

@Entity
public class EiaMaintenancePlanification extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "eiaFk")
	private Eia eia;

	private Date scheduledDate;
	private ExternalProvider provider;
	private Date deliverDate;
	private Date acceptationDate;

	/**
	 * 
	 */
	public EiaMaintenancePlanification() {
		// TODO Auto-generated constructor stub
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
}
