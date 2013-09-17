package org.fourgeeks.gha.domain.mix;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import org.fourgeeks.gha.domain.AbstractEntity;

/**
 * @author emiliot Entidad legal aplicable a todas las personas Naturales y
 *         Jurídicas
 * 
 */
@Entity
@NamedQueries(value = { @NamedQuery(name = "LegalEntity.getAll", query = "SELECT e from LegalEntity e order by e.id") })
public class LegalEntity extends AbstractEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Timestamp dateCreated = new Timestamp(
			new java.util.Date().getTime());

	/**
	 * this field should be used as rif in VE
	 */
	private String identifier;

	/** Fecha y Hora de Creación length =22 */

	/**
	 * @param identifier
	 *            the identifier
	 * 
	 */
	public LegalEntity(String identifier) {
		this.identifier = identifier;
	}

	/**
	 * 
	 */
	public LegalEntity() {
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

	/**
	 * @return the identifier
	 */
	public String getIdentifier() {
		return identifier;
	}

	/**
	 * @param identifier
	 *            the entity identifier
	 */
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
}
