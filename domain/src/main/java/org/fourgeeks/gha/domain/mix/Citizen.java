package org.fourgeeks.gha.domain.mix;

import java.io.Serializable;
import java.sql.Date;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import org.fourgeeks.gha.domain.AbstractEntity;

@Entity
public class Citizen extends AbstractEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String alternativeEmail;
	private String birthCity;
	private String birthCountry;
	private Date birthDate;
	private String birthMunicipality;
	private String birthParish;
	private String birthState;
	private String bloodGroup;
	private String etnicGroup;
	private String firstLastName;
	private String firstName;
	private String fiscalRegistrationNumber;
	private String gender;
	
	// TODO: private long idDocumentNumber;
	// TODO: private long idDocumentType;
	// TODO: private ? illiterate;
	
	private String instructionDegree;
	private String jobTypeDescription;
	
	// TODO: private jobTypeDescription;
	
	private String maritalStatus;
	private String migratoryStatus;
	private String mixStatus;
	private String nationality;
	private String nationalityType;
	private String occupation;
	private String primaryEmail;
	private String race;
	private String religion;
	private String rhFactor;
	private String secondLastName;
	private String secondName;
	
	//TODO: private String socialSecurityNumber;
	
	private String studyReadingWriting;
	
	//TODO: private Timestamp timeInCountry;
	//TODO: private ? timeUnit;
	
	private String transportationType;
	private String vehicleOwner;
	private String vehicleType;
	
	@OneToMany(mappedBy="citizen")
	private Collection <CitizenEducation> citizenEducation;
	
	@ManyToMany
	private Collection <CitizenFamily> citizenFamily;
	
	@OneToMany(mappedBy="citizen")
	private Collection <CitizenPicture> citizenPictures;
	
	@ManyToMany
	private Collection <CitizenContact> citizenContacts;
	
	@OneToMany(mappedBy="citizen")
	private Collection <CitizenNotification> citizenNotifications;
	
	@ManyToMany
	private Collection <AssociatedCitizenId> associatedCitizens;
	
	@OneToMany(mappedBy="citizen")
	private Collection <PhysicalCharacteristicsCode> physicalCharacteristicsCodes;
	
	@OneToMany(mappedBy="citizen")
	private Collection <DuplicatedCitizenId> duplicatedCitizenIds;
	
	/**
	 * Constructor
	 */
	public Citizen() {
		//super();
		// TODO Auto-generated constructor stub
	}
	public long getId() {
		return id;
	}
	public String getAlternativeEmail() {
		return alternativeEmail;
	}
	public String getBirthCity() {
		return birthCity;
	}
	public String getBirthCountry() {
		return birthCountry;
	}
	public Date getBirthDate() {
		return birthDate;
	}
	public String getBirthMunicipality() {
		return birthMunicipality;
	}
	public String getBirthParish() {
		return birthParish;
	}
	public String getBirthState() {
		return birthState;
	}
	public String getBloodGroup() {
		return bloodGroup;
	}
	public String getEtnicGroup() {
		return etnicGroup;
	}
	public String getFirstLastName() {
		return firstLastName;
	}
	public String getFirstName() {
		return firstName;
	}
	public String getFiscalRegistrationNumber() {
		return fiscalRegistrationNumber;
	}
	public String getGender() {
		return gender;
	}
	public String getInstructionDegree() {
		return instructionDegree;
	}
	public String getJobTypeDescription() {
		return jobTypeDescription;
	}
	public String getMaritalStatus() {
		return maritalStatus;
	}
	public String getMigratoryStatus() {
		return migratoryStatus;
	}
	public String getMixStatus() {
		return mixStatus;
	}
	public String getNationality() {
		return nationality;
	}
	public String getNationalityType() {
		return nationalityType;
	}
	public String getOccupation() {
		return occupation;
	}
	public String getPrimaryEmail() {
		return primaryEmail;
	}
	public String getRace() {
		return race;
	}
	public String getReligion() {
		return religion;
	}
	public String getRhFactor() {
		return rhFactor;
	}
	public String getSecondLastName() {
		return secondLastName;
	}
	public String getSecondName() {
		return secondName;
	}
	public String getStudyReadingWriting() {
		return studyReadingWriting;
	}
	public String getTransportationType() {
		return transportationType;
	}
	public String getVehicleOwner() {
		return vehicleOwner;
	}
	public String getVehicleType() {
		return vehicleType;
	}
	public void setId(long id) {
		this.id = id;
	}
	public void setAlternativeEmail(String alternativeEmail) {
		this.alternativeEmail = alternativeEmail;
	}
	public void setBirthCity(String birthCity) {
		this.birthCity = birthCity;
	}
	public void setBirthCountry(String birthCountry) {
		this.birthCountry = birthCountry;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	public void setBirthMunicipality(String birthMunicipality) {
		this.birthMunicipality = birthMunicipality;
	}
	public void setBirthParish(String birthParish) {
		this.birthParish = birthParish;
	}
	public void setBirthState(String birthState) {
		this.birthState = birthState;
	}
	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}
	public void setEtnicGroup(String etnicGroup) {
		this.etnicGroup = etnicGroup;
	}
	public void setFirstLastName(String firstLastName) {
		this.firstLastName = firstLastName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public void setFiscalRegistrationNumber(String fiscalRegistrationNumber) {
		this.fiscalRegistrationNumber = fiscalRegistrationNumber;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public void setInstructionDegree(String instructionDegree) {
		this.instructionDegree = instructionDegree;
	}
	public void setJobTypeDescription(String jobTypeDescription) {
		this.jobTypeDescription = jobTypeDescription;
	}
	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}
	public void setMigratoryStatus(String migratoryStatus) {
		this.migratoryStatus = migratoryStatus;
	}
	public void setMixStatus(String mixStatus) {
		this.mixStatus = mixStatus;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	public void setNationalityType(String nationalityType) {
		this.nationalityType = nationalityType;
	}
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
	public void setPrimaryEmail(String primaryEmail) {
		this.primaryEmail = primaryEmail;
	}
	public void setRace(String race) {
		this.race = race;
	}
	public void setReligion(String religion) {
		this.religion = religion;
	}
	public void setRhFactor(String rhFactor) {
		this.rhFactor = rhFactor;
	}
	public void setSecondLastName(String secondLastName) {
		this.secondLastName = secondLastName;
	}
	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}
	public void setStudyReadingWriting(String studyReadingWriting) {
		this.studyReadingWriting = studyReadingWriting;
	}
	public void setTransportationType(String transportationType) {
		this.transportationType = transportationType;
	}
	public void setVehicleOwner(String vehicleOwner) {
		this.vehicleOwner = vehicleOwner;
	}
	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}
	
	
}
