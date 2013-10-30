package org.fourgeeks.gha.domain;

import java.io.Serializable;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

/**
 * @author alacret A super class to define entities with a code as a primary key
 */
@MappedSuperclass
public abstract class AbstractCodeEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@NotNull(message = "code-not-null")
	@Id
	protected String code;

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code
	 */
	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public int hashCode() {
		return this.code.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (obj == this)
			return true;
		if (!(obj instanceof AbstractEntity))
			return false;
		AbstractCodeEntity entity = (AbstractCodeEntity) obj;
		return entity.getCode().equals(this);
	}

}
