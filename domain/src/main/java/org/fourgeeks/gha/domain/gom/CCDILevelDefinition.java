package org.fourgeeks.gha.domain.gom;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
@Table(uniqueConstraints = @UniqueConstraint(columnNames = { "definitionFk",
		"level", "code" }))
@NamedQueries(value = {
		@NamedQuery(name = "CCDILevelDefinition.findBylevel", query = "SELECT e from CCDILevelDefinition e WHERE e.level=:level AND e.definition=:definition"),
		@NamedQuery(name = "CCDILevelDefinition.findByCode", query = "SELECT e from CCDILevelDefinition e WHERE e.code=:code") })
public class CCDILevelDefinition extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "definitionFk")
	private CCDIDefinition definition;

	private String code;
	private int level;

	private String name;
	private int length;
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
	 * @param definition
	 * @param code
	 * @param level
	 * @param name
	 * @param length
	 * @param valueType
	 * @param fixedValue
	 * @param initialValue
	 * @param incValue
	 * @param separator
	 * @param valueAtEndAction
	 */
	public CCDILevelDefinition(CCDIDefinition definition, String code,
			int level, String name, int length, CCDIValueTypeEnum valueType,
			String fixedValue, int initialValue, int incValue,
			String separator, CCDIEndValueActionEnum valueAtEndAction) {
		this.definition = definition;
		this.code = code;
		this.level = level;
		this.name = name;
		this.length = length;
		this.valueType = valueType;
		this.fixedValue = fixedValue;
		this.initialValue = initialValue;
		this.incValue = incValue;
		this.separator = separator;
		this.valueAtEndAction = valueAtEndAction;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
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
	 * @return the length
	 */
	public int getLength() {
		return length;
	}

	/**
	 * @return the level
	 */
	public int getLevel() {
		return level;
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
	 * @param code
	 *            the code to set
	 */
	public void setCode(String code) {
		this.code = code;
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
	 * @param length
	 *            the length to set
	 */
	public void setLength(int length) {
		this.length = length;
	}

	/**
	 * @param level
	 *            the level to set
	 */
	public void setLevel(int level) {
		this.level = level;
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
