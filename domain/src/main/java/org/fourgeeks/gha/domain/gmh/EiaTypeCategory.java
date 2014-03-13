/**
 * 
 */
package org.fourgeeks.gha.domain.gmh;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import org.fourgeeks.gha.domain.AbstractCodeEntity;

/**
 * @author emiliot
 * 
 */
@Entity
@NamedQueries(value = {
		@NamedQuery(name = "EiaTypeCategory.getAll", query = "SELECT category from EiaTypeCategory category ORDER BY category.code"),
		@NamedQuery(name = "EiaTypeCategory.findByCode", query = "SELECT category from EiaTypeCategory category WHERE category.code = :code") })
public class EiaTypeCategory extends AbstractCodeEntity implements

Comparable<EiaTypeCategory> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "serviceResourceCategoryFk")
	private ServiceResourceCategory sRCategory;

	private String name;

	/**
	 * 
	 */
	public EiaTypeCategory() {
		// TODO Auto-generated constructor stub
	}

	public EiaTypeCategory(String code) {
		this.code = code;
	}

	@Override
	public int compareTo(EiaTypeCategory arg0) {
		return code.compareTo(arg0.getCode());
	}

	@Override
	public boolean equals(Object arg0) {
		if (arg0 == null)
			return false;
		if (this == arg0)
			return true;
		if (!(arg0 instanceof EiaTypeCategory))
			return false;
		final EiaTypeCategory other = (EiaTypeCategory) arg0;
		return this.code.equals(other.getCode());
	}

	public String getName() {
		return name;
	}

	public ServiceResourceCategory getsRCategory() {
		return sRCategory;
	}

	@Override
	public int hashCode() {
		return code.hashCode();
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setsRCategory(ServiceResourceCategory sRCategory) {
		this.sRCategory = sRCategory;
	}

}
