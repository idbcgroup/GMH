/**
 * 
 */
package org.fourgeeks.gha.domain.gmh;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.fourgeeks.gha.domain.AbstractEntity;
import org.fourgeeks.gha.domain.glm.ExternalProvider;

/**
 * @author emiliot
 *
 */

@Entity
public class SRCategoryProvider extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	@JoinColumn(name = "serviceResourceCategoryFk")
	private ServiceResourceCategory sRCategory;
	
	@ManyToOne
	@JoinColumn(name = "brandFk")
	private Brand brand;
	
	@ManyToOne
	@JoinColumn(name = "externalProviderFk")
	private ExternalProvider externalProvider;

	/**
	 * 
	 */
	public SRCategoryProvider() {
		// TODO Auto-generated constructor stub
	}

	public ServiceResourceCategory getsRCategory() {
		return sRCategory;
	}

	public void setsRCategory(ServiceResourceCategory sRCategory) {
		this.sRCategory = sRCategory;
	}

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	public ExternalProvider getExternalProvider() {
		return externalProvider;
	}

	public void setExternalProvider(ExternalProvider externalProvider) {
		this.externalProvider = externalProvider;
	}

}
