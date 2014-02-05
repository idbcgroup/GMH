/**
 * 
 */
package org.fourgeeks.gha.domain.gmh;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.fourgeeks.gha.domain.AbstractEntity;

/**
 * @author emiliot
 * 
 */
@Entity
public class Service extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "serviceCategoryFk")
	private ServiceCategory serviceCategory;

	@OneToOne
	@JoinColumn(name = "resourceFk")
	private ServiceAndResource serviceResource;

	/**
	 * 
	 */
	public Service() {
		// TODO Auto-generated constructor stub
		// }
	}

	public ServiceCategory getServiceCategory() {
		return serviceCategory;
	}

	public void setServiceCategory(ServiceCategory serviceCategory) {
		this.serviceCategory = serviceCategory;
	}

	public ServiceAndResource getServiceResource() {
		return serviceResource;
	}

	public void setServiceResource(ServiceAndResource serviceResource) {
		this.serviceResource = serviceResource;
	}
}