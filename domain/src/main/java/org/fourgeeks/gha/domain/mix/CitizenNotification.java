package org.fourgeeks.gha.domain.mix;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.fourgeeks.gha.domain.AbstractEntity;
import org.fourgeeks.gha.domain.enu.AvailableTimeTypeEnum;
import org.fourgeeks.gha.domain.enu.TelephoneTypeEnum;

@Entity
public class CitizenNotification extends AbstractEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "citizenFk")
	private Citizen citizen;

	/** Attributes */

	private String firstName;
	/** FIRST_NAME length =255 */

	private AvailableTimeTypeEnum availableTime;
	/** AVAILABLE_TIME length =60 */

	private TelephoneTypeEnum telephoneType;
	/** TELEPHONE_TYPE length =60 */

	private String countryCode;
	/** COUNTRY_CODE length =60 */
	private String areaCode;
	/** AREA_CODE length =60 */
	private String telephoneNumber;
	/** TELEPHONE_NUMBER length =16 */
	private String firstLastName;
	/** FIRST_LAST_NAME length =255 */
	private String primaryEmail;
	/** PRIMARY_EMAIL length =255 */

	private String relationship;

	/** RELATIONSHIP length =60 */

	/**
	 * 
	 */
	public CitizenNotification() {
		super();
		// TODO Auto-generated constructor stub
	}
}
