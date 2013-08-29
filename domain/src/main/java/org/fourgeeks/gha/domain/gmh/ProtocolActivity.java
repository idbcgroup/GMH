package org.fourgeeks.gha.domain.gmh;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.validation.constraints.NotNull;

import org.fourgeeks.gha.domain.AbstractEntity;

/**
 * @author alacret
 * 
 */
@Entity
@NamedQueries(value = {
		@NamedQuery(name = "ProtocolActivity.getAll", query = "SELECT e from ProtocolActivity e order by e.id"),
		@NamedQuery(name = "ProtocolActivity.findByMaintenanceProtocol", query = "SELECT a FROM ProtocolActivityMaintenanceProtocol e JOIN e.activity a WHERE e.protocol = :maintenanceProtocol order by e.ordinal"),
		@NamedQuery(name = "ProtocolActivity.findByServiceResource", query = "SELECT p FROM  ProtocolActivityServiceResource e JOIN e.protocolActivity p WHERE e.serviceResource = :serviceResource")})
public class ProtocolActivity extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotNull(message = "El nombre de la actividad no puede ser nulo")
	@Column(nullable = false)
	private String name;

	private String description;

	/**
	 * 
	 */
	public ProtocolActivity() {
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
