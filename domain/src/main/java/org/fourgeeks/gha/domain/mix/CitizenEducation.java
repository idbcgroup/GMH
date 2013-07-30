package org.fourgeeks.gha.domain.mix;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.fourgeeks.gha.domain.AbstractEntity;
import org.fourgeeks.gha.domain.enu.EducationTypeEnum;
import org.fourgeeks.gha.domain.enu.EducationalInstitutionTypeEnum;

@Entity
public class CitizenEducation extends AbstractEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "citizenFk")
	private Citizen citizen;

	/** Attributes */
	private Long startYear;
	/** length =10 */
	private Long endYear;
	/** length =10 */

	private EducationTypeEnum educationType;
	/** length =60 */

	private String educationalInstitutionName;
	/** length =255 */

	private EducationalInstitutionTypeEnum educationalInstitutionType;
	/** length =60 */

	private String studyName;
	/** length =255 */
	private String educationalInstitution;
	/** length =255 */
	private String study;

	/** length =255 */

	/**
	 * 
	 */
	public CitizenEducation() {
		super();
		// TODO Auto-generated constructor stub
	}
}
