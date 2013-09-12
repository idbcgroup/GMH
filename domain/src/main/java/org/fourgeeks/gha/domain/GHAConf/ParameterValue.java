/**
 * 
 */
package org.fourgeeks.gha.domain.GHAConf;

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
@Table(name = "ParameterValue", schema = "GHAConf")
public class ParameterValue extends AbstractEntity {

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
		// TODO Auto-generated constructor stub
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
