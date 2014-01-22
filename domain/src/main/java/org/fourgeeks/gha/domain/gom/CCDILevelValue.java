package org.fourgeeks.gha.domain.gom;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.fourgeeks.gha.domain.AbstractEntity;
import org.fourgeeks.gha.domain.enu.CCDIValueStatusEnum;

/**
 * @author emiliot
 * 
 */

@Entity
public class CCDILevelValue extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@OneToOne
	@JoinColumn(name = "ccdiLevelDefinitionFk")
	private CCDILevelDefinition levelDefinition;

	private String name;
	private String nextValue;
	private CCDIValueStatusEnum status;

	/**
	 * 
	 */
	public CCDILevelValue() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the levelDefinition
	 */
	public CCDILevelDefinition getLevelDefinition() {
		return levelDefinition;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the nextValue
	 */
	public String getNextValue() {
		return nextValue;
	}

	/**
	 * @return the status
	 */
	public CCDIValueStatusEnum getStatus() {
		return status;
	}

	/**
	 * @param levelDefinition
	 *            the levelDefinition to set
	 */
	public void setLevelDefinition(CCDILevelDefinition levelDefinition) {
		this.levelDefinition = levelDefinition;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param nextValue
	 *            the nextValue to set
	 */
	public void setNextValue(String nextValue) {
		this.nextValue = nextValue;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(CCDIValueStatusEnum status) {
		this.status = status;
	}

}
