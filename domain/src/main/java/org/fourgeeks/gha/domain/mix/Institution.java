package org.fourgeeks.gha.domain.mix;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.fourgeeks.gha.domain.AbstractEntity;
import org.fourgeeks.gha.domain.enu.InstitutionTypeEnum;

/**
 * @author alacret
 * 
 */
@Entity
public class Institution extends AbstractEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@OneToOne
	@JoinColumn(name = "legalEntityFk", nullable = false)
	private LegalEntity legalEntity;

	// @OneToMany(mappedBy = "institution")
	// private Collection<InstitutionContact> institutionContacts;
	//
	// @OneToMany(mappedBy = "institution")
	// private Collection<SystemInstance> systemInstances;
	//
	// @OneToMany(mappedBy = "institution")
	// private Collection<Bpi> bpis;
	//
	// @OneToMany(mappedBy = "institution")
	// private Collection<WorkingHoliday> workingHolidays;
	//
	// /**
	// * This represents the children collection of this institution
	// */
	// @OneToMany(mappedBy = "parentInstitution")
	// private Collection<InstitutionChild> institutionChildren;
	//
	/**
	 * 
	 * This represents the link relation to my parent (if any), semantically it
	 * says who is my institutionChild to refer to my institution parent
	 */
	@OneToOne(mappedBy = "institution")
	private InstitutionChild institutionChild;

	/** Attributes */

	@Column(nullable = false)
	private String name;
	/** length =255 */

	private InstitutionTypeEnum institutionType;
	/** length =20 */

	private String institutionDescription;
	/** length =255 */
	private String institutionLevel;
	/** length =20 */
	private String institutionSector;
	/** length =20 */
	private String institutionProductiveSector;
	/** length =255 */
	private String institutionPrincipalProduct;
	/** length =255 */
	private String institutionHealthCode;
	/** length =255 */
	private String institutionTaxCode;
	/** length =255 */
	private String institutionProductiveSubSector;
	/** length =255 */
	private String institutionRisk;

	/** length =255 */

	/**
	 * 
	 */
	public Institution() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the legalEntity
	 */
	public LegalEntity getLegalEntity() {
		return legalEntity;
	}

	/**
	 * @param legalEntity
	 *            the legalEntity to set
	 */
	public void setLegalEntity(LegalEntity legalEntity) {
		this.legalEntity = legalEntity;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
}
