package org.fourgeeks.gha.domain.mix;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.fourgeeks.gha.domain.AbstractEntity;
import org.fourgeeks.gha.domain.enu.CitizenRelationshipTypeEnum;
import org.fourgeeks.gha.domain.enu.GenderTypeEnum;
import org.fourgeeks.gha.domain.enu.TelephoneTypeEnum;

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
	
	/**Attributes*/
	
	private String firstName; /** length =255 */
	
	@Enumerated(EnumType.STRING)
	private TelephoneTypeEnum telephoneType; /** length =60 */
	
	private String countryCode; /** length =10 */
	private String areaCode; /** length =10 */
	private String telephoneNumber; /** length =16 */
	
	@Enumerated(EnumType.STRING)
	private String idDocumentType; /** length =60 */
	
	@Enumerated(EnumType.STRING)
	private GenderTypeEnum gender; /** length =60 */
	
	private String firstLastName; /** length =255 */
	private String idDocumentNumber; /** length =30 */
	private Timestamp birthDate; /** length =22 */
	private String nationality; /** length =60 */
	
	@Enumerated(EnumType.STRING)
	private CitizenRelationshipTypeEnum citizensRelarionship; /** length =60 */
	private Boolean legalRepresentative; /** length =1 */



	/**
	 * 
	 */
	public CitizenFamily() {
		super();
		// TODO Auto-generated constructor stub
	}

}
