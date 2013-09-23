/**
 * 
 */
package org.fourgeeks.gha.domain.conf;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.fourgeeks.gha.domain.AbstractCodeEntity;

/**
 * @author emiliot
 * 
 */
@Entity
@Table(name = "Parameter", schema = "conf")
public class Parameter extends AbstractCodeEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String name;

	/**
	 * 
	 */
	public Parameter() {
	}

	/**
	 * @param name
	 * @param code
	 * 
	 */
	public Parameter(String name, String code) {
		this.name = name;
		this.code = code;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

}
