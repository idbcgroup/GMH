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

	// // TODO: certificar esta relacion
	// @OneToMany(mappedBy = "legalEntity")
	// private Collection <LegalEntityAddress> legalEntityAddresses;

	// @OneToOne(mappedBy = "legalEntity")
	// private Institution institution;
	//
	// @OneToOne(mappedBy = "legalEntity")
	// private Citizen citizen;
	//
	// @OneToOne(mappedBy = "legalEntity")
	// private SingleSignOnUser signOnUser;

	/** Atributes */

	// private String Ueid;
	/**
	 * <PKEY> Universal Entity ID. Identificación Universal de Entidad Legal
	 * length =16
	 */

	// private LegalEntityTypeEnum legalEntityType;
	// /** Tipo de Entidad Legal length =60 */
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

	// /**
	// * @return the ueid
	// */
	// public String getUeid() {
	// return Ueid;
	// }

	// /**
	// * @param ueid
	// * the ueid to set
	// */
	// public void setUeid(String ueid) {
	// Ueid = ueid;
	// }

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
