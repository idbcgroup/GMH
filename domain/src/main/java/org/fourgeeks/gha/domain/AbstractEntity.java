package org.fourgeeks.gha.domain;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

/**
 * @author alacret
 * 
 */
@MappedSuperclass
public abstract class AbstractEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@NotNull(message = "id-not-null")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected long id;

	/**
	 * @param id
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the Id
	 */
	public long getId() {
		return id;
	}

}