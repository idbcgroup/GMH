/**
 * 
 */
package org.fourgeeks.gha.domain.gar;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.fourgeeks.gha.domain.AbstractEntity;
import org.fourgeeks.gha.domain.gmh.ServiceResourceCategory;

/**
 * @author emiliot
 *
 */
@Entity
public class FacilityCategory extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	@JoinColumn(name = "serviceResourceCategoryFk")
	private ServiceResourceCategory sRCategory;

	/**
	 * 
	 */
	public FacilityCategory() {
		// TODO Auto-generated constructor stub
	}

	public ServiceResourceCategory getsRCategory() {
		return sRCategory;
	}

	public void setsRCategory(ServiceResourceCategory sRCategory) {
		this.sRCategory = sRCategory;
	}
	
}
