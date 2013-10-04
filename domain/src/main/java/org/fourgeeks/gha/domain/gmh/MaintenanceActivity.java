package org.fourgeeks.gha.domain.gmh;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.fourgeeks.gha.domain.AbstractEntity;

/**
 * @author alacret
 * 
 */
@Entity
@NamedQueries(value = {
		@NamedQuery(name = "MaintenanceActivity.getAll", query = "SELECT e from MaintenanceActivity e order by e.id"),
		@NamedQuery(name = "MaintenanceActivity.findByMaintenanceProtocol", query = "SELECT a FROM MaintenanceActivityMaintenanceProtocol e JOIN e.activity a WHERE e.protocol = :maintenanceProtocol order by e.ordinal"),
		@NamedQuery(name = "MaintenanceActivity.findByServiceResource", query = "SELECT p FROM  MaintenanceActivityServiceResource e JOIN e.maintenanceActivity p WHERE e.serviceResource = :serviceResource") })
public class MaintenanceActivity extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Size(min = 1, max = 100)
	@NotNull(message = "name-not-null")
	@Column(nullable = false)
	private String name;

	private String description;

	/**
	 * 
	 */
	public MaintenanceActivity() {
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
}
