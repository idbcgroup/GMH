package org.fourgeeks.gha.domain.mix;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.fourgeeks.gha.domain.AbstractEntity;

@Entity
public class CitizenNotification extends AbstractEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String firstLastName;
	private String firstName;
	private String telephone;
	private String countryCode;
	private String areaCode;
	private String telephoneNumber;
	// TODO: availableTime
	private String primaryEmail;
	
	@ManyToOne
	@JoinColumn(name = "citizenFk")
	private Citizen citizen;
	
	/**
	 * 
	 */
	public CitizenNotification() {
		super();
		// TODO Auto-generated constructor stub
	}
	public long getId() {
		return id;
	}
	public Citizen getCitizen() {
		return citizen;
	}
	public String getFirstLastName() {
		return firstLastName;
	}
	public String getFirstName() {
		return firstName;
	}
	public String getTelephone() {
		return telephone;
	}
	public String getCountryCode() {
		return countryCode;
	}
	public String getAreaCode() {
		return areaCode;
	}
	public String getTelephoneNumber() {
		return telephoneNumber;
	}
	public String getPrimaryEmail() {
		return primaryEmail;
	}
	public void setId(long id) {
		this.id = id;
	}
	public void setCitizen(Citizen citizen) {
		this.citizen = citizen;
	}
	public void setFirstLastName(String firstLastName) {
		this.firstLastName = firstLastName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
	public void setTelephoneNumber(String telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}
	public void setPrimaryEmail(String primaryEmail) {
		this.primaryEmail = primaryEmail;
	}
	
	
}
