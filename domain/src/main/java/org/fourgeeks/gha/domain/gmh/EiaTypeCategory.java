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
public class EiaTypeCategory extends AbstractEntity {

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
	public EiaTypeCategory() {
		// TODO Auto-generated constructor stub
	}

	public ServiceResourceCategory getsRCategory() {
		return sRCategory;
	}

	public void setsRCategory(ServiceResourceCategory sRCategory) {
		this.sRCategory = sRCategory;
	}
	
	

}
