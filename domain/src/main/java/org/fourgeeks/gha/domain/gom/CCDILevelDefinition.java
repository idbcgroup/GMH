/**
 * @author emiliot
 *
 */
package org.fourgeeks.gha.domain.gom;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.fourgeeks.gha.domain.AbstractEntity;
import org.fourgeeks.gha.domain.enu.CCDIEndValueActionEnum;
import org.fourgeeks.gha.domain.enu.CCDIValueTypeEnum;

/**
 * @author emiliot
 * 
 */
@Entity
@Table(name = "ccdileveldefinition", uniqueConstraints = @UniqueConstraint(columnNames = {
		"definitionFk", "level" }))
public class CCDILevelDefinition extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "definitionFk")
	private CCDIDefinition definition;

	private int level;

	private String name;
	private int levelLength;
	private CCDIValueTypeEnum valueType;
	// private Variable variableName; TODO: use this to add a variable to the
	// code
	private String fixedValue;
	private int initialValue;
	private int incValue;
	private String separator;
	private CCDIEndValueActionEnum valueAtEndAction;

	/**
	 * 
	 */
	public CCDILevelDefinition() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the definition
	 */
	public CCDIDefinition getDefinition() {
		return definition;
	}

	/**
	 * @return the fixedValue
	 */
	public String getFixedValue() {
		return fixedValue;
	}

	/**
	 * @return the incValue
	 */
	public int getIncValue() {
		return incValue;
	}

	/**
	 * @return the initialValue
	 */
	public int getInitialValue() {
		return initialValue;
	}

	/**
	 * @return the level
	 */
	public int getLevel() {
		return level;
	}

	/**
	 * @return the levelLength
	 */
	public int getLevelLength() {
		return levelLength;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the separator
	 */
	public String getSeparator() {
		return separator;
	}

	/**
	 * @return the valueAtEndAction
	 */
	public CCDIEndValueActionEnum getValueAtEndAction() {
		return valueAtEndAction;
	}

	/**
	 * @return the valueType
	 */
	public CCDIValueTypeEnum getValueType() {
		return valueType;
	}

	/**
	 * @param definition
	 *            the definition to set
	 */
	public void setDefinition(CCDIDefinition definition) {
		this.definition = definition;
	}

	/**
	 * @param fixedValue
	 *            the fixedValue to set
	 */
	public void setFixedValue(String fixedValue) {
		this.fixedValue = fixedValue;
	}

	/**
	 * @param incValue
	 *            the incValue to set
	 */
	public void setIncValue(int incValue) {
		this.incValue = incValue;
	}

	/**
	 * @param initialValue
	 *            the initialValue to set
	 */
	public void setInitialValue(int initialValue) {
		this.initialValue = initialValue;
	}

	/**
	 * @param level
	 *            the level to set
	 */
	public void setLevel(int level) {
		this.level = level;
	}

	/**
	 * @param levelLength
	 *            the levelLength to set
	 */
	public void setLevelLength(int levelLength) {
		this.levelLength = levelLength;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param separator
	 *            the separator to set
	 */
	public void setSeparator(String separator) {
		this.separator = separator;
	}

	/**
	 * @param valueAtEndAction
	 *            the valueAtEndAction to set
	 */
	public void setValueAtEndAction(CCDIEndValueActionEnum valueAtEndAction) {
		this.valueAtEndAction = valueAtEndAction;
	}

	/**
	 * @param valueType
	 *            the valueType to set
	 */
	public void setValueType(CCDIValueTypeEnum valueType) {
		this.valueType = valueType;
	}

}
