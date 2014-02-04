package org.fourgeeks.gha.domain.gmh;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import org.fourgeeks.gha.domain.AbstractEntity;
import org.fourgeeks.gha.domain.enu.ServiceAndResourceType;

/**
 * Entity to represent resources and services
 * 
 * @author emiliot, naramirez
 */
@Entity
@NamedQueries(value = {
		@NamedQuery(name = "ServiceAndResource.getAll", query = "SELECT e from ServiceAndResource e order by e.id"),
		@NamedQuery(name = "ServiceAndResource.findByActivity", query = "SELECT r FROM  RequiredResources e JOIN e.resource r WHERE e.activity = :activity") })
public class ServiceAndResource extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String code;
	private ServiceAndResourceType type;

	/**
	 * 
	 */
	public ServiceAndResource() {
	}

	/**
	 * @param code
	 *            the code of the resource or service
	 */
	public ServiceAndResource(String code, ServiceAndResourceType type) {
		this.code = code;
		this.type = type;
	}

	/**
	 * 
	 * @return the code of the resource or service
	 */
	public String getCode() {
		return code;
	}

	/**
	 * 
	 * @param code
	 *            the code of the resource or service
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the type of the service or resource
	 */
	public ServiceAndResourceType getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type of the service or resource
	 */
	public void setType(ServiceAndResourceType type) {
		this.type = type;
	}
}
