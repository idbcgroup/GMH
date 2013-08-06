package org.fourgeeks.gha.domain.mix;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.fourgeeks.gha.domain.AbstractEntity;
import org.fourgeeks.gha.domain.enu.BloodTypeEnum;
import org.fourgeeks.gha.domain.enu.EthnicGroupEnum;
import org.fourgeeks.gha.domain.enu.GenderTypeEnum;
import org.fourgeeks.gha.domain.enu.IdDocumentTypeEnum;
import org.fourgeeks.gha.domain.enu.InstructionDegreeEnum;
import org.fourgeeks.gha.domain.enu.LateralityTypeEnum;
import org.fourgeeks.gha.domain.enu.MaritalStatusEnum;
import org.fourgeeks.gha.domain.enu.MigratoryStatusEnum;
import org.fourgeeks.gha.domain.enu.MixStatusEnum;
import org.fourgeeks.gha.domain.enu.OccupationEnum;
import org.fourgeeks.gha.domain.enu.RaceTypeEnum;
import org.fourgeeks.gha.domain.enu.RhFactorEnum;
import org.fourgeeks.gha.domain.enu.TransportationTypeEnum;
import org.fourgeeks.gha.domain.enu.VehicleTypeEnum;

@Entity
public class Citizen extends AbstractEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@OneToOne
	@JoinColumn(name = "legalEntityFk")
	private LegalEntity legalEntity;

	/** Attributes */

	private String firstName;
	/** length =255 */

	private IdDocumentTypeEnum idDocumentType;
	/** length =60 */

	private MixStatusEnum mixStatus;
	/** length =60 */

	private GenderTypeEnum gender;
	/** length =60 */

	private LateralityTypeEnum laterality;
	/** length =60 */

	private String timeUnit;
	/** length =20 */

	private BloodTypeEnum bloodGroup;
	/** length =60 */

	private String nationalityType;
	/** length =60 */

	private String firstLastName;
	/** length =255 */
	private String secondName;
	/** length =255 */
	private String secondLastName;
	/** length =255 */
	private String idDocumentNumber;
	/** length =30 */
	private Timestamp birthDate;
	/** length =22 */

	private MaritalStatusEnum maritalStatus;
	/** length =60 */

	private String religion;
	/** length =60 */
	private String nationality;
	/** length =60 */
	private String birthCountry;
	/** length =60 */
	private String birthState;
	/** length =60 */
	private String birthCity;
	/** length =60 */
	private String birthMunicipality;
	/** length =255 */
	private String birthParish;
	/** length =255 */
	private String primaryEmail;
	/** length =255 */
	private String alternativeEmail;
	/** length =255 */

	private RaceTypeEnum race;
	/** length =60 */

	private EthnicGroupEnum ethnicGroup;
	/** length =60 */

	private MigratoryStatusEnum migratoryStatus;
	/** length =60 */

	private Long timeInCountry;
	/** length =10 */
	private String socialSecurityNumber;
	/** length =16 */
	private String fiscalRegistrationNumber;
	/** length =16 */
	private Boolean illiterate;
	/** length =1 */

	private InstructionDegreeEnum instructionDegree;
	/** length =60 */

	private String jobTypeDescription;
	/** length =255 */

	private OccupationEnum occupation;
	/** length =60 */
	private Boolean vehicleOwner;
	/** length =1 */

	private VehicleTypeEnum vehicleType;
	/** length =60 */

	private TransportationTypeEnum transportationType;
	/** length =60 */

	private Short studyReadingWriting;
	/** length =1 */

	private RhFactorEnum rhFactor;

	/** length =60 */

	/**
	 * Constructor
	 */
	public Citizen() {
		// super();
		// TODO Auto-generated constructor stub
	}

}
