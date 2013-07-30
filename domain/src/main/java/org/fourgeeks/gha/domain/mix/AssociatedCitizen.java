package org.fourgeeks.gha.domain.mix;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.fourgeeks.gha.domain.AbstractEntity;
import org.fourgeeks.gha.domain.enu.CitizenRelationshipTypeEnum;

@Entity
public class AssociatedCitizen extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "citizenToFk")
	private Citizen citizenTo;

	@ManyToOne
	@JoinColumn(name = "citizenFromFk")
	private Citizen citizenFrom;

	/** Attributes */

	private CitizenRelationshipTypeEnum citizensRelarionship;
	/** length =60 */
	private Boolean legalRepresentative;
	/** length =1 */

}
