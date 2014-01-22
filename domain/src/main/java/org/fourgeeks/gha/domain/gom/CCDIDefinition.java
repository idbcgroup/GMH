package org.fourgeeks.gha.domain.gom;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.fourgeeks.gha.domain.AbstractEntity;
import org.fourgeeks.gha.domain.enu.CCDIStatusEnum;
import org.fourgeeks.gha.domain.enu.CodeTypeEnum;

/**
 * @author emiliot
 * 
 */
@Entity
public class CCDIDefinition extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String code;
	private String name;
	private int length;
	private int levels;
	private CCDIStatusEnum status;

	@ManyToOne
	@JoinColumn(name = "conceptFk")
	private Concept concept;

	private CodeTypeEnum type;
	private boolean verification;
	private String verificationMethod;

	/**
	 * 
	 */
	public CCDIDefinition() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
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
	public CodeTypeEnum getType() {
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
	 * @param code
	 *            the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @param concept
	 *            the concept to set
	 */
	public void setConcept(Concept concept) {
		this.concept = concept;
	}

	/**
	 * @param length
	 *            the length to set
	 */
	public void setLength(int length) {
		this.length = length;
	}

	/**
	 * @param levels
	 *            the levels to set
	 */
	public void setLevels(int levels) {
		this.levels = levels;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(CCDIStatusEnum status) {
		this.status = status;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(CodeTypeEnum type) {
		this.type = type;
	}

	/**
	 * @param verification
	 *            the verification to set
	 */
	public void setVerification(boolean verification) {
		this.verification = verification;
	}

	/**
	 * @param verificationMethod
	 *            the verificationMethod to set
	 */
	public void setVerificationMethod(String verificationMethod) {
		this.verificationMethod = verificationMethod;
	}

}
