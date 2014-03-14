package org.fourgeeks.gha.domain;

import java.io.Serializable;

import javax.persistence.Entity;

/**
 * Entity implementation class for Entity: ActivityType
 * 
 */
@Entity
public class ActivityType extends AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	private String description;

	private Long parentActivityTypeId;

	/**
	 * 
	 */
	public ActivityType() {
		super();
	}

	/**
	 * @return the parentActivityType
	 */
	public Long getParentActivityTypeId() {
		return parentActivityTypeId;
	}

	/**
	 * @param parentActivityTypeId
	 *            the parentActivityType to set
	 */
	public void setParentActivityTypeId(Long parentActivityTypeId) {
		this.parentActivityTypeId = parentActivityTypeId;
	}

	/**
	 * @return the activity tyoe description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

}
