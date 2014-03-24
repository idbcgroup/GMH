package org.fourgeeks.gha.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 * Entity implementation class for Entity: ActivityType
 * 
 */
@Entity
@NamedQueries(value = {
		@NamedQuery(name = "ActivityType.getAllTypes", query = "SELECT atype FROM ActivityType atype WHERE atype.parentActivityTypeId = 0 ORDER BY atype.id"),
		@NamedQuery(name = "ActivityType.getSubTypes", query = "SELECT atype FROM ActivityType atype WHERE atype.parentActivityTypeId = :parentId ORDER BY atype.id"),
		@NamedQuery(name = "ActivityType.getAll", query = "SELECT atype FROM ActivityType atype WHERE atype.parentActivityTypeId = :parentId ORDER BY atype.id") })
public class ActivityType extends AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	private String description;

	private long parentActivityTypeId;

	/**
	 * 
	 */
	public ActivityType() {
		super();
		parentActivityTypeId = 0L;
	}

	/**
	 * @return the parentActivityType
	 */
	public long getParentActivityTypeId() {
		return parentActivityTypeId;
	}

	/**
	 * @param parentActivityTypeId
	 *            the parentActivityType to set
	 */
	public void setParentActivityTypeId(long parentActivityTypeId) {
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
