package org.fourgeeks.gha.domain.mix;

import java.sql.Time;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.fourgeeks.gha.domain.AbstractEntity;

@Entity
public class InstitutionContacts extends AbstractEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "institutionFk")
	private Institution institution;

	private String alternativeEmail;
	private String areaCode;
	private Time availableTime; // Time?
	private String contactsType; // String?
	private String countryCode; // String?
	private String firstLastName;
	private String firstName;
	private String jobPosition;
	private String primaryEmail;
	private String telephoneNumber;
	private String telephoneType; // String?

	/**
	 * 
	 */
	public InstitutionContacts() {
		super();
		// TODO Auto-generated constructor stub
	}

	public long getId() {
		return id;
	}

	public String getAlternativeEmail() {
		return alternativeEmail;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public Time getAvailableTime() {
		return availableTime;
	}

	public String getContactsType() {
		return contactsType;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public String getFirstLastName() {
		return firstLastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getJobPosition() {
		return jobPosition;
	}

	public String getPrimaryEmail() {
		return primaryEmail;
	}

	public String getTelephoneNumber() {
		return telephoneNumber;
	}

	public String getTelephoneType() {
		return telephoneType;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setAlternativeEmail(String alternativeEmail) {
		this.alternativeEmail = alternativeEmail;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public void setAvailableTime(Time availableTime) {
		this.availableTime = availableTime;
	}

	public void setContactsType(String contactsType) {
		this.contactsType = contactsType;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public void setFirstLastName(String firstLastName) {
		this.firstLastName = firstLastName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setJobPosition(String jobPosition) {
		this.jobPosition = jobPosition;
	}

	public void setPrimaryEmail(String primaryEmail) {
		this.primaryEmail = primaryEmail;
	}

	public void setTelephoneNumber(String telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}

	public void setTelephoneType(String telephoneType) {
		this.telephoneType = telephoneType;
	}

}
