package org.fourgeeks.gha.domain.mix;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.fourgeeks.gha.domain.AbstractEntity;
import org.fourgeeks.gha.domain.enu.DocumentTypeEnum;

@Entity
public class Citizen extends AbstractEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@OneToOne
	@JoinColumn(name = "legalEntityFk")
	private LegalEntity legalEntity;
	
	private DocumentTypeEnum idType; /** Tipo de documento de Identificación del ciudadano length =60 */
	private String idNumber; /** No de documento de Identificación del ciudadano length =30 */
	
	private String firstName; /** 1er Nombre del ciudadano length =255 */
	private String firstLastName; /** Apellido del ciudadano length =255 */
	private String secondName; /** 2do Nombre del ciudadano length =255 */
	private String secondLastName; /** 2do Apellido del ciudadano length =255 */
	
	private String gender; /** Sexo del ciudadano length =60 */
	private Date birthDate; /** Fecha Nacimiento del ciudadano length =12 */
	private String nationality; /** Nacionalidad actual del ciudadano length =60 */


	/**
	 * Constructor
	 */
	public Citizen() {
		// TODO Auto-generated constructor stub
	}


	/**
	 * @param legalEntity
	 * @param idType
	 * @param idNumber
	 * @param firstName
	 * @param firstLastName
	 * @param secondName
	 * @param secondLastName
	 * @param gender
	 * @param birthDate
	 * @param nationality
	 */
	public Citizen(LegalEntity legalEntity, DocumentTypeEnum idType,
			String idNumber, String firstName, String firstLastName,
			String secondName, String secondLastName, String gender,
			Date birthDate, String nationality) {
		this.legalEntity = legalEntity;
		this.idType = idType;
		this.idNumber = idNumber;
		this.firstName = firstName;
		this.firstLastName = firstLastName;
		this.secondName = secondName;
		this.secondLastName = secondLastName;
		this.gender = gender;
		this.birthDate = birthDate;
		this.nationality = nationality;
	}


	public LegalEntity getLegalEntity() {
		return legalEntity;
	}


	public void setLegalEntity(LegalEntity legalEntity) {
		this.legalEntity = legalEntity;
	}


	public DocumentTypeEnum getIdType() {
		return idType;
	}


	public void setIdType(DocumentTypeEnum idType) {
		this.idType = idType;
	}


	public String getIdNumber() {
		return idNumber;
	}


	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getFirstLastName() {
		return firstLastName;
	}


	public void setFirstLastName(String firstLastName) {
		this.firstLastName = firstLastName;
	}


	public String getSecondName() {
		return secondName;
	}


	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}


	public String getSecondLastName() {
		return secondLastName;
	}


	public void setSecondLastName(String secondLastName) {
		this.secondLastName = secondLastName;
	}


	public String getGender() {
		return gender;
	}


	public void setGender(String gender) {
		this.gender = gender;
	}


	public Date getBirthDate() {
		return birthDate;
	}


	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}


	public String getNationality() {
		return nationality;
	}


	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

}
