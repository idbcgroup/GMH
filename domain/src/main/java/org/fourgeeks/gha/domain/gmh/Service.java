/**
 * 
 */
package org.fourgeeks.gha.domain.gmh;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

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
	private Service service;

	/**
	 * 
	 */
	public Service() {
		// TODO Auto-generated constructor stub
//	}
}

	public Service getService() {
		return service;
	}

	public void setService(Service service) {
		this.service = service;
	}
}