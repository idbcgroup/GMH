package org.fourgeeks.gha.domain;

import java.io.Serializable;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * @author alacret A super class to define entities with a code as a primary key
 */
@MappedSuperclass
public abstract class AbstractCodeEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private String code;

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
