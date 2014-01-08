package org.fourgeeks.gha.domain.gmh;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.fourgeeks.gha.domain.AbstractEntity;
import org.fourgeeks.gha.domain.enu.ActivityTypeEnum;

/**
 * @author naramirez
 */
@Entity
@NamedQueries(value = {
		@NamedQuery(name = "Activity.getAll", query = "SELECT e from Activity e order by e.id"),
		@NamedQuery(name = "Activity.findByType", query = "SELECT act FROM Activity act WHERE act.type = :type order by act.id") })
public class Activity extends AbstractEntity {
	/** */
	private static final long serialVersionUID = 1L;

	@Size(max = 100)
	@NotNull(message = "name-not-null")
	@Column(nullable = false)
	private String name;

	@NotNull(message = "type-not-null")
	@Column(nullable = false)
	private ActivityTypeEnum type;

	/** */
	public Activity() {
	}

	/**
	 * @return the name of the activity
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the type of the maintenance activity
	 */
	public ActivityTypeEnum getType() {
		return type;
	}

	/**
	 * @param name
	 *            the name of the activity
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param type
	 *            the type of the activity
	 */
	public void setType(ActivityTypeEnum type) {
		this.type = type;
	}
}
