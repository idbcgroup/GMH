package org.fourgeeks.gha.domain.gom;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.fourgeeks.gha.domain.AbstractEntity;
import org.fourgeeks.gha.domain.enu.CCDIValueStatusEnum;

/**
 * @author emiliot
 * 
 */

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = { "code" }))
@NamedQueries(value = {
		@NamedQuery(name = "CCDILevelValue.findByCode", query = "SELECT e from CCDILevelValue e WHERE e.code=:code"),
		@NamedQuery(name = "CCDILevelValue.findAllByDefinitionCode", query = "SELECT e from CCDILevelValue e WHERE e.levelDefinition.definition.code = :code") })
public class CCDILevelValue extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "ccdiLevelDefinitionFk", columnDefinition = "bigint REFERENCES ccdileveldefinition(id) ON UPDATE CASCADE ON DELETE CASCADE")
	private CCDILevelDefinition levelDefinition;

	@ManyToOne
	@JoinColumn(name = "parentValueFk")
	private CCDILevelValue parentValue;

	private String name;

	private String code;

	private int nextValue;
	private String fixedValue;

	private int nextElement;

	private CCDIValueStatusEnum status;

	/**
	 * 
	 */
	public CCDILevelValue() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param levelDefinition
	 * @param parentValue
	 * @param name
	 * @param code
	 * @param nextValue
	 * @param fixedValue
	 * @param status
	 */
	public CCDILevelValue(CCDILevelDefinition levelDefinition,
			CCDILevelValue parentValue, String name, String code,
			int nextValue, String fixedValue, CCDIValueStatusEnum status) {
		this.levelDefinition = levelDefinition;
		this.parentValue = parentValue;
		this.name = name;
		this.code = code;
		this.nextValue = nextValue;
		this.fixedValue = fixedValue;
		this.status = status;
		this.nextElement = 1;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @return the fixedValue
	 */
	public String getFixedValue() {
		return fixedValue;
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

	public int getNextElement() {
		return nextElement;
	}

	/**
	 * @return the nextValue
	 */
	public int getNextValue() {
		return nextValue;
	}

	/**
	 * @return the parentValue
	 */
	public CCDILevelValue getParentValue() {
		return parentValue;
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
	 * @param fixedValue
	 *            the fixedValue to set
	 */
	public void setFixedValue(String fixedValue) {
		this.fixedValue = fixedValue;
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

	public void setNextElement(int nextElement) {
		this.nextElement = nextElement;
	}

	/**
	 * @param nextValue
	 *            the nextValue to set
	 */
	public void setNextValue(int nextValue) {
		this.nextValue = nextValue;
	}

	/**
	 * @param parentValue
	 *            the parentValue to set
	 */
	public void setParentValue(CCDILevelValue parentValue) {
		this.parentValue = parentValue;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(CCDIValueStatusEnum status) {
		this.status = status;
	}

}
