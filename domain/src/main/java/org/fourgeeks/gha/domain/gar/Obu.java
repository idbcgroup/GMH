package org.fourgeeks.gha.domain.gar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.fourgeeks.gha.domain.AbstractEntity;
import org.fourgeeks.gha.domain.mix.Bpi;

/**
 * @author alacret
 * 
 *         Organization Bussiness unit
 */
@Entity
public class Obu extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "bpiFk")
	private Bpi bpi;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private String code;

	// @OneToMany(mappedBy = "obu")
	// private Collection <ExternalObu> externalObus;

	// @OneToMany(mappedBy = "obu")
	// private Collection <CobeAccount> cobeAccounts;

	// @OneToMany(mappedBy = "obu")
	// private Collection <JobPosition> jobPositions;

	// @OneToMany(mappedBy = "obu")
	// private Collection <Waio> waios;

	/**
	 * This represents the children collection of this obu
	 */
	// @OneToMany(mappedBy = "parentObu")
	// private Collection <ObuChild> obuChildren;

	/**
	 * This represents the link relation to my parent (if any), semantically it
	 * says who is my obuChild to refer to my obu parent
	 */
	@OneToOne(mappedBy = "obu")
	private ObuChild obuChild;

	/**
	 * @return the bpi
	 */
	public Bpi getBpi() {
		return bpi;
	}

	/**
	 * @param bpi
	 *            the bpi to set
	 */
	public void setBpi(Bpi bpi) {
		this.bpi = bpi;
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

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code
	 *            the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

}
