package org.fourgeeks.gha.domain.mix;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;

import org.fourgeeks.gha.domain.AbstractEntity;

/**
 * @author alacret, vivi.torresg
 * 
 */
@Entity
@NamedQueries(value = {
		@NamedQuery(name = "Institution.getAll", query = "SELECT e from Institution e order by e.name"),
		@NamedQuery(name = "Institution.findByInstitution", query = "SELECT e from Institution e where e like :institution order by e.name") })
public class Institution extends AbstractEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@OneToOne
	@JoinColumn(name = "legalEntityFk", nullable = false)
	private LegalEntity legalEntity;

	/** Attributes */

	@Column(nullable = false)
	private String name;

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
