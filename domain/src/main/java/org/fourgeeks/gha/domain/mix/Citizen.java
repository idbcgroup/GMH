package org.fourgeeks.gha.domain.mix;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.fourgeeks.gha.domain.AbstractEntity;
import org.fourgeeks.gha.domain.enu.DocumentTypeEnum;
import org.fourgeeks.gha.domain.enu.GenderTypeEnum;

/**
 * @author alacret Entity that represents a citizen
 */
@Entity
@NamedQueries(value = { @NamedQuery(name = "Citizen.getAll", query = "SELECT e from Citizen e order by e.firstLastName") })
public class Citizen extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotNull
	@OneToOne(cascade = { CascadeType.MERGE })
	@JoinColumn(name = "legalEntityFk", nullable = false)
	private LegalEntity legalEntity;

	private DocumentTypeEnum idType;
	/** Tipo de documento de Identificación del ciudadano length =60 */

	private String idNumber;
	/** No de documento de Identificación del ciudadano length =30 */

	@Size(max = 20)
	private String firstName;
	/** 1er Nombre del ciudadano length =255 */
	@Size(max = 20)
	private String firstLastName;
	/** Apellido del ciudadano length =255 */
	@Size(max = 20)
	private String secondName;
	/** 2do Nombre del ciudadano length =255 */
	@Size(max = 20)
	private String secondLastName;
	/** 2do Apellido del ciudadano length =255 */

	@NotNull(message = "gender-not-null")
	@Column(nullable = false)
	private GenderTypeEnum gender;
	/** Sexo del ciudadano length =60 */

	private Date birthDate;
	/** Fecha Nacimiento del ciudadano length =12 */
	private String nationality;
	/** Nacionalidad actual del ciudadano length =60 */

	@Size(max = 254)
	private String primaryEmail;
	/** Correo primario del Ciudadano length =255 */
	@Size(max = 254)
	private String alternativeEmail;

	/** Correo alterno del Ciudadano length =255 */

	/**
	 * Constructor
	 */
	public Citizen() {
	}

	/**
	 * @param legalEntity
	 * @param gender
	 */
	public Citizen(LegalEntity legalEntity, GenderTypeEnum gender) {
		super();
		this.legalEntity = legalEntity;
		this.gender = gender;
	}

	/**
	 * @return the legal Entity
	 */
	public LegalEntity getLegalEntity() {
		return legalEntity;
	}

	/**
	 * @param legalEntity
	 */
	public void setLegalEntity(LegalEntity legalEntity) {
		this.legalEntity = legalEntity;
	}

	/**
	 * @return the documenttype
	 */
	public DocumentTypeEnum getIdType() {
		return idType;
	}

	/**
	 * @param idType
	 */
	public void setIdType(DocumentTypeEnum idType) {
		this.idType = idType;
	}

	/**
	 * @return the id number
	 */
	public String getIdNumber() {
		return idNumber;
	}

	/**
	 * @param idNumber
	 */
	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	/**
	 * @return the first name
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the first last name
	 */
	public String getFirstLastName() {
		return firstLastName;
	}

	/**
	 * @param firstLastName
	 */
	public void setFirstLastName(String firstLastName) {
		this.firstLastName = firstLastName;
	}

	/**
	 * @return the second name
	 */
	public String getSecondName() {
		return secondName;
	}

	/**
	 * @param secondName
	 */
	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}

	/**
	 * @return the second last name
	 */
	public String getSecondLastName() {
		return secondLastName;
	}

	/**
	 * @param secondLastName
	 */
	public void setSecondLastName(String secondLastName) {
		this.secondLastName = secondLastName;
	}

	/**
	 * @return the gender
	 */
	public GenderTypeEnum getGender() {
		return gender;
	}

	/**
	 * @param gender
	 */
	public void setGender(GenderTypeEnum gender) {
		this.gender = gender;
	}

	/**
	 * @return the birth date
	 */
	public Date getBirthDate() {
		return birthDate;
	}

	/**
	 * @param birthDate
	 */
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	/**
	 * @return the nationality
	 */
	public String getNationality() {
		return nationality;
	}

	/**
	 * @param nationality
	 */
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	/**
	 * @return the primary email
	 */
	public String getPrimaryEmail() {
		return primaryEmail;
	}

	/**
	 * @param primaryEmail
	 */
	public void setPrimaryEmail(String primaryEmail) {
		this.primaryEmail = primaryEmail;
	}

	/**
	 * @return the alternative email
	 */
	public String getAlternativeEmail() {
		return alternativeEmail;
	}

	/**
	 * @param alternativeEmail
	 */
	public void setAlternativeEmail(String alternativeEmail) {
		this.alternativeEmail = alternativeEmail;
	}

}
