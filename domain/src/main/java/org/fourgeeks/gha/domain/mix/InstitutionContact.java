package org.fourgeeks.gha.domain.mix;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.fourgeeks.gha.domain.AbstractEntity;
import org.fourgeeks.gha.domain.enu.ContactTypeEnum;
import org.fourgeeks.gha.domain.enu.TelephoneTypeEnum;

@Entity
public class InstitutionContact extends AbstractEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "institutionFk")
	private Institution institution;

	/** Attributes */
	private String firstName;
	/** FIRST_NAME length =255 */
	private String availableTime;
	/** AVAILABLE_TIME length =20 */

	private TelephoneTypeEnum telephoneType;
	/** TELEPHONE_TYPE length =20 */

	private String countryCode;
	/** COUNTRY_CODE length =10 */
	private String areaCode;
	/** AREA_CODE length =10 */
	private String telephoneNumber;
	/** TELEPHONE_NUMBER length =16 */
	private String firstLastName;
	/** FIRST_LAST_NAME length =255 */
	private String primaryEmail;
	/** PRIMARY_EMAIL length =255 */
	private String alternativeEmail;
	/** ALTERNATIVE_EMAIL length =255 */

	private ContactTypeEnum contactType;
	/** CONTACTS_TYPE length =255 */

	private String jobPosition;

	/** JOB_POSITION length =255 */

	/**
	 * 
	 */
	public InstitutionContact() {
		super();
		// TODO Auto-generated constructor stub
	}
}
