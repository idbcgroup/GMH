package org.fourgeeks.gha.domain.mix;

import java.sql.Timestamp;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.fourgeeks.gha.domain.AbstractEntity;
import org.fourgeeks.gha.domain.enu.LegalEntityTypeEnum;
import org.fourgeeks.gha.domain.ess.SingleSignOnUser;

/**
 * @author emiliot
 * Entidad legal aplicable a todas las personas Naturales y Jurídicas
 *
 */
@Entity
public class LegalEntity extends AbstractEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	// TODO: certificar esta relacion
	@OneToMany(mappedBy = "legalEntity")
	private Collection <LegalEntityAddress> legalEntityAddresses;
	
	@OneToOne(mappedBy = "legalEntity")
	private Institution institution;
	
	@OneToOne(mappedBy = "legalEntity")
	private Citizen citizen;
	
	@OneToOne(mappedBy = "legalEntity")
	private SingleSignOnUser signOnUser;
	
	/**Atributes*/
	
	private String Ueid; /** <PKEY> Universal Entity ID. Identificación Universal de Entidad Legal length =16 */
	
	@Enumerated(EnumType.STRING)
	private LegalEntityTypeEnum legalEntityType; /** Tipo de Entidad Legal length =60 */
	
	private Timestamp dateCreated; /** Fecha y Hora de Creación length =22 */

	/**
	 * 
	 */
	public LegalEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
}
