package org.fourgeeks.gha.domain.mix;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToMany;

import org.fourgeeks.gha.domain.AbstractEntity;
import org.fourgeeks.gha.domain.enu.AvailableTimeTypeEnum;
import org.fourgeeks.gha.domain.enu.ContactRelationTypeEnum;
import org.fourgeeks.gha.domain.enu.ContactTypeEnum;
import org.fourgeeks.gha.domain.enu.TelephoneTypeEnum;

@Entity
public class CitizenContact extends AbstractEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ManyToMany(mappedBy="citizenContacts")
	private Collection <Citizen> citizens;
	
	/**Attributes*/
	
	@Enumerated(EnumType.STRING)
	private AvailableTimeTypeEnum availableTime; /** AVAILABLE_TIME length =60 */
	
	@Enumerated(EnumType.STRING)
	private TelephoneTypeEnum telephoneType; /** TELEPHONE_TYPE length =60 */
	
	@Enumerated(EnumType.STRING)
	private ContactTypeEnum contactType; /** CONTACT_TYPE length =60 */
	
	@Enumerated(EnumType.STRING)
	private ContactRelationTypeEnum contactRelationType; /** CONTACT_RELATION_TYPE length =60 */
	
	private String contactFirstName; /** CONTACT_FIRST_NAME length =255 */
	private String contactLastName; /** CONTACT_LAST_NAME length =255 */
	private String countryCode; /** COUNTRY_CODE length =60 */
	private String areaCode; /** AREA_CODE length =60 */
	private String telephoneNumber; /** TELEPHONE_NUMBER length =16 */
	private String contactEmail; /** CONTACT_EMAIL length =255 */
	private String contactJobPosition; /** CONTACT_JOB_POSITION length =255 */


	
	/**
	 * 
	 */
	public CitizenContact() {
		super();
		// TODO Auto-generated constructor stub
	}
}
