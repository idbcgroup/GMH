package org.fourgeeks.gha.domain.mix;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class CitizenFamily implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String areaCode;
	private Date birthDate;
	private String countryCode;
	private String firstLastName;
	private String firstName;
	
	//TODO: private gender;
	//TODO: private idDocumentNumber;
	//TODO: private idDocumentType;
	//TODO: private legalRepresentative;
	
	private String nationality;
	private String relationship;
	private String telephoneNumber;
	private String telephoneType; //String?
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
	public String getAreaCode() {
		return areaCode;
	}
	public Date getBirthDate() {
		return birthDate;
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
	public String getNationality() {
		return nationality;
	}
	public String getRelationship() {
		return relationship;
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
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
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
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}
	public void setTelephoneNumber(String telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}
	public void setTelephoneType(String telephoneType) {
		this.telephoneType = telephoneType;
	}
	
	
}
