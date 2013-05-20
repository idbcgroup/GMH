package org.fourgeeks.gha.domain.mix;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.fourgeeks.gha.domain.AbstractEntity;

@Entity
public class CitizenFamily extends AbstractEntity {
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

	// private String areaCode;
	// private Date birthDate;
	// private String countryCode;
	// private String firstLastName;
	// private String firstName;
	//
	// // TODO: private gender;
	// // TODO: private idDocumentNumber;
	// // TODO: private idDocumentType;
	// // TODO: private legalRepresentative;
	//
	// private String nationality;
	// private String relationship;
	// private String telephoneNumber;
	// private String telephoneType;
	// String?

	/**
	 * 
	 */
	public CitizenFamily() {
		super();
		// TODO Auto-generated constructor stub
	}

	public long getId() {
		return id;
	}

}
