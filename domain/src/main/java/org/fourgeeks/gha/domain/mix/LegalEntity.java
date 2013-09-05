package org.fourgeeks.gha.domain.mix;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;

import org.fourgeeks.gha.domain.AbstractEntity;

/**
 * @author emiliot Entidad legal aplicable a todas las personas Naturales y
 *         Jurídicas
 * 
 */
@Entity
public class LegalEntity extends AbstractEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(/* nullable = false, */columnDefinition = "timestamp without time zone NOT NULL DEFAULT NOW()")
	private Timestamp dateCreated = new Timestamp(
			new java.util.Date().getTime());

	/** Fecha y Hora de Creación length =22 */

	/**
	 * 
	 */
	public LegalEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the dateCreated
	 */
	public Timestamp getDateCreated() {
		return dateCreated;
	}

	/**
	 * @param dateCreated
	 *            the dateCreated to set
	 */
	public void setDateCreated(Timestamp dateCreated) {
		this.dateCreated = dateCreated;
	}
}
