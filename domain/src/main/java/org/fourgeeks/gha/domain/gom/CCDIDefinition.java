package org.fourgeeks.gha.domain.gom;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import org.fourgeeks.gha.domain.AbstractCodeEntity;
import org.fourgeeks.gha.domain.enu.CCDICodeTypeEnum;
import org.fourgeeks.gha.domain.enu.CCDIStatusEnum;

/**
 * @author emiliot
 * 
 */
@Entity
@NamedQueries(value = { @NamedQuery(name = "CCDIDefinition.findByCode", query = "SELECT e from CCDIDefinition e WHERE e.code=:code") })
public class CCDIDefinition extends AbstractCodeEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String name;
	@Column(nullable = false)
	private int length;
	@Column(nullable = false)
	private int levels;
	private CCDIStatusEnum status;

	@ManyToOne
	@JoinColumn(name = "conceptFk")
	private Concept concept;

	private CCDICodeTypeEnum type;
	private boolean verification;
	private String verificationMethod;

	/**
	 * 
	 */
	public CCDIDefinition() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param code
	 */
	public CCDIDefinition(final String code) {
		this.code = code;
	}

	/**
	 * @param code
	 * @param name
	 * @param length
	 * @param levels
	 * @param status
	 * @param concept
	 * @param type
	 * @param verification
	 * @param verificationMethod
	 */
	public CCDIDefinition(final String code, final String name,
			final int length, final int levels, final CCDIStatusEnum status,
			final Concept concept, final CCDICodeTypeEnum type,
			final boolean verification, final String verificationMethod) {
		this.code = code;
		this.name = name;
		this.length = length;
		this.levels = levels;
		this.status = status;
		this.concept = concept;
		this.type = type;
		this.verification = verification;
		this.verificationMethod = verificationMethod;
	}

	/**
	 * @return the concept
	 */
	public Concept getConcept() {
		return concept;
	}

	/**
	 * @return the length
	 */
	public int getLength() {
		return length;
	}

	/**
	 * @return the levels
	 */
	public int getLevels() {
		return levels;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the status
	 */
	public CCDIStatusEnum getStatus() {
		return status;
	}

	/**
	 * @return the type
	 */
	public CCDICodeTypeEnum getType() {
		return type;
	}

	/**
	 * @return the verificationMethod
	 */
	public String getVerificationMethod() {
		return verificationMethod;
	}

	/**
	 * @return the verification
	 */
	public boolean isVerification() {
		return verification;
	}

	/**
	 * @param concept
	 *            the concept to set
	 */
	public void setConcept(final Concept concept) {
		this.concept = concept;
	}

	/**
	 * @param length
	 *            the length to set
	 */
	public void setLength(final int length) {
		this.length = length;
	}

	/**
	 * @param levels
	 *            the levels to set
	 */
	public void setLevels(final int levels) {
		this.levels = levels;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(final String name) {
		this.name = name;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(final CCDIStatusEnum status) {
		this.status = status;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(final CCDICodeTypeEnum type) {
		this.type = type;
	}

	/**
	 * @param verification
	 *            the verification to set
	 */
	public void setVerification(final boolean verification) {
		this.verification = verification;
	}

	/**
	 * @param verificationMethod
	 *            the verificationMethod to set
	 */
	public void setVerificationMethod(final String verificationMethod) {
		this.verificationMethod = verificationMethod;
	}

}
