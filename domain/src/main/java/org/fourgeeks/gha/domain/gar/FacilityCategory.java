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
	}

	/**
	 * @return the service Resource Category
	 */
	public ServiceResourceCategory getsRCategory() {
		return sRCategory;
	}

	/**
	 * @param sRCategory
	 */
	public void setsRCategory(ServiceResourceCategory sRCategory) {
		this.sRCategory = sRCategory;
	}

}
