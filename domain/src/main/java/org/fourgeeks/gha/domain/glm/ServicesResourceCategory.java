/**
 * 
 */
package org.fourgeeks.gha.domain.glm;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import org.fourgeeks.gha.domain.AbstractCodeEntity;
import org.fourgeeks.gha.domain.gmh.EiaType;

/**
 * @author emiliot, vivi.torresg,eduardoguerere
 * 
 */

@Entity
@NamedQueries(value = {
		@NamedQuery(name = "ServicesResourceCategory.getAll", query = "SELECT e from ServicesResourceCategory e order by e.name"),
		@NamedQuery(name = "ServicesResourceCategory.findByCode", query = "SELECT category from ServicesResourceCategory category WHERE category.code=:code") })
public class ServicesResourceCategory extends AbstractCodeEntity implements
		Comparable<ServicesResourceCategory> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public ServicesResourceCategory() {
	}

	// @ManyToOne
	// @NotNull(message = "eiatype-category-not-null")
	// @JoinColumn(name = "eiaTypeCategoryFk", nullable = false)
	// private ServiceResourceCategory serviceResourceCategory;

	@ManyToOne
	@JoinColumn(name = "eiaTypeFk")
	private EiaType eiaType;

	// /**
	// * @return the serviceResourceCategory
	// */
	// public ServiceResourceCategory getEiaTypeCategory() {
	// return serviceResourceCategory;
	// }
	//
	// /**
	// * @param serviceResourceCategory
	// * the serviceResourceCategory to set
	// */
	// public void setEiaTypeCategory(
	// ServiceResourceCategory serviceResourceCategory) {
	// this.serviceResourceCategory = serviceResourceCategory;
	// }

	public ServicesResourceCategory(String code) {
		this.code = code;
	}

	@Override
	public int compareTo(ServicesResourceCategory arg0) {
		return code.compareTo(arg0.getCode());
	}

	@Override
	public boolean equals(Object arg0) {
		if (arg0 == null)
			return false;
		if (this == arg0)
			return true;
		if (!(arg0 instanceof ServicesResourceCategory))
			return false;
		ServicesResourceCategory other = (ServicesResourceCategory) arg0;
		return this.code.equals(other.getCode());
	}

	@Override
	public int hashCode() {
		return this.code.hashCode();
	}

}