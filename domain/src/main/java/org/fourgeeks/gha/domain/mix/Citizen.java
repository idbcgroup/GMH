package org.fourgeeks.gha.domain.mix;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import org.fourgeeks.gha.domain.AbstractEntity;
import org.fourgeeks.gha.domain.enu.DocumentTypeEnum;
import org.fourgeeks.gha.domain.enu.GenderTypeEnum;

/**
 * @author alacret, vivi.torresg
 * 
 *         Entity that represents a citizen
 */
@Entity
@NamedQueries(value = {
		@NamedQuery(name = "Citizen.getAll", query = "SELECT e from Citizen e order by e.firstLastName"),
		@NamedQuery(name = "Citizen.findByCitizen", query = "SELECT e from Citizen e where e like :citizen order by e.firstLastName") })
public class Citizen extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotNull
	@OneToOne
	@JoinColumn(name = "legalEntityFk", nullable = false)
	private LegalEntity legalEntity;

	private DocumentTypeEnum idType;
	/** Tipo de documento de Identificación del ciudadano length =60 */

	private String idNumber;
	/** No de documento de Identificación del ciudadano length =30 */

	private String firstName;
	/** 1er Nombre del ciudadano length =255 */
	private String firstLastName;
	/** Apellido del ciudadano length =255 */

	private String secondName;
	/** 2do Nombre del ciudadano length =255 */
	private String secondLastName;
	/** 2do Apellido del ciudadano length =255 */

	@NotNull
	@Column(nullable = false)
	private GenderTypeEnum gender;
	/** Sexo del ciudadano length =60 */

	private Date birthDate;
	/** Fecha Nacimiento del ciudadano length =12 */
	private String nationality;
	/** Nacionalidad actual del ciudadano length =60 */

	private String primaryEmail;
	/** Correo primario del Ciudadano length =255 */
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

	public GenderTypeEnum getGender() {
		return gender;
	}

	public void setGender(GenderTypeEnum gender) {
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

	public String getPrimaryEmail() {
		return primaryEmail;
	}

	public void setPrimaryEmail(String primaryEmail) {
		this.primaryEmail = primaryEmail;
	}

	public String getAlternativeEmail() {
		return alternativeEmail;
	}

	public void setAlternativeEmail(String alternativeEmail) {
		this.alternativeEmail = alternativeEmail;
	}

}
