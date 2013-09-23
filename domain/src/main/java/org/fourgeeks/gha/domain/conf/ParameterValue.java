/**
 * 
 */
package org.fourgeeks.gha.domain.conf;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.fourgeeks.gha.domain.AbstractEntity;

/**
 * @author emiliot
 * 
 */
@Entity
@Table(name = "ParameterValue", schema = "conf")
public class ParameterValue extends AbstractEntity {

	/**
	 * @param parameterGroup
	 * @param parameter
	 * @param value
	 */
	public ParameterValue(ParameterGroup parameterGroup, Parameter parameter,
			String value) {
		super();
		this.parameterGroup = parameterGroup;
		this.parameter = parameter;
		this.value = value;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "parameterGroupFk")
	private ParameterGroup parameterGroup;

	@ManyToOne
	@JoinColumn(name = "parameterFk")
	private Parameter parameter;

	private String value;

	/**
	 * 
	 */
	public ParameterValue() {
	}

	public ParameterGroup getParameterGroup() {
		return parameterGroup;
	}

	public void setParameterGroup(ParameterGroup parameterGroup) {
		this.parameterGroup = parameterGroup;
	}

	public Parameter getParameter() {
		return parameter;
	}

	public void setParameter(Parameter parameter) {
		this.parameter = parameter;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
