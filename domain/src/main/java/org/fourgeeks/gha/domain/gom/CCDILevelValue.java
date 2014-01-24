package org.fourgeeks.gha.domain.gom;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;

import org.fourgeeks.gha.domain.AbstractEntity;
import org.fourgeeks.gha.domain.enu.CCDIValueStatusEnum;

/**
 * @author emiliot
 * 
 */

@Entity
@NamedQueries(value = {
		@NamedQuery(name = "CCDILevelValue.findByLevelDefinition", query = "SELECT e from CCDILevelValue e WHERE e.levelDefinition=:levelDefinition"),
		@NamedQuery(name = "CCDILevelValue.findByCode", query = "SELECT e from CCDILevelValue e WHERE e.code=:code") })
public class CCDILevelValue extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@OneToOne
	@JoinColumn(name = "ccdiLevelDefinitionFk")
	private CCDILevelDefinition levelDefinition;

	private String name;
	private String code;
	private int nextValue;
	private CCDIValueStatusEnum status;

	/**
	 * 
	 */
	public CCDILevelValue() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
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
	public int getNextValue() {
		return nextValue;
	}

	/**
	 * @return the status
	 */
	public CCDIValueStatusEnum getStatus() {
		return status;
	}

	/**
	 * @param code
	 *            the code to set
	 */
	public void setCode(String code) {
		this.code = code;
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
	public void setNextValue(int nextValue) {
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
