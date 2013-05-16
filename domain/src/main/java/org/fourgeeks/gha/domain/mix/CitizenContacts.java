package org.fourgeeks.gha.domain.mix;

import java.io.Serializable;
import java.sql.Time;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class CitizenContacts implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String areaCode;
	private Time availableTime; //Time?
	private String contactEmail;
	private String contactFirstName;
	private String contactJobPosition;
	private String contactLastName;
	private String contactRelationType; //String?
	private String contactType;
	private String countryCode; //String?
	private String telephoneNumber;
	private String telephoneType; //String?
	/**
	 * 
	 */
	public CitizenContacts() {
		super();
		// TODO Auto-generated constructor stub
	}
	public long getId() {
		return id;
	}
	public String getAreaCode() {
		return areaCode;
	}
	public Time getAvailableTime() {
		return availableTime;
	}
	public String getContactEmail() {
		return contactEmail;
	}
	public String getContactFirstName() {
		return contactFirstName;
	}
	public String getContactJobPosition() {
		return contactJobPosition;
	}
	public String getContactLastName() {
		return contactLastName;
	}
	public String getContactRelationType() {
		return contactRelationType;
	}
	public String getContactType() {
		return contactType;
	}
	public String getCountryCode() {
		return countryCode;
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
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
	public void setAvailableTime(Time availableTime) {
		this.availableTime = availableTime;
	}
	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}
	public void setContactFirstName(String contactFirstName) {
		this.contactFirstName = contactFirstName;
	}
	public void setContactJobPosition(String contactJobPosition) {
		this.contactJobPosition = contactJobPosition;
	}
	public void setContactLastName(String contactLastName) {
		this.contactLastName = contactLastName;
	}
	public void setContactRelationType(String contactRelationType) {
		this.contactRelationType = contactRelationType;
	}
	public void setContactType(String contactType) {
		this.contactType = contactType;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	public void setTelephoneNumber(String telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}
	public void setTelephoneType(String telephoneType) {
		this.telephoneType = telephoneType;
	}
	
	
}
