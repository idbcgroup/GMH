package org.fourgeeks.gha.domain.mix;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import org.fourgeeks.gha.domain.AbstractEntity;

@Entity
public class LegalEntityAddress extends AbstractEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// TODO: private long addressRelationType;
	@ManyToOne
	private LegalEntity legalEntity;
	private String addressType; // String?
	private String areaCode;
	private String avenueName;
	private String avenueType; // String?
	private String city;
	private String country;
	private String countryCode; // String?
	private String floor;
	private String municipality;
	private String parish;
	private String propertyName;
	private String propertyNumber; // String?
	private String propertyType; // String
	private String referencePlace;
	private String residentialZone; // String?
	private String residentialZoneName;
	private String state;
	private String telephoneNumber;
	private String telephoneType;
	private String zipCode;

	/**
	 * 
	 */
	public LegalEntityAddress() {
		super();
		// TODO Auto-generated constructor stub
	}

	public long getId() {
		return id;
	}

	public String getAddressType() {
		return addressType;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public String getAvenueName() {
		return avenueName;
	}

	public String getAvenueType() {
		return avenueType;
	}

	public String getCity() {
		return city;
	}

	public String getCountry() {
		return country;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public String getFloor() {
		return floor;
	}

	public String getMunicipality() {
		return municipality;
	}

	public String getParish() {
		return parish;
	}

	public String getPropertyName() {
		return propertyName;
	}

	public String getPropertyNumber() {
		return propertyNumber;
	}

	public String getPropertyType() {
		return propertyType;
	}

	public String getReferencePlace() {
		return referencePlace;
	}

	public String getResidentialZone() {
		return residentialZone;
	}

	public String getResidentialZoneName() {
		return residentialZoneName;
	}

	public String getState() {
		return state;
	}

	public String getTelephoneNumber() {
		return telephoneNumber;
	}

	public String getTelephoneType() {
		return telephoneType;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setAddressType(String addressType) {
		this.addressType = addressType;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public void setAvenueName(String avenueName) {
		this.avenueName = avenueName;
	}

	public void setAvenueType(String avenueType) {
		this.avenueType = avenueType;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public void setFloor(String floor) {
		this.floor = floor;
	}

	public void setMunicipality(String municipality) {
		this.municipality = municipality;
	}

	public void setParish(String parish) {
		this.parish = parish;
	}

	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}

	public void setPropertyNumber(String propertyNumber) {
		this.propertyNumber = propertyNumber;
	}

	public void setPropertyType(String propertyType) {
		this.propertyType = propertyType;
	}

	public void setReferencePlace(String referencePlace) {
		this.referencePlace = referencePlace;
	}

	public void setResidentialZone(String residentialZone) {
		this.residentialZone = residentialZone;
	}

	public void setResidentialZoneName(String residentialZoneName) {
		this.residentialZoneName = residentialZoneName;
	}

	public void setState(String state) {
		this.state = state;
	}

	public void setTelephoneNumber(String telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}

	public void setTelephoneType(String telephoneType) {
		this.telephoneType = telephoneType;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

}
